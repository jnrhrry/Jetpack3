package com.dicoding.jetpack3.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.jetpack3.data.source.remote.response.ApiResponse
import com.dicoding.jetpack3.data.source.remote.response.Response
import com.dicoding.jetpack3.utils.AppExecutors
import com.dicoding.jetpack3.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors){
    private val result = MediatorLiveData<Resource<ResultType>>()
    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val db = loadFromDb()

        result.addSource(db){data ->
            result.removeSource(db)
            if (shouldFetch(data)){
                fetchFromNetwork(db)
            } else {
                result.addSource(db){new ->
                    result.value = Resource.success(new)
                }
            }
        }
    }

    protected open fun onFetchFailed(){}
    protected abstract fun loadFromDb(): LiveData<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
    protected abstract fun saveCallResult(data: RequestType)
    fun asLiveData(): LiveData<Resource<ResultType>> = result

    private fun fetchFromNetwork(db: LiveData<ResultType>){
        val api = createCall()
        result.addSource(db){new ->
            result.value = Resource.loading(new)
        }
        result.addSource(api){response ->
            result.removeSource(api)
            result.removeSource(db)
            when(response.status){
                Response.SUCCESS ->
                    mExecutors.diskIO().execute {
                        saveCallResult(response.body)
                        mExecutors.mainThread().execute {
                            result.addSource(loadFromDb()){
                                result.value = Resource.success(it)
                            }
                        }
                    }
                Response.EMPTY -> mExecutors.mainThread().execute {
                    result.addSource(loadFromDb()){ empty ->
                        result.value = Resource.success(empty)
                    }
                }
                Response.ERROR -> {
                    onFetchFailed()
                    result.addSource(db) { error ->
                        result.value = Resource.error(response.message, error)
                    }
                }
            }
        }
    }
}
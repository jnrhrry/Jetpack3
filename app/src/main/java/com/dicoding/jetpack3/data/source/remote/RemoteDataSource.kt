package com.dicoding.jetpack3.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jetpack3.data.source.remote.request.ApiHelper
import com.dicoding.jetpack3.data.source.remote.response.ApiResponse
import com.dicoding.jetpack3.data.source.remote.response.MovieResponse
import com.dicoding.jetpack3.data.source.remote.response.SeriesResponse
import com.dicoding.jetpack3.utils.EspressoIdlingResource

class RemoteDataSource private constructor(private val helper: ApiHelper){
    companion object{
        @Volatile
        private var INSTANCE: RemoteDataSource?=null
        fun getInstance(helper: ApiHelper): RemoteDataSource =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: RemoteDataSource(helper)
            }
    }

    fun getMovie(): LiveData<ApiResponse<List<MovieResponse>>>{
        val movies = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        EspressoIdlingResource.increment()
        val movieObserver: Observer<List<MovieResponse>> = object : Observer<List<MovieResponse>>{
            override fun onChanged(t: List<MovieResponse>) {
                movies.value = ApiResponse.success(t)
                if (!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow)
                    EspressoIdlingResource.decrement()
                helper.getMovies().removeObserver(this)
            }
        }
        helper.getMovies().observeForever(movieObserver)
        return movies
    }
    fun getSeries(): LiveData<ApiResponse<List<SeriesResponse>>>{
        val series = MutableLiveData<ApiResponse<List<SeriesResponse>>>()
        EspressoIdlingResource.increment()
        val seriesObserver: Observer<List<SeriesResponse>> = object : Observer<List<SeriesResponse>>{
            override fun onChanged(t: List<SeriesResponse>) {
                series.value = ApiResponse.success(t)
                if (!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow)
                    EspressoIdlingResource.decrement()
                helper.getTVSeries().removeObserver(this)
            }
        }
        helper.getTVSeries().observeForever(seriesObserver)
        return series
    }
}
package com.dicoding.jetpack3.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.jetpack3.data.source.DataSource
import com.dicoding.jetpack3.data.source.NetworkBoundResource
import com.dicoding.jetpack3.data.source.local.LocalDataSource
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity
import com.dicoding.jetpack3.data.source.remote.RemoteDataSource
import com.dicoding.jetpack3.data.source.remote.response.ApiResponse
import com.dicoding.jetpack3.data.source.remote.response.MovieResponse
import com.dicoding.jetpack3.data.source.remote.response.SeriesResponse
import com.dicoding.jetpack3.utils.AppExecutors
import com.dicoding.jetpack3.vo.Resource

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors): DataSource{
    companion object{
        @Volatile
        private var INSTANCE : Repository ?=null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): Repository = INSTANCE ?: synchronized(this){
            INSTANCE ?: Repository(remoteData, localData, appExecutors)
        }
    }

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors){
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1)
                    .setPageSize(1)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> = remoteDataSource.getMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val list = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        movieId = response.movieId,
                        title = response.title,
                        release = response.release,
                        rating = response.rating,
                        description = response.description,
                        imagePath = response.imagePath,
                        favorit = false
                    )
                    list.add(movie)
                }

                localDataSource.insertMovies(list)
            }
        }.asLiveData()
    }

    override fun getMovie(id: String): LiveData<MovieEntity> = localDataSource.getMovie(id)

    override fun getFavouriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1)
            .setPageSize(1)
            .build()
        return  LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(movie, state) }

    override fun getTVSeries(): LiveData<Resource<PagedList<SeriesEntity>>> {
        return object : NetworkBoundResource<PagedList<SeriesEntity>, List<SeriesResponse>>(appExecutors){
            override fun loadFromDb(): LiveData<PagedList<SeriesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1)
                    .setPageSize(1)
                    .build()
                return LivePagedListBuilder(localDataSource.getTVSeries(), config).build()
            }

            override fun shouldFetch(data: PagedList<SeriesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SeriesResponse>>> =
                remoteDataSource.getSeries()

            override fun saveCallResult(data: List<SeriesResponse>) {
                val list = ArrayList<SeriesEntity>()
                for (response in data){
                    val series = SeriesEntity(
                        tvShowId = response.tvShowId,
                        title = response.title,
                        release = response.release,
                        rating = response.rating,
                        description = response.description,
                        imagePath = response.imagePath,
                        favorit = false
                    )
                    list.add(series)
                }
                localDataSource.insertTVSeries(list)
            }

        }.asLiveData()
    }

    override fun getSeries(id: String): LiveData<SeriesEntity> = localDataSource.getSeries(id)

    override fun getFavoriteSeries(): LiveData<PagedList<SeriesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1)
            .setPageSize(1)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTVSeries(), config).build()
    }

    override fun setFavoriteSeries(series: SeriesEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTVSeries(series, state) }
}
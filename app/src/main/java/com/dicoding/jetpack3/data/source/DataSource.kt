package com.dicoding.jetpack3.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity
import com.dicoding.jetpack3.vo.Resource

interface DataSource {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovie(id: String): LiveData<MovieEntity>
    fun getFavouriteMovie(): LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun getTVSeries(): LiveData<Resource<PagedList<SeriesEntity>>>
    fun getSeries(id: String): LiveData<SeriesEntity>
    fun getFavoriteSeries(): LiveData<PagedList<SeriesEntity>>
    fun setFavoriteSeries(series: SeriesEntity, state: Boolean)
}
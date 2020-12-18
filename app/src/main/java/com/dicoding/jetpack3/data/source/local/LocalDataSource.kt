package com.dicoding.jetpack3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.jetpack3.data.source.local.room.ContentDao
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity

class LocalDataSource private constructor(private val dao: ContentDao){
    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dao: ContentDao): LocalDataSource =
            INSTANCE?: LocalDataSource(dao)
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = dao.getMovies()
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = dao.getFavoriteMovie()
    fun getMovie(id: String): LiveData<MovieEntity> = dao.getMovie(id)
    fun getTVSeries(): DataSource.Factory<Int, SeriesEntity> = dao.getTVSeries()
    fun getFavoriteTVSeries(): DataSource.Factory<Int, SeriesEntity> = dao.getFavoriteSeries()
    fun getSeries(id: String): LiveData<SeriesEntity> = dao.getSeries(id)
    fun insertMovies(movie: List<MovieEntity>) = dao.insertMovies(movie)
    fun insertTVSeries(series: List<SeriesEntity>)= dao.insertSeries(series)
    fun setFavoriteMovies(movie: MovieEntity, newState: Boolean){
        movie.favorit = newState
        dao.updateMovie(movie)
    }
    fun setFavoriteTVSeries(series: SeriesEntity, newState: Boolean){
        series.favorit = newState
        dao.updateSeries(series)
    }
}
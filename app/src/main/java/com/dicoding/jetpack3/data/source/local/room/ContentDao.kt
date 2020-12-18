package com.dicoding.jetpack3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity

@Dao
interface ContentDao {
    @Query("SELECT * FROM movieentity")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentity WHERE favorit = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentity WHERE movieId = :id")
    fun getMovie(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM seriesentity")
    fun getTVSeries(): DataSource.Factory<Int, SeriesEntity>

    @Query("SELECT * FROM seriesentity WHERE favorit = 1")
    fun getFavoriteSeries(): DataSource.Factory<Int, SeriesEntity>

    @Query("SELECT * FROM seriesentity WHERE tvShowId = :id")
    fun getSeries(id: String): LiveData<SeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<SeriesEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateSeries(series: SeriesEntity)
}
package com.dicoding.jetpack3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity

class DetailViewModel (private val repo: Repository): ViewModel(){
    val movieID = MutableLiveData<String>()
    val seriesID = MutableLiveData<String>()
    var movie: LiveData<MovieEntity> = Transformations.switchMap(movieID){
        repo.getMovie(it)
    }
    var series: LiveData<SeriesEntity> = Transformations.switchMap(seriesID){
        repo.getSeries(it)
    }

    fun setMovie(id: String){
        this.movieID.value = id
    }
    fun setSeries(id: String){
        this.seriesID.value = id
    }

    fun setFavoriteMovie(){
        val entity = movie.value
        if (entity != null){
            val newMovie = !entity.favorit
            repo.setFavoriteMovie(entity, newMovie)
        }
    }
    fun setFavoriteSeries(){
        val entity = series.value
        if (entity != null){
            val newSeries = !entity.favorit
            repo.setFavoriteSeries(entity, newSeries)
        }
    }
}
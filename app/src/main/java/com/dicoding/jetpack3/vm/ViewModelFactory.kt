package com.dicoding.jetpack3.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.di.Injection
import com.dicoding.jetpack3.ui.detail.DetailViewModel
import com.dicoding.jetpack3.ui.favourite.movie.FavoriteMovieViewModel
import com.dicoding.jetpack3.ui.favourite.tv.FavoriteSeriesViewModel
import com.dicoding.jetpack3.ui.home.movie.MovieViewModel
import com.dicoding.jetpack3.ui.home.tv.SeriesViewModel

class ViewModelFactory private constructor(private val repo: Repository): ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory?=null
        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: ViewModelFactory(Injection.provider(context))
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repo) as T
            }
            modelClass.isAssignableFrom(SeriesViewModel::class.java) -> {
                SeriesViewModel(repo) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repo) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(repo) as T
            }
            modelClass.isAssignableFrom(FavoriteSeriesViewModel::class.java) -> {
                FavoriteSeriesViewModel(repo) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}
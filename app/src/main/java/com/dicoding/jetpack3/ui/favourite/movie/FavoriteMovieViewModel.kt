package com.dicoding.jetpack3.ui.favourite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.MovieEntity

class FavoriteMovieViewModel (private val repo: Repository): ViewModel(){
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = repo.getFavouriteMovie()
}
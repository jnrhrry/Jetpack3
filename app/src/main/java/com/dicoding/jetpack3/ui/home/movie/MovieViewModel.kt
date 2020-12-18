package com.dicoding.jetpack3.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.vo.Resource

class MovieViewModel(private val repo: Repository): ViewModel(){
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = repo.getMovies()
}
package com.dicoding.jetpack3.ui.home.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.SeriesEntity
import com.dicoding.jetpack3.vo.Resource

class SeriesViewModel (private val repo: Repository): ViewModel(){
    fun getTVSeries(): LiveData<Resource<PagedList<SeriesEntity>>> = repo.getTVSeries()
}
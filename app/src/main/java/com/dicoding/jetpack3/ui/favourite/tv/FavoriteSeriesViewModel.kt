package com.dicoding.jetpack3.ui.favourite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.SeriesEntity

class FavoriteSeriesViewModel (private val repo: Repository): ViewModel(){
    fun getFavoritTVSeries(): LiveData<PagedList<SeriesEntity>> = repo.getFavoriteSeries()
}
package com.dicoding.jetpack3.ui.favorite.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.SeriesEntity
import com.dicoding.jetpack3.ui.favourite.tv.FavoriteSeriesViewModel
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteSeriesViewModelTest {
    private lateinit var vm: FavoriteSeriesViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var repo:Repository
    @Mock
    private lateinit var observer: Observer<PagedList<SeriesEntity>>
    @Mock
    private lateinit var pagedList: PagedList<SeriesEntity>

    @Before
    fun setUp(){
        vm = FavoriteSeriesViewModel(repo)
    }

    @Test
    fun getFavoriteSeriesTest(){
        val dummy = pagedList
        `when`(dummy.size).thenReturn(20)

        val series = MutableLiveData<PagedList<SeriesEntity>>()
        series.value = dummy
        `when`(repo.getFavoriteSeries()).thenReturn(series)

        val entity = vm.getFavoritTVSeries().value
        verify(repo).getFavoriteSeries()
        assertNotNull(entity)
        assertEquals(20, entity?.size)

        vm.getFavoritTVSeries().observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}
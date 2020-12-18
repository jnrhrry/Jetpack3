package com.dicoding.jetpack3.ui.home.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.SeriesEntity
import com.dicoding.jetpack3.ui.home.tv.SeriesViewModel
import com.dicoding.jetpack3.vo.Resource
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
class SeriesViewModelTest {
    private lateinit var vm: SeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo: Repository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<SeriesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<SeriesEntity>

    @Before
    fun setUp(){
        vm = SeriesViewModel(repo)
    }

    @Test
    fun getTVSeriesTest(){
        val dummies = Resource.success(pagedList)
        `when`(dummies.data?.size).thenReturn(10)

        val series = MutableLiveData<Resource<PagedList<SeriesEntity>>>()
        series.value = dummies
        `when`(repo.getTVSeries()).thenReturn(series)

        val entities = vm.getTVSeries().value?.data
        verify(repo).getTVSeries()
        assertNotNull(entities)
        assertEquals(10, entities?.size)

        vm.getTVSeries().observeForever(observer)
        verify(observer).onChanged(dummies)
    }
}
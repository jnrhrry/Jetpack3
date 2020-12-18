package com.dicoding.jetpack3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity
import com.dicoding.jetpack3.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{
    private lateinit var vm: DetailViewModel
    private val dummyMovie = DummyData.dummyMovies()[0]
    private val dummySeries = DummyData.dummySeries()[0]
    private val movieID = dummyMovie.movieId
    private val seriesID = dummySeries.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo:Repository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerSeries:Observer<SeriesEntity>

    @Before
    fun setUp(){
        vm = DetailViewModel(repo)
        vm.setSeries(seriesID)
        vm.setMovie(movieID)
    }
    @Test
    fun getDetailMovie(){
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie
        `when`(repo.getMovie(movieID)).thenReturn(movie)
        vm.movie.observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }
    @Test
    fun getDetailSeries(){
        val series = MutableLiveData<SeriesEntity>()
        series.value = dummySeries
        `when`(repo.getSeries(seriesID)).thenReturn(series)
        vm.series.observeForever(observerSeries)
        verify(observerSeries).onChanged(dummySeries)
    }
}
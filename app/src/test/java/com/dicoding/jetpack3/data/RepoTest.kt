package com.dicoding.jetpack3.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.jetpack3.data.source.local.LocalDataSource
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity
import com.dicoding.jetpack3.data.source.remote.RemoteDataSource
import com.dicoding.jetpack3.utils.AppExecutors
import com.dicoding.jetpack3.utils.DummyData
import com.dicoding.jetpack3.utils.LiveDataTest
import com.dicoding.jetpack3.utils.PagedList
import com.dicoding.jetpack3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepoTest {

    private val movieResponse = DummyData.dummyMovies()
    private val seriesResponse = DummyData.dummySeries()
    private val movieId = movieResponse[0].movieId
    private val seriesId = seriesResponse[0].tvShowId

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val repository = FakeRepository(remote, local, appExecutors)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        repository.getMovies()

        val movieEntities = Resource.success(PagedList.mockPagedList(DummyData.dummyMovies()))
        verify(local).getMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }
    @Test
    fun getMovie(){
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DummyData.dummyMovies()[0]
        `when`(local.getMovie(movieId)).thenReturn(dummyEntity)
        val entity = LiveDataTest.getValue(repository.getMovie(movieId))
        verify(local).getMovie(movieId)
        assertNotNull(entity)
        assertNotNull(entity.title)
        assertEquals(movieResponse[0].title, entity.title)
    }
    @Test
    fun getFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        repository.getFavouriteMovie()
        val entity = Resource.success(PagedList.mockPagedList(DummyData.dummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(entity)
        assertEquals(movieResponse.size.toLong(), entity.data?.size?.toLong())
    }

    @Test
    fun getTVSeries(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, SeriesEntity>
        `when`(local.getTVSeries()).thenReturn(dataSourceFactory)
        repository.getTVSeries()
        val entity = Resource.success(PagedList.mockPagedList(DummyData.dummySeries()))
        verify(local).getTVSeries()
        assertNotNull(entity.data)
        assertEquals(seriesResponse.size.toLong(), entity.data?.size?.toLong())
    }
    @Test
    fun getSeries(){
        val dummySeries = MutableLiveData<SeriesEntity>()
        dummySeries.value = DummyData.dummySeries()[0]
        `when`(local.getSeries(seriesId)).thenReturn(dummySeries)
        val entities = LiveDataTest.getValue(repository.getSeries(seriesId))
        verify(local).getSeries(seriesId)
        assertNotNull(entities)
        assertNotNull(entities.title)
        assertEquals(seriesResponse[0].title, entities.title)
    }

    @Test
    fun getFavoriteTVSeries(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, SeriesEntity>
        `when`(local.getFavoriteTVSeries()).thenReturn(dataSourceFactory)
        repository.getFavoriteSeries()
        val entities = Resource.success(PagedList.mockPagedList(DummyData.dummySeries()))
        verify(local).getFavoriteTVSeries()
        assertNotNull(entities)
        assertEquals(seriesResponse.size.toLong(), entities.data?.size?.toLong())
    }
}
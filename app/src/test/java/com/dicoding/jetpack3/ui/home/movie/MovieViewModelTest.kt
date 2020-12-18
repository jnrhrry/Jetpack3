package com.dicoding.jetpack3.ui.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.MovieEntity
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
class MovieViewModelTest {

    private lateinit var vm: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var repo: Repository
    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>
    @Mock
    private lateinit var pageList: PagedList<MovieEntity>

    @Before
    fun setUp(){
        vm = MovieViewModel(repo)
    }

    @Test
    fun getMoviesTest(){
        val dummies = Resource.success(pageList)
        `when`(dummies.data?.size).thenReturn(20)

        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummies
        `when`(repo.getMovies()).thenReturn(movies)

        val entity = vm.getMovies().value?.data
        verify(repo).getMovies()
        assertNotNull(entity)
        assertEquals(20, entity?.size)

        vm.getMovies().observeForever(observer)
        verify(observer).onChanged(dummies)
    }
}
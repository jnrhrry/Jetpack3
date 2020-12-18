package com.dicoding.jetpack3.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.ui.favourite.movie.FavoriteMovieViewModel
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
class FavoriteMovieViewModelTest {
    private lateinit var vm: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>
    @Before
    fun setUp(){
        vm = FavoriteMovieViewModel(repo)
    }
    @Test
    fun getFavoriteMovieTest(){
        val dummies = pagedList
        `when`(dummies.size).thenReturn(20)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummies

        `when`(repo.getFavouriteMovie()).thenReturn(movie)
        val entity = vm.getFavoriteMovies().value
        verify(repo).getFavouriteMovie()
        assertNotNull(entity)
        assertEquals(20, entity?.size)
        vm.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummies)
    }

}
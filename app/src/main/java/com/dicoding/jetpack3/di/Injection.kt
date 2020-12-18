package com.dicoding.jetpack3.di

import android.content.Context
import com.dicoding.jetpack3.data.repository.Repository
import com.dicoding.jetpack3.data.source.local.LocalDataSource
import com.dicoding.jetpack3.data.source.local.room.ContentDatabase
import com.dicoding.jetpack3.data.source.remote.RemoteDataSource
import com.dicoding.jetpack3.data.source.remote.request.ApiHelper
import com.dicoding.jetpack3.utils.AppExecutors

object Injection {
    fun provider(context: Context): Repository{
        val remoteDataSource = RemoteDataSource.getInstance(ApiHelper(context))
        val database = ContentDatabase.getDatabase(context)
        val localDataSource = LocalDataSource.getInstance(database.dao())
        val appExecutors = AppExecutors()
        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
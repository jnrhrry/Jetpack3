package com.dicoding.jetpack3.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity

@Database(entities = [MovieEntity::class, SeriesEntity::class], version = 1)
abstract class ContentDatabase : RoomDatabase(){
    abstract fun dao(): ContentDao

    companion object{
        @Volatile
        private var INSTANCE: ContentDatabase?=null
        @JvmStatic
        fun getDatabase(context: Context): ContentDatabase{
            if (INSTANCE == null){
                synchronized(ContentDatabase::class.java){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ContentDatabase::class.java, "movie_db")
                            .build()
                    }
                }
            }
            return INSTANCE as ContentDatabase
        }
    }
}
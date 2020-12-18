package com.dicoding.jetpack3.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.jetpack3.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val nav: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)
        val navigationController = findNavController(R.id.navigation_host)
        val appbarConfig = AppBarConfiguration(setOf(
                R.id.navigation_movies, R.id.navigation_tv_series, R.id.navigation_favourites
        ))
        setupActionBarWithNavController(navigationController, appbarConfig)
        nav.setupWithNavController(navigationController)
    }
}
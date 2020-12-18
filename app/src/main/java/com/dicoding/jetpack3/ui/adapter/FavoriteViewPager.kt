package com.dicoding.jetpack3.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.jetpack3.R
import com.dicoding.jetpack3.ui.favourite.movie.FavoriteMovieFragment
import com.dicoding.jetpack3.ui.favourite.tv.FavoriteSeriesFragment

class FavoriteViewPager (private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    companion object{private val TAB_TITLE = intArrayOf(R.string.movies, R.string.tv_series)}
    override fun getCount(): Int = TAB_TITLE.size

    override fun getItem(position: Int): Fragment = when(position){
        0 -> FavoriteMovieFragment()
        1 -> FavoriteSeriesFragment()
        else -> Fragment()
    }

    override fun getPageTitle(position: Int): CharSequence? = context.getString(TAB_TITLE[position])
}
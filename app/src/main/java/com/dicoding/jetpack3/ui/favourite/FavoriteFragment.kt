package com.dicoding.jetpack3.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.jetpack3.R
import com.dicoding.jetpack3.ui.adapter.FavoriteViewPager
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionPagerAdapter = FavoriteViewPager(requireContext(), childFragmentManager)
        favorite_view_pager.adapter = sectionPagerAdapter
        tab_layout.setupWithViewPager(favorite_view_pager)
    }
}
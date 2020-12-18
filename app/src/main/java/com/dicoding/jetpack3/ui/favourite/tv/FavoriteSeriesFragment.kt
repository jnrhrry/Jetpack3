package com.dicoding.jetpack3.ui.favourite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack3.R
import com.dicoding.jetpack3.ui.adapter.FavoriteSeriesAdapter
import com.dicoding.jetpack3.vm.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_series.*

class FavoriteSeriesFragment : Fragment(){
    private lateinit var vm : FavoriteSeriesViewModel
    private lateinit var adapter: FavoriteSeriesAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_series, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            vm = ViewModelProvider(this, factory)[FavoriteSeriesViewModel::class.java]
            adapter = FavoriteSeriesAdapter()
            favorite_series_progress_bar.visibility = View.VISIBLE
            vm.getFavoritTVSeries().observe(viewLifecycleOwner, Observer {
                favorite_series_progress_bar.visibility = View.GONE
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            })
            favorite_series_rv.layoutManager = LinearLayoutManager(context)
            favorite_series_rv.setHasFixedSize(true)
            favorite_series_rv.adapter = adapter
        }
    }
}
package com.dicoding.jetpack3.ui.favourite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack3.R
import com.dicoding.jetpack3.ui.adapter.FavoriteMovieAdapter
import com.dicoding.jetpack3.vm.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

class FavoriteMovieFragment : Fragment(){
    private lateinit var vm: FavoriteMovieViewModel
    private lateinit var adapter: FavoriteMovieAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            vm = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
            adapter = FavoriteMovieAdapter()
            favorite_movie_progress_bar.visibility = View.VISIBLE
            vm.getFavoriteMovies().observe(viewLifecycleOwner, Observer {
                favorite_movie_progress_bar.visibility = View.GONE
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            })
            favorite_movie_rv.layoutManager = LinearLayoutManager(context)
            favorite_movie_rv.setHasFixedSize(true)
            favorite_movie_rv.adapter = adapter
        }
    }
}
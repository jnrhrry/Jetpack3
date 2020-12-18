package com.dicoding.jetpack3.ui.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack3.R
import com.dicoding.jetpack3.ui.adapter.MovieAdapter
import com.dicoding.jetpack3.vm.ViewModelFactory
import com.dicoding.jetpack3.vo.Status
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val vm = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val adapter = MovieAdapter()
            vm.getMovies().observe(viewLifecycleOwner, Observer { movie ->
                if (movie != null){
                    when(movie.status){
                        Status.LOADING -> movie_progress_bar.visibility = View.VISIBLE
                        Status.ERROR -> {
                            movie_progress_bar.visibility = View.GONE
                            Toast.makeText(context, R.string.salah, Toast.LENGTH_SHORT).show()
                        }
                        Status.SUCCESS -> {
                            movie_progress_bar.visibility = View.GONE
                            adapter.submitList(movie.data)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            })
            with(movie_rv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}
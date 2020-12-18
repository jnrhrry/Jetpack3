package com.dicoding.jetpack3.ui.home.tv

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
import com.dicoding.jetpack3.ui.adapter.SeriesAdapter
import com.dicoding.jetpack3.vm.ViewModelFactory
import com.dicoding.jetpack3.vo.Status
import kotlinx.android.synthetic.main.fragment_series.*

class SeriesFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_series, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val vm = ViewModelProvider(this, factory)[SeriesViewModel::class.java]
            val adapter = SeriesAdapter()
            vm.getTVSeries().observe(viewLifecycleOwner, Observer {
                if(it != null){
                    when(it.status){
                        Status.LOADING -> series_progress_bar.visibility = View.VISIBLE
                        Status.ERROR -> {
                            series_progress_bar.visibility = View.GONE
                            Toast.makeText(context, R.string.salah, Toast.LENGTH_SHORT).show()
                        }
                        Status.SUCCESS ->{
                            series_progress_bar.visibility = View.GONE
                            adapter.submitList(it.data)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            })
            with(series_rv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}
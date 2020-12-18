package com.dicoding.jetpack3.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jetpack3.BuildConfig
import com.dicoding.jetpack3.R
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_card.view.*

class MovieAdapter internal constructor(): PagedListAdapter<MovieEntity, MovieAdapter.MovieHolder>(DIFF_CALLBACK){
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
    inner class MovieHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(movie: MovieEntity){
            with(itemView){
                content_title.text = movie.title
                content_rating.text = movie.rating
                content_release_date.text = movie.release
                Glide.with(context)
                        .load(BuildConfig.IMAGE_URL+movie.imagePath)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.broken_image)
                                        .error(R.drawable.broken_image)
                        )
                        .into(content_poster)
                setOnClickListener {
                    Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_MOVIE, movie.movieId)
                    }.also (context::startActivity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val views = getItem(position)
        if (views != null){
            holder.bind(views)
        }
    }
}
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
import com.dicoding.jetpack3.BuildConfig.IMAGE_URL
import com.dicoding.jetpack3.R
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_card.view.*

class FavoriteMovieAdapter internal constructor(): PagedListAdapter<MovieEntity, FavoriteMovieAdapter.FavoriteMovieHolder>(DIFF_CALLBACK){
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
    inner class FavoriteMovieHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(fav: MovieEntity){
            with(itemView){
                content_title.text = fav.title
                content_rating.text = fav.rating
                content_release_date.text = fav.release
                Glide.with(context)
                        .load(IMAGE_URL+fav.imagePath)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.broken_image)
                                        .error(R.drawable.broken_image)
                        )
                        .into(content_poster)
                setOnClickListener {
                    Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_MOVIE, fav.movieId)
                    }.also (context::startActivity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return FavoriteMovieHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteMovieHolder, position: Int) {
        val favorite = getItem(position)
        if (favorite != null){
            holder.bind(favorite)
        }
    }
}
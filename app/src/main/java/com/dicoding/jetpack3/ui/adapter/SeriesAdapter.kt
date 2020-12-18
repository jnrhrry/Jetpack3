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
import com.dicoding.jetpack3.data.source.model.SeriesEntity
import com.dicoding.jetpack3.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_card.view.*

class SeriesAdapter internal constructor(): PagedListAdapter<SeriesEntity, SeriesAdapter.SeriesHolder>(DIFF_CALLBACK){
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SeriesEntity>(){

            override fun areItemsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }
            override fun areContentsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
    inner class SeriesHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(series: SeriesEntity){
            with(itemView){
                content_title.text = series.title
                content_rating.text = series.rating
                content_release_date.text = series.release

                Glide.with(context)
                        .load(BuildConfig.IMAGE_URL+series.imagePath)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.broken_image)
                                        .error(R.drawable.broken_image)
                        )
                        .into(content_poster)
                setOnClickListener {
                    Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_SERIES, series.tvShowId)
                }.also (context::startActivity)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return SeriesHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesHolder, position: Int) {
        val view = getItem(position)
        if (view != null){
            holder.bind(view)
        }
    }
}
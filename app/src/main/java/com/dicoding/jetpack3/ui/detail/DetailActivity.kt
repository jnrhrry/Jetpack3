package com.dicoding.jetpack3.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jetpack3.BuildConfig.IMAGE_URL
import com.dicoding.jetpack3.R
import com.dicoding.jetpack3.data.source.model.MovieEntity
import com.dicoding.jetpack3.data.source.model.SeriesEntity
import com.dicoding.jetpack3.vm.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var vm: DetailViewModel
    private lateinit var seriesId: String
    private lateinit var movieId: String
    private var menu: Menu?=null

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_SERIES = "extra_series"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        val factory = ViewModelFactory.getInstance(this)
        vm = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val intent = intent.extras
        if (intent != null){
            seriesId = intent.getString(EXTRA_SERIES).toString()
            movieId = intent.getString(EXTRA_MOVIE).toString()
            vm.setMovie(movieId)
            vm.movie.observe(this, Observer {
                if (it != null){
                    detailMovie(it)
                }
            })
            vm.setSeries(seriesId)
            vm.series.observe(this, Observer {
                if(it != null){
                    detailSeries(it)
                }
            })
        }
        val playButton = findViewById<FloatingActionButton>(R.id.play_button)
        playButton.setOnClickListener {
            Snackbar.make(it, "Feature to play this content will be available soon.", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_page, menu)
        this.menu = menu
        vm.movie.observe(this, Observer {
            if (it != null){
                val state = it.favorit
                Log.d("State: ", state.toString())
                favorite(state)
            }
        })
        vm.series.observe(this, Observer{
            if (it != null){
                val stated = it.favorit
                Log.d("State: ", stated.toString())
                favorite(stated)
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite_action){
            vm.setFavoriteMovie()
            Log.d("movieId: ", movieId)
            vm.setFavoriteSeries()
            Log.d("seriesId: ", seriesId)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun detailMovie(movie: MovieEntity){
        supportActionBar?.title = "Movie"
        content_detail_name.title = movie.title
        content_rating.text = movie.rating
        content_release_date.text = movie.release
        content_overview.text = movie.description
        Glide.with(this)
                .load(IMAGE_URL+movie.imagePath)
                .apply(
                        RequestOptions.placeholderOf(R.drawable.broken_image)
                                .error(R.drawable.broken_image))
                .into(detail_image)
    }
    private fun detailSeries(series: SeriesEntity){
        supportActionBar?.title = "TV Series"
        content_detail_name.title = series.title
        content_rating.text = series.rating
        content_release_date.text = series.release
        content_overview.text = series.description
        Glide.with(this)
                .load(IMAGE_URL+series.imagePath)
                .apply(
                        RequestOptions.placeholderOf(R.drawable.broken_image)
                                .error(R.drawable.broken_image))
                .into(detail_image)
    }
    private fun favorite(state: Boolean){
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.favorite_action)
        if (state){
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.favourite_filled)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.favourite_border)
        }
    }
}
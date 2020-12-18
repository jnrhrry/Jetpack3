package com.dicoding.jetpack3.data.source.remote.request

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dicoding.jetpack3.BuildConfig.API_KEY
import com.dicoding.jetpack3.data.source.remote.response.MovieResponse
import com.dicoding.jetpack3.data.source.remote.response.SeriesResponse
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class ApiHelper (private val context: Context){
    private fun parseJsonToArrayListMovies(responseBody: String): List<MovieResponse>{
        val movies = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(responseBody)
            val list = responseObject.getJSONArray("results")
            for (i in 0 until list.length()){
                val movie = list.getJSONObject(i)
                val id = movie.getInt("id").toString()
                val title = movie.getString("title")
                val release = movie.getString("release_date")
                val rating = movie.getDouble("vote_average").toString()
                val description = movie.getString("overview")
                val image = movie.getString("poster_path")
                print(title)
                val movieResponse = MovieResponse(id,title,release,rating,description,image)
                movies.add(movieResponse)
            }
        }catch (e: Exception){
            Log.d("Exception: ", e.message.toString())
        }
        return movies
    }

    fun getMovies(): MutableLiveData<ArrayList<MovieResponse>>{
        val listMovies = MutableLiveData<ArrayList<MovieResponse>>()
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=$API_KEY&language=en-US"
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler(){

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val movieResponse: List<MovieResponse> = parseJsonToArrayListMovies(String(responseBody!!))
                listMovies.postValue(movieResponse as ArrayList<MovieResponse>?)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure due to: ", error?.message.toString())
            }
        })
        return listMovies
    }

    private fun parseJsonToArrayListTv(responseBody: String): List<SeriesResponse>{
        val series = ArrayList<SeriesResponse>()
        try {
            val responseObject = JSONObject(responseBody)
            val list = responseObject.getJSONArray("results")
            for (i in 0 until list.length()){
                val tv = list.getJSONObject(i)
                val id = tv.getInt("id").toString()
                val title = tv.getString("name")
                val release = tv.getString("first_air_date")
                val rating = tv.getDouble("vote_average").toString()
                val description = tv.getString("overview")
                val image = tv.getString("poster_path")
                print(title)
                val tvShowResponse = SeriesResponse(id,title,release,rating,description,image)
                series.add(tvShowResponse)
            }
        }catch (e: Exception){
            Log.d("Exception", e.message.toString())
        }
        return series
    }

    fun getTVSeries(): MutableLiveData<ArrayList<SeriesResponse>> {
        val listTv = MutableLiveData <ArrayList<SeriesResponse>>()
        val url = "https://api.themoviedb.org/3/discover/tv?api_key=$API_KEY&language=en-US"

        val client = AsyncHttpClient()
        client.get(url , object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val tvShowResponse: List<SeriesResponse> = parseJsonToArrayListTv(String(responseBody!!))
                listTv.postValue(tvShowResponse as ArrayList<SeriesResponse>?)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }

        })
        return listTv
    }
}
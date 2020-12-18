package com.dicoding.jetpack3.data.source.remote.response

class ApiResponse <T>(val status: Response, val body: T, val message: String?){
    companion object{
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(Response.SUCCESS, body, null)
        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(Response.ERROR, body, msg)
    }
}
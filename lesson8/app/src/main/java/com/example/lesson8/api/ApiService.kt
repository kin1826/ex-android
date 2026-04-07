package com.example.lesson8.api

import com.example.lesson8.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

}
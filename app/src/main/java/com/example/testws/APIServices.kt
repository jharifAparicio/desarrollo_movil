package com.example.testws

import retrofit2.Call
import retrofit2.http.GET

interface APIServices {
    @GET("albums")
    fun getAlbums(): Call<List<AlbumData>>
    @GET("comments")
    fun getComments(): Call<List<ComentsData>>
    @GET("users")
    fun getUsers(): Call<List<UsersData>>

}
package com.hectorfortuna.nytbooks.data.response

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {
    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "2v6HvM20bplupBpGnHfB1TVGvlyYc9KG",
        @Query("list") list: String = "hardcover-fiction",

    ): Call<BookBodyResponse>
}
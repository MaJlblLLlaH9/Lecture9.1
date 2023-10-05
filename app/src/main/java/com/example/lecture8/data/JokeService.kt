package com.example.lecture8.data

import com.example.lecture8.JokeDataModel
import com.example.lecture8.core.Mapper
import retrofit2.Call
import retrofit2.http.GET

interface JokeService<T : Mapper<JokeDataModel>> {
    fun getJoke(): Call<T>
}

interface BaseJokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke(): Call<JokeServerModel>

}

interface NewJokeService {
    @GET("https://v2.jokeapi.dev/joke/Any")
    fun getJoke(): Call<NewJokeServerModel>
}


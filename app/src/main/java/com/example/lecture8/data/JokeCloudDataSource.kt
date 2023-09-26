package com.example.lecture8.data

import retrofit2.Call

class JokeCloudDataSource(private val service: BaseJokeService) :
    BaseCloudDataSource<JokeServerModel>() {
    override fun getJokeServerModel(): Call<JokeServerModel> = service.getJoke()
}
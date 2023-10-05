package com.example.lecture8.data

import retrofit2.Call

class NewJokeCloudDataSource(private val service: NewJokeService) :
    BaseCloudDataSource<NewJokeServerModel>() {
    override fun getJokeServerModel(): Call<NewJokeServerModel> = service.getJoke()

}
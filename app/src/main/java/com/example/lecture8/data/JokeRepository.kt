package com.example.lecture8.data

import com.example.lecture8.JokeDataModel


interface JokeRepository {

    fun chooseDataSource(cached: Boolean)

    suspend fun changeJokeStatus(): JokeDataModel

    suspend fun getJoke(): JokeDataModel


}
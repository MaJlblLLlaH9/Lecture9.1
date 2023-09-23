package com.example.lecture8


interface JokeDataFetcher {
    suspend fun getJoke(): JokeDataModel
}
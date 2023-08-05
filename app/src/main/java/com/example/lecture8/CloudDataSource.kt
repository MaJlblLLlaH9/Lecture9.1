package com.example.lecture8

interface CloudDataSource {
    suspend fun getJoke(): Result<JokeServerModel, ErrorType>
}
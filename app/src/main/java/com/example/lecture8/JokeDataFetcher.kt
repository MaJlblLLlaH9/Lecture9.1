package com.example.lecture8

interface JokeDataFetcher<S, E> {
    suspend fun getJoke(): Result<S, E>
}
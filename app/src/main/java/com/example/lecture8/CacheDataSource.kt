package com.example.lecture8

interface CacheDataSource : ChangeJokeStatus {
    suspend fun getJoke(): Result<Joke, Unit>
}

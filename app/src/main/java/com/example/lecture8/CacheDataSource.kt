package com.example.lecture8

interface CacheDataSource {
    fun getJoke(jokeCacheCallback: JokeCacheCallback)
    fun addOrRemove(id: Int, joke: JokeServerModel): Joke
}
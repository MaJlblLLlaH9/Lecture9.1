package com.example.lecture8

interface JokeCacheCallback {
    fun provide(jokeServerModel: Joke)
    fun fail()
}
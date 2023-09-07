package com.example.lecture8

interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: Joke)
    fun clear()
}
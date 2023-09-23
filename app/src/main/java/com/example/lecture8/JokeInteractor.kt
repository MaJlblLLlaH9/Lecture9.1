package com.example.lecture8

import com.example.lecture8.domain.Joke

interface JokeInteractor {
    suspend fun getJoke(): Joke

    suspend fun changeFavorites(): Joke

    fun getFavoriteJokes(favorites: Boolean)
}
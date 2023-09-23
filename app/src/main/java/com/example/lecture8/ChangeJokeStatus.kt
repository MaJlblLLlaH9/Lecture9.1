package com.example.lecture8

import com.example.lecture8.domain.Joke

interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel
}
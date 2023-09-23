package com.example.lecture8.domain

import com.example.lecture8.*
import com.example.lecture8.core.Mapper

sealed class Joke : Mapper<JokeUiModel> {
    class Success(
        val text: String,
        val punchline: String,
        val favorite: Boolean
    ) : Joke() {
        override fun to(): JokeUiModel {
            return if (favorite)
                FavoriteJokeUiModel(text, punchline)
            else BaseJokeUiModel(text, punchline)
        }
    }

    class Failed(private val failure: JokeFailure) : Joke() {
        override fun to(): JokeUiModel {
            return FailedJokeUiModel(failure.getMessage())
        }

    }

}
package com.example.lecture8

import com.example.lecture8.core.Mapper
import com.example.lecture8.domain.Joke

interface JokeDataModelMapper<T> {
    fun map(id: Int, text: String, punchline: String, cached: Boolean): T
}

class JokeSuccessMapper : JokeDataModelMapper<Joke.Success> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean): Joke.Success = (
            Joke.Success(text, punchline, cached)
            )
}

class JokeRealmMapper : JokeDataModelMapper<JokeRealmModel> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean): JokeRealmModel {
        return JokeRealmModel().also { joke ->
            joke.id = id
            joke.text = text
            joke.punchline = punchline
        }
    }
}

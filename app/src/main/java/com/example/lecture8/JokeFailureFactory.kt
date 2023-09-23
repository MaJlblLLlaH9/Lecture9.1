package com.example.lecture8

import com.example.lecture8.domain.NoCachedException
import com.example.lecture8.domain.NoConnectionException
import com.example.lecture8.domain.ServiceUnavailableException

class JokeFailureFactory(private val resourceManager: ResourceManager) : JokeFailureHandler {
    override fun handle(e: Exception): JokeFailure {
        return when (e) {
            is NoConnectionException -> NoConnection(resourceManager)
            is NoCachedException -> NoCachedJokes(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else -> GenericError(resourceManager)
        }
    }
}

interface JokeFailureHandler {
    fun handle(e: Exception): JokeFailure
}
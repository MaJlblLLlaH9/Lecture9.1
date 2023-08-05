package com.example.lecture8

interface JokeCloudCallback {

    fun provide(data: Joke)

    fun fail(type: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}

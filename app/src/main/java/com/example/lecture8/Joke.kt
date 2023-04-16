package com.example.lecture8

class Joke(private val text: String, private val punchline: String) {
    fun getJokeUi() = "$text\n$punchline"
}

interface JokeFailure {
    fun getMessage(): String
}

class NoConnection(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage() = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.service_unavailable)
}


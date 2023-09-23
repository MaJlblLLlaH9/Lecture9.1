package com.example.lecture8

class GenericError(private val resourceManager: ResourceManager):JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.generic_fail_message)
}
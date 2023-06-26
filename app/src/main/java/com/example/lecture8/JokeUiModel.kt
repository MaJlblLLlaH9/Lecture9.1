package com.example.lecture8

import androidx.annotation.DrawableRes

abstract class JokeUiModel(private val text: String, private val punchline: String) {
    private fun text() = "$text\n$punchline"

    @DrawableRes
    abstract fun getIconResId(): Int
    fun map(callback: DataCallback) = callback.run {
        provideText(text())
        provideIconRes(getIconResId())
    }
}

class BaseJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId(): Int = R.drawable.ic_baseline_favorite_border_24
}

class FavoriteJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId(): Int = R.drawable.ic_baseline_favorite_24
}

class FailedJokeUiModel(text: String) : JokeUiModel(text, "") {
    override fun getIconResId(): Int = 0
}

interface JokeFailure {
    fun getMessage(): String
}

class NoCachedJokes(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.no_cached_jokes)
}

class NoConnection(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage() = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.service_unavailable)
}


package com.example.lecture8

import androidx.annotation.DrawableRes

abstract class JokeUiModel(private val text: String, private val punchline: String) {
    protected open fun text() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId(): Int
    open fun show(communication: Communication) =
        communication.showState(BaseViewModel.State.Initial(text(), getIconResId()))
}

class BaseJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId(): Int = R.drawable.ic_baseline_favorite_border_24
}

class FavoriteJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId(): Int = R.drawable.ic_baseline_favorite_24
}

class FailedJokeUiModel(private val text: String) : JokeUiModel(text, "") {
    override fun text() = text
    override fun getIconResId(): Int = 0
    override fun show(communication: Communication) =
        communication.showState(BaseViewModel.State.Failed(text(), getIconResId()))
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
    override fun getMessage() = resourceManager.getString(R.string.no_connection)
}

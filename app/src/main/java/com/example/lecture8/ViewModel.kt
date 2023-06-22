package com.example.lecture8

import androidx.annotation.DrawableRes

class ViewModel(private val model: Model) {

    private var dataCallback: DataCallback? = null

    private var jokeCallback = object : JokeCallback {
        override fun provide(joke: Joke) {
            dataCallback?.let {
                joke.map(it)
            }
        }
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }

    fun init(callback: DataCallback) {
        dataCallback = callback
        model.init(jokeCallback)
    }

    fun changeJokeStatus() {
        model.changeJokeStatus(jokeCallback)
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        dataCallback = null
        model.clear()
    }
}

interface DataCallback {
    fun provideText(text: String)

    fun provideIconRes(@DrawableRes id: Int)

}

interface Model {

    fun chooseDataSource(cached: Boolean)

    fun changeJokeStatus(jokeCallback: JokeCallback)

    fun getJoke()

    fun init(callback: JokeCallback)

    fun clear()
}

interface JokeCallback {

    fun provide(joke: Joke)
}

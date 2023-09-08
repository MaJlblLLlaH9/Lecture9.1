package com.example.lecture8

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(private val model: Model) : ViewModel() {

    private var dataCallback: DataCallback? = null

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }

    fun init(callback: DataCallback) {
        dataCallback = callback
    }

    fun changeJokeStatus() = viewModelScope.launch {
        val uiModel = model.changeJokeStatus()
        dataCallback?.let { uiModel?.map(it) }
    }

    fun getJoke() = viewModelScope.launch {
        val uiModel = model.getJoke()
        dataCallback?.let { uiModel.map(it) }
    }

}

interface DataCallback {
    fun provideText(text: String)

    fun provideIconRes(@DrawableRes id: Int)

}

interface Model {

    fun chooseDataSource(cached: Boolean)

    suspend fun changeJokeStatus(): JokeUiModel?

    suspend fun getJoke(): JokeUiModel


}

interface JokeCallback {

    fun provide(joke: JokeUiModel)
}

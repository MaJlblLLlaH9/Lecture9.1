package com.example.lecture8

import androidx.annotation.DrawableRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BaseViewModel(
    private val model: Model,
    private val communication: Communication
) :
    ViewModel() {


    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }


    fun changeJokeStatus() = viewModelScope.launch {
        model.changeJokeStatus()?.let {
          it.show(communication)
        }
    }

    fun getJoke() = viewModelScope.launch {
        model.getJoke().show(communication)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) =
        communication.observe(owner, observer)
}

interface Model {

    fun chooseDataSource(cached: Boolean)

    suspend fun changeJokeStatus(): JokeUiModel?

    suspend fun getJoke(): JokeUiModel

}


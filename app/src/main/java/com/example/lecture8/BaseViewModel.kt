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
        communication.showState(State.Progress)
        model.getJoke().show(communication)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>) =
        communication.observe(owner, observer)

    interface Show<T> {
        fun show(arg: T)
    }

    interface ShowText : Show<String> {
        override fun show(text: String)
    }

    interface ShowImage : Show<Int> {
        override fun show(@DrawableRes id: Int)
    }

    interface ShowView : Show<Boolean> {
        override fun show(boolean: Boolean)
    }

    interface EnableView {
        fun enable(enable: Boolean)
    }

    sealed class State {
        fun showView(
            progress: ShowView,
            button: EnableView,
            textView: ShowText,
            imageButton: ShowImage
        ) {
            show(progress, button)
            show(textView, imageButton)
        }

        protected open fun show(progress: ShowView, button: EnableView) {}
        protected open fun show(textView: ShowText, imageButton: ShowImage) {}

        object Progress : State() {
            override fun show(progress: ShowView, button: EnableView) {
                progress.show(true)
                button.enable(false)
            }
        }

        data class Initial(val text: String, @DrawableRes val id: Int) : State() {
            override fun show(progress: ShowView, button: EnableView) {
                progress.show(false)
                button.enable(true)
            }

            override fun show(textView: ShowText, imageButton: ShowImage) {
                textView.show(text)
                imageButton.show(id)
            }
        }
    }
}

interface Model {

    fun chooseDataSource(cached: Boolean)

    suspend fun changeJokeStatus(): JokeUiModel?

    suspend fun getJoke(): JokeUiModel

}


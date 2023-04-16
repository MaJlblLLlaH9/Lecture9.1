package com.example.lecture8

class ViewModel(private val model: Model) {

    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback
        model.init(object : ResultCallback {
            override fun provideSuccess(data: Joke) = callback.provideText(data.getJokeUi())
            override fun provideError(error: JokeFailure) = callback.provideText(error.getMessage())
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        callback = null
        model.clear()
    }
}

interface TextCallback {
    fun provideText(text: String)
}

interface Model {

    fun getJoke()

    fun init(callback: ResultCallback)

    fun clear()
}

interface ResultCallback {

    fun provideSuccess(data: Joke)

    fun provideError(error: JokeFailure)
}

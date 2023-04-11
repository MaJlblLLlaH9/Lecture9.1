package com.example.lecture8

class ViewModel(private val model: Model<Any, Any>) {

    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback
        model.init(object : ResultCallback<Any, Any> {
            override fun provideSuccess(data: Any) {
                callback.provideText("Success")
            }

            override fun provideError(error: Any) {
                callback.provideText("Error")
            }
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

interface Model<S, E> {

    fun getJoke()

    fun init(callback: ResultCallback<S, E>)
    fun clear()
}

interface ResultCallback<S, E> {

    fun provideSuccess(data: S)
    fun provideError(error: E)
}

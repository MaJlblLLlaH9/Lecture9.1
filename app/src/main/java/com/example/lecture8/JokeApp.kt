package com.example.lecture8

import android.app.Application

class JokeApp : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(TestModel())
    }
}

class TestModel : Model<Any, Any> {
    private var callback: ResultCallback<Any, Any>? = null

    private var count = 1

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            if (count % 2 == 0)
                callback?.provideSuccess("success")
            else
                callback?.provideError("error")
            count++
        }.start()
    }

    override fun init(callback: ResultCallback<Any, Any>) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}
package com.example.lecture8

import android.app.Application
import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException

class JokeApp : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(BaseModel(BaseJokeService(Gson()), BaseResourceManager(this)))
    }
}

//class TestModel(resourceManager: ResourceManager) : Model {
//    private var callback: ResultCallback? = null
//    private var count = 0
//    private val noConnection = NoConnection(resourceManager)
//    private val serviceUnavailable = ServiceUnavailable(resourceManager)
//
//    override fun getJoke() {
//        Thread {
//            Thread.sleep(1000)
//            when (count) {
//                0 -> callback?.provideSuccess(Joke("testText", "testPunchline"))
//                1 -> callback?.provideError(noConnection)
//                2 -> callback?.provideError(serviceUnavailable)
//            }
//            count++
//            if (count == 3) count = 0
//        }.start()
//    }
//
//    override fun init(callback: ResultCallback) {
//        this.callback = callback
//    }
//
//    override fun clear() {
//        callback = null
//    }
//
//}

class BaseJokeService(private val gson: Gson) : JokeService {
    override fun getJoke(callback: ServiceCallback) {
        Thread {
            var connection: HttpURLConnection? = null
            try {
                var url = URL(JOKE_URL)
                connection = url.openConnection() as HttpURLConnection
                InputStreamReader(BufferedInputStream(connection.inputStream)).use {
                    val line: String = it.readText()
                    val dto = gson.fromJson(line, JokeDTO::class.java)
                    callback.returnSuccess(dto)
                }
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    callback.returnError(ErrorType.NO_CONNECTION)
                else
                    callback.returnError(ErrorType.OTHER)
            } finally {
                connection?.disconnect()
            }
        }.start()
    }

    private companion object {
        const val JOKE_URL = "https://official-joke-api.appspot.com/random_joke"
    }
}


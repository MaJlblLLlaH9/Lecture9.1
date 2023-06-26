package com.example.lecture8

import android.app.Application
import com.google.gson.Gson
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException

class JokeApp : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder().baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viewModel = ViewModel(
            BaseModel(
                BaseCachedDataSource(Realm.getDefaultInstance()),
                BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                BaseResourceManager(this)
            )
        )
    }
}

//class BaseJokeService(private val gson: Gson) : JokeService {
//    override fun getJoke(callback: JokeCloudCallback) {
//        Thread {
//            var connection: HttpURLConnection? = null
//            try {
//                var url = URL(JOKE_URL)
//                connection = url.openConnection() as HttpURLConnection
//                InputStreamReader(BufferedInputStream(connection.inputStream)).use {
//                    val line: String = it.readText()
//                    val dto = gson.fromJson(line, JokeServerModel::class.java)
//                    callback.provide(dto)
//                }
//            } catch (e: Exception) {
//                if (e is UnknownHostException)
//                    callback.fail(ErrorType.NO_CONNECTION)
//                else
//                    callback.fail(ErrorType.SERVICE_UNAVAILABLE)
//            } finally {
//                connection?.disconnect()
//            }
//        }.start()
//    }
//
//    private companion object {
//        const val JOKE_URL = "https://official-joke-api.appspot.com/random_joke"
//    }
//}


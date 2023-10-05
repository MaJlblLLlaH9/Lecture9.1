package com.example.lecture8

import android.app.Application
import com.example.lecture8.data.*
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class JokeApp : Application() {

    lateinit var viewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder().baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cacheDataSource = BaseCachedDataSource(BaseRealmProvider(), JokeRealmMapper())
        val cloudDataSource = NewJokeCloudDataSource(retrofit.create(NewJokeService::class.java))
        val resourceManager = BaseResourceManager(this)
        val repository = BaseJokeRepository(cacheDataSource, cloudDataSource, BaseCachedJoke())
        val interactor =
            BaseJokeInteractor(repository, JokeFailureFactory(resourceManager), JokeSuccessMapper())
        viewModel = BaseViewModel(interactor, BaseCommunication())
    }
}



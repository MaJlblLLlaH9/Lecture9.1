package com.example.lecture8.data

import com.example.lecture8.JokeDataModel
import com.example.lecture8.core.Mapper
import com.example.lecture8.domain.NoConnectionException
import com.example.lecture8.domain.ServiceUnavailableException
import retrofit2.Call
import java.lang.Exception
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T:Mapper<JokeDataModel>>: CloudDataSource {

    protected abstract fun getJokeServerModel(): Call<T>
    override suspend fun getJoke(): JokeDataModel {
        try {
            return getJokeServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException)
                throw NoConnectionException()
            else throw ServiceUnavailableException()
        }
    }
}
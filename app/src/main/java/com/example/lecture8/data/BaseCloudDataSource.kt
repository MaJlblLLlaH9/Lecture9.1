package com.example.lecture8.data

import com.example.lecture8.JokeDataModel
import com.example.lecture8.domain.NoConnectionException
import com.example.lecture8.domain.ServiceUnavailableException
import java.lang.Exception
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService) : CloudDataSource {

    override suspend fun getJoke(): JokeDataModel {
        return try {
            return service.getJoke().to()
        } catch (e: Exception) {
            if (e is UnknownHostException)
                throw NoConnectionException()
            else throw ServiceUnavailableException()
        }
    }
}
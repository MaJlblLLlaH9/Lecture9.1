package com.example.lecture8

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)
}
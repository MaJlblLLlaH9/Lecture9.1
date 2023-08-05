//package com.example.lecture8
//
//class TestCloudDataSource : CloudDataSource {
//    private var count = 0
//    override fun getJoke(callback: JokeCloudCallback) {
//        val joke = Joke(count, "testType", "testText$count", "TestPunchline$count")
//        callback.provide(joke)
//        count++
//    }
//}
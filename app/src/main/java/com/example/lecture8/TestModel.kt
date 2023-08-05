//package com.example.lecture8
//
//class TestModel(resourceManager: ResourceManager) : Model {
//    private var callback: JokeCallback? = null
//    private var count = 0
//    private val noConnection = NoConnection(resourceManager)
//    private val serviceUnavailable = ServiceUnavailable(resourceManager)
//    private var getJokeFromCache = false
//
//    override fun chooseDataSource(cached: Boolean) {
//        getJokeFromCache = cached
//    }
//
//    override fun changeJokeStatus(jokeCallback: JokeCallback) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getJoke() {
//        Thread {
//            Thread.sleep(1000)
//            when (count) {
//                0 -> callback?.provide(BaseJokeUiModel("testText", "testPunchline"))
//                1 -> callback?.provide(FavoriteJokeUiModel("FavoriteJokeText", "FavoriteJokePunchline"))
//                2 -> callback?.provide(FailedJokeUiModel(serviceUnavailable.getMessage()))
//            }
//            count++
//            if (count == 3) count = 0
//        }.start()
//    }
//
//    override fun init(callback: JokeCallback) {
//        this.callback = callback
//    }
//
//    override fun clear() {
//        callback = null
//    }
//
//}
package com.example.lecture8

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourceManager: ResourceManager
) :
    Model {
    private var jokeCallback: JokeCallback? = null
    private val noCachedJokes by lazy { NoCachedJokes(resourceManager) }
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    private var getJokeFromCache = false

    private var cachedJokeServerModel: JokeServerModel? = null

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJokeServerModel?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }

    override fun getJoke() {
        if (getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCacheCallback {
                override fun provide(jokeServerModel: JokeServerModel) {
                    jokeCallback?.provide(jokeServerModel.toFavoriteJoke())
                }

                override fun fail() {
                    jokeCallback?.provide(FailedJoke(noCachedJokes.getMessage()))
                }

            })
        } else {
            cloudDataSource.getJoke(object : JokeCloudCallback {
                override fun provide(data: JokeServerModel) {
                    cachedJokeServerModel = data
                    jokeCallback?.provide(data.toBaseJoke())
                }

                override fun fail(type: ErrorType) {
                    cachedJokeServerModel = null
                    val failure =
                        if (type == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJoke(failure.getMessage()))
                }

            })
        }
    }

    override fun init(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun clear() {
        jokeCallback = null
    }

}
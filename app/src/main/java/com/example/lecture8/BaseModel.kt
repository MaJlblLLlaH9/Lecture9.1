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

    private var cachedJoke: Joke? = null

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJoke?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }

    override fun getJoke() {
        if (getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCacheCallback {
                override fun provide(joke: Joke) {
                    jokeCallback?.provide(joke.toFavoriteJoke())
                }

                override fun fail() {
                    jokeCallback?.provide(FailedJokeUiModel(noCachedJokes.getMessage()))
                }

            })
        } else {
            cloudDataSource.getJoke(object : JokeCloudCallback {
                override fun provide(data: Joke) {
                    cachedJoke = data
                    jokeCallback?.provide(data.toBaseJoke())
                }

                override fun fail(type: ErrorType) {
                    cachedJoke = null
                    val failure =
                        if (type == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJokeUiModel(failure.getMessage()))
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
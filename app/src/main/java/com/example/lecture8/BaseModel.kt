package com.example.lecture8

class BaseModel(
    private val service: JokeService,
    private val resourceManager: ResourceManager
) :
    Model {
    private var callback: ResultCallback? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }

    override fun getJoke() {
        service.getJoke((object : ServiceCallback {
            override fun returnSuccess(data: JokeDTO) {
                callback?.provideSuccess(data.toJoke())
            }

            override fun returnError(type: ErrorType) {
                when (type) {
                    ErrorType.NO_CONNECTION -> callback?.provideError(noConnection)
                    ErrorType.OTHER -> callback?.provideError(serviceUnavailable)
                }
            }
        }))
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}
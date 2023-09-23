package com.example.lecture8

class JokeDataModel(
    private val id: Int,
    val text: String,
    val punchline: String,
    val cached: Boolean = false
) : ChangeJoke {
    override suspend fun change(changeJokeStatus: ChangeJokeStatus) =
        changeJokeStatus.addOrRemove(id, this)

    fun <T> map(mapper: JokeDataModelMapper<T>): T {
        return mapper.map(id, text, punchline, cached)
    }

    fun changeCached(cached: Boolean) = JokeDataModel(id, text, punchline, cached)
}
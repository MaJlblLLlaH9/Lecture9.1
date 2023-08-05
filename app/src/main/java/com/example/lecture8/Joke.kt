package com.example.lecture8

class Joke(
    private val id: Int,
    private val type: String,
    private val text: String,
    private val punchline: String
) {
    fun toBaseJoke() = BaseJokeUiModel(text, punchline)
    fun toFavoriteJoke() = FavoriteJokeUiModel(text, punchline)
    fun toJokeRealm(): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.type = type
            it.punchline = punchline
            it.text = text
        }
    }

    suspend fun change(cacheDataSource: ChangeJokeStatus) = cacheDataSource.addOrRemove(id, this)
}
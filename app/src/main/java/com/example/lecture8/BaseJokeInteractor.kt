package com.example.lecture8

import com.example.lecture8.data.JokeRepository
import com.example.lecture8.domain.Joke
import com.example.lecture8.domain.NoCachedException
import com.example.lecture8.domain.NoConnectionException
import com.example.lecture8.domain.ServiceUnavailableException

class BaseJokeInteractor(
    private val repository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler,
    private val mapper: JokeDataModelMapper<Joke.Success>
) : JokeInteractor {
    override suspend fun getJoke(): Joke {
        return try {
            repository.getJoke().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavorites(): Joke {
        return try {
            repository.changeJokeStatus().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override fun getFavoriteJokes(favorites: Boolean) {
        repository.chooseDataSource(favorites)
    }
}
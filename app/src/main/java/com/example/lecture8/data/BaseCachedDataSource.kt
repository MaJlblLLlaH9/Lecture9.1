package com.example.lecture8.data

import com.example.lecture8.*
import com.example.lecture8.domain.NoCachedException
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCachedDataSource(
    private val realmProvider: RealmProvider,
    private val mapper: JokeDataModelMapper<JokeRealmModel>
) : CacheDataSource {

    override suspend fun getJoke(): JokeDataModel {
        realmProvider.provide().use {
            val jokes = it.where(JokeRealmModel::class.java).findAll()
            if (jokes.isEmpty())
                throw NoCachedException()
            else
                return jokes.random().to()
        }
    }

    override suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel =
        withContext(Dispatchers.IO) {
            Realm.getDefaultInstance().use {
                val jokeRealm = it.where(JokeRealmModel::class.java).equalTo("id", id).findFirst()
                return@withContext if (jokeRealm == null) {
                    it.executeTransaction { transaction ->
                        val newJoke = joke.map(mapper)
                        transaction.insert(newJoke)
                    }
                    joke.changeCached(true)
                } else {
                    it.executeTransaction {
                        jokeRealm.deleteFromRealm()
                    }
                    joke.changeCached(false)
                }
            }
        }

}
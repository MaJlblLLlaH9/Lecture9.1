package com.example.lecture8.data

import com.example.lecture8.ChangeJokeStatus
import com.example.lecture8.JokeDataFetcher

interface CacheDataSource : JokeDataFetcher, ChangeJokeStatus

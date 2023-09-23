package com.example.lecture8.domain

import io.realm.internal.IOException

class NoConnectionException : IOException()
class ServiceUnavailableException : IOException()
class NoCachedException : IOException()
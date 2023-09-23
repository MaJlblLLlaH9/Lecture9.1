package com.example.lecture8.data

import com.example.lecture8.JokeDataModel
import com.example.lecture8.core.Mapper
import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,

    @SerializedName("setup")
    private val text: String,

    @SerializedName("punchline")
    private val punchline: String
) : Mapper<JokeDataModel> {

    override fun to() = JokeDataModel(id, text, punchline)
}
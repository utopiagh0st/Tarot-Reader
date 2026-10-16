package com.example.tarot_reader.data.remote

import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int
)
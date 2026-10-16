package com.example.tarot_reader.data.remote

import com.google.gson.annotations.SerializedName

data class PokemonDetailDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("height") val height: Int?,
    @SerializedName("weight") val weight: Int?,
    @SerializedName("base_experience") val baseExperience: Int?,
    @SerializedName("types") val types: List<PokemonTypeSlotDto>?
)

data class PokemonTypeSlotDto(
    @SerializedName("slot") val slot: Int?,
    @SerializedName("type") val type: PokemonTypeDto?
)

data class PokemonTypeDto(
    @SerializedName("name") val name: String?
)

data class PokemonListResponseDto(
    @SerializedName("count") val count: Int?,
    @SerializedName("results") val results: List<PokemonNamedItemDto>?
)

data class PokemonNamedItemDto(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)

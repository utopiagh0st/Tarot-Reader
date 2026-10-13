package com.example.tarot_reader.data.remote

import com.google.gson.annotations.SerializedName

data class TarotCardDto(
    @SerializedName("name") val name: String?,
    @SerializedName("name_short") val nameShort: String?,
    @SerializedName("value") val value: String?,
    @SerializedName("value_int") val valueInt: Int?,
    @SerializedName("suit") val suit: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("meaning_up") val meaningUp: String?,
    @SerializedName("meaning_rev") val meaningRev: String?,
    @SerializedName("desc") val desc: String?
)

data class TarotResponseDto(
    @SerializedName("nhits") val nhits: Int?,
    @SerializedName("cards") val cards: List<TarotCardDto>?
)

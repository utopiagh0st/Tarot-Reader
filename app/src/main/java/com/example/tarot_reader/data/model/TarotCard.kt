package com.example.tarot_reader.data.model

data class TarotCard(
    val name: String,
    val name_short: String,
    val value: String,
    val value_int: Int,
    val suit: String?, // null for Major Arcana
    val type: String,
    val meaning_up: String,
    val meaning_rev: String,
    val desc: String
) {
    override fun toString(): String {
        return "TarotCard(name='$name', name_short='$name_short', value='$value', value_int=$value_int, suit=$suit, type='$type', meaning_up='$meaning_up', meaning_rev='$meaning_rev', desc='$desc')"
    }
}

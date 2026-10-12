package com.example.tarot_reader.data.model

class TarotCard {
    val name: String
    val name_short: String
    val value: String
    val value_int: Int
    val suit: String?
    val type: String
    val meaning_up: String
    val meaning_rev: String
    val desc: String
    
    override fun toString(): String
}
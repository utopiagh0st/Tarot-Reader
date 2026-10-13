package com.example.tarot_reader.utils

data class TarotError(
    val message: String,
    val code: Int? = null
) {
    @JvmName("getMessageCustom")
    fun getMessage(): String {
        return message
    }
}

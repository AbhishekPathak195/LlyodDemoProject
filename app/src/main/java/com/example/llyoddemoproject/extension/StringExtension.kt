package com.example.llyoddemoproject.extension

class StringExtension {
    fun String.capitalizeFirstChar(): String {
        return replaceFirstChar {
            it.uppercaseChar()
        }
    }
}
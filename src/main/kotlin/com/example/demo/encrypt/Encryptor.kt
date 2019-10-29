package com.example.demo.encrypt

interface Encryptor {

    fun encrypt(value: String): String

    fun getAbout(): String

    fun getName(): String
}
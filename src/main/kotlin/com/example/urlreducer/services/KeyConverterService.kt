package com.example.urlreducer.services


interface KeyConverterService {
    fun idToKey(id: Long): String
    fun keyToId(key: String): Long
}
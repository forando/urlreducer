package com.example.urlreducer.services

import org.springframework.stereotype.Service

@Service
class DefaultKeyConverterService: KeyConverterService{

    val chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM-_".toCharArray()
    val charToLong = (0..chars.size - 1).map { Pair(chars[it], it.toLong()) }.toMap()

    override fun idToKey(id: Long): String {
        var n = id
        val builder = StringBuilder()
        while (n != 0L){
            builder.append(chars[(n % chars.size).toInt()])
            n /= chars.size
        }
        return builder.reverse().toString()
    }

    override fun keyToId(key: String) = key.map { charToLong[it]!! }.fold(0L, {a, b -> a* chars.size + b})

}

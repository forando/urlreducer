package com.example.urlreducer.services


interface KeyMapperService {

    /**
     * Marker Interface. This is just a container for data classes
     */
    interface GET {
        data class Link(val link: String): GET
        data class NotFound(val key: String): GET
    }

    fun add(link: String): String
    fun getLink(key: String): GET

}
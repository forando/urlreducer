package com.example.urlreducer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class UrlReducerApplication

fun main(args: Array<String>) {
    SpringApplication.run(UrlReducerApplication::class.java, *args)
}

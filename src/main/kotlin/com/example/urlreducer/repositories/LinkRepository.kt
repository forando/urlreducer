package com.example.urlreducer.repositories

import com.example.urlreducer.model.Link
import java.util.*

interface LinkRepository: org.springframework.data.repository.Repository<Link, Long> {
    fun findOne(id: Long?): Optional<Link>
    fun save(link: Link): Link
    fun findAll(): List<Link>
}
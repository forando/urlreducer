package com.example.urlreducer.services

import com.example.urlreducer.model.Link
import com.example.urlreducer.repositories.LinkRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class DefaultKeyMapperService(val converter: KeyConverterService, val repo: LinkRepository) : KeyMapperService{

    @Transactional
    override fun add(link: String) = converter.idToKey(repo.save(Link(link)).id)

    override fun getLink(key: String): KeyMapperService.GET {
        val id = converter.keyToId(key)
        val result = repo.findOne(id)

        if (result.isPresent)
            return KeyMapperService.GET.Link(result.get().text)
        else
            return KeyMapperService.GET.NotFound(key)
    }
}

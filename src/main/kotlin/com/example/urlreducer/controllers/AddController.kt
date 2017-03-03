package com.example.urlreducer.controllers

import com.example.urlreducer.services.KeyMapperService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class AddController(val service: KeyMapperService){

    @Value("\${reducer.prefix}")
    private lateinit var prefix: String

    @ResponseBody
    @RequestMapping(path = arrayOf("add"), method = arrayOf(RequestMethod.POST))
    fun addRest(@RequestBody request: AddRequest) =
            ResponseEntity.ok(AddResponse(request.link, service.add(request.link)))

    @RequestMapping(path = arrayOf("addhtml"), method = arrayOf(RequestMethod.POST))
    fun addHtml(model: Model,
                @RequestParam(value = "link", required = true) link: String): String {
        val result = add(link)
        model.addAttribute("link", result.link)
        model.addAttribute("keyed", prefix + result.key)
        return "result"
    }

    private fun add(link: String) = AddResponse(link, service.add(link))

    data class AddRequest(val link: String)
    data class AddResponse(val link: String, val key: String)
}

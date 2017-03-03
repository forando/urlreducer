package com.example.urlreducer.controllers

import com.example.urlreducer.services.KeyMapperService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
class RedirectController(val service: KeyMapperService) {

    companion object{
        private val HEADER_NAME = "Location"
    }

    @RequestMapping("/")
    fun home() = "home"

    @RequestMapping("/{key}")
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse){
        val result = service.getLink(key)

        when(result){
            is KeyMapperService.GET.Link -> {
                response.setHeader(HEADER_NAME, result.link)
                response.status = 302
            }
            is KeyMapperService.GET.NotFound -> response.status = 404
        }
    }

}

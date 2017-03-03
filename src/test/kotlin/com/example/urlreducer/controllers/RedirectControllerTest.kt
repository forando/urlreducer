package com.example.urlreducer.controllers

import com.example.urlreducer.services.KeyMapperService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@WebMvcTest(RedirectController::class)
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
class RedirectControllerTest(){

    @MockBean
    lateinit var service: KeyMapperService

    @Autowired
    lateinit var mvc: MockMvc

    private val REDIRECT_STATUS: Int = 302
    private val NOT_FOUND: Int = 404
    private val PATH = "/aAbBcCdD"
    private val BED_PATH = "/lolololo"
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://www.eveonline.com"

    @Before
    fun init(){
        given(service.getLink(PATH.replace("/", ""))).willReturn(KeyMapperService.GET.Link(HEADER_VALUE))
        given(service.getLink(BED_PATH.replace("/", ""))).willReturn(KeyMapperService.GET.NotFound(BED_PATH))
    }

    @Test
    fun controllerMustRedirectUsWhenRequestIsSuccessful(){
        mvc.perform(get(PATH)).andExpect(status()
                .`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    @Test
    fun controllerMustReturn404IfBadKey(){
        mvc.perform(get(BED_PATH))
                .andExpect(status()
                        .`is`(NOT_FOUND))
    }

    @Test
    fun homeWorksFine(){
        mvc.perform(get("/")).andExpect(view().name("home"))
    }
}

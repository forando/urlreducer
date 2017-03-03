package com.example.urlreducer.controllers

import com.example.urlreducer.services.KeyMapperService
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@WebMvcTest(AddController::class)
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
class AddControllerTest{

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @MockBean
    lateinit var service: KeyMapperService

    private val KEY = "key"
    private val LINK = "Link"

    @Before
    fun init(){
        given(service.add(LINK)).willReturn(KEY)
    }

    @Test
    fun keyIsReturnedAfterLinkIsProvided(){
        mvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(AddController.AddRequest(LINK))))
                .andExpect(jsonPath("$.key", Matchers.equalTo((KEY))))
                .andExpect(jsonPath("$.link", Matchers.equalTo((LINK))))
    }

    @Test
    fun redirectedToSecondPageAfterFormSubmitted(){
        mvc.perform(post("/addhtml").param("link", LINK).contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk)
                .andExpect(content().string(Matchers.containsString(KEY)))
                .andExpect(content().string(Matchers.containsString(LINK)))
    }

}

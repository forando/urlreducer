package com.example.urlreducer.services

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
class KeyConverterServiceTest {

    @Autowired
    lateinit var keyConverterService: KeyConverterService

    @Test
    fun givenIdMustBeConvertableBothWays(){
        val rand = Random()
        for (i in 0..1000L) {
            val initialId = Math.abs(rand.nextLong())
            val key = keyConverterService.idToKey(initialId)
            val id = keyConverterService.keyToId(key)
            assertEquals(initialId, id)
        }
    }

}

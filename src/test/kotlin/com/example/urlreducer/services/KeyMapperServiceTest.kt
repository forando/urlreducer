package com.example.urlreducer.services

import com.example.urlreducer.model.Link
import com.example.urlreducer.repositories.LinkRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
class KeyMapperServiceTest() {

    @Autowired
    lateinit var keyMapperService: KeyMapperService

    @MockBean
    lateinit var converter: KeyConverterService

    @MockBean
    lateinit var repo: LinkRepository

    private val KEY_A: String = "abc"
    private val KEY_B: String = "def"
    private val ID_A: Long = 10000000L
    private val ID_B: Long = 10000001L
    private val NEW_KEY: String = "anotherKey"
    private val LINK: String = "http://wow.ru"
    private val LINK_NEW: String = "http://www.eveonline.com"

    private val LINK_OBJ_A: Link = Link(LINK, ID_A)
    private val LINK_OBJ_B: Link = Link(LINK_NEW, ID_B)

    @Before
    fun init(){
        given(converter.idToKey(ID_A)).willReturn(KEY_A)
        given(converter.idToKey(ID_B)).willReturn(KEY_B)
        given(converter.keyToId(KEY_A)).willReturn(ID_A)
        given(converter.keyToId(KEY_B)).willReturn(ID_B)

        given(repo.findOne(Matchers.anyObject())).willReturn(Optional.empty())
        given(repo.save(Link(LINK))).willReturn(LINK_OBJ_A)
        given(repo.save(Link(LINK_NEW))).willReturn(LINK_OBJ_B)
        given(repo.findOne(ID_A)).willReturn(Optional.of(LINK_OBJ_A))
        given(repo.findOne(ID_B)).willReturn(Optional.of(LINK_OBJ_B))
    }


    /*@Test
    fun clientCannotAddExistingKey(){
        keyMapperService.add(KEY, LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY), keyMapperService.add(KEY, LINK_NEW))
        assertEquals(KeyMapperService.GET.Link(LINK), keyMapperService.getLink(KEY))
    }

    @Test
    fun clientCanAddNewKeyWithLink(){
        assertEquals(KeyMapperService.Add.Success(KEY, LINK), keyMapperService.add(KEY, LINK))
        assertEquals(KeyMapperService.GET.Link(LINK), keyMapperService.getLink(KEY))
    }*/

    @Test
    fun clientCanAddLinks(){
        val keyA = keyMapperService.add(LINK)
        assertEquals(KeyMapperService.GET.Link(LINK), keyMapperService.getLink(keyA))
        val keyB = keyMapperService.add(LINK_NEW)
        assertEquals(KeyMapperService.GET.Link(LINK_NEW), keyMapperService.getLink(keyB))
        assertNotEquals(keyA, keyB)
    }

    @Test
    fun clientCannotTakeLinkIfKeyIsNotFoundInService(){
        assertEquals(KeyMapperService.GET.NotFound(NEW_KEY), keyMapperService.getLink(NEW_KEY))
    }
}

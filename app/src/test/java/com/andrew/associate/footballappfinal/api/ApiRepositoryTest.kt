package com.andrew.associate.footballappfinal.api

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class ApiRepositoryTest{
    @Test
    fun testDoRequest() {
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }
}
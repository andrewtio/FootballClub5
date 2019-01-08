package com.andrew.associate.footballappfinal.search

import com.andrew.associate.footballappfinal.TestContextProvider
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTest {

    @Mock
    private
    lateinit var view: GameSearchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: SearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getGameSearch() {
        val matches: MutableList<MatchItems> = mutableListOf()
        val response = EventSearchResponse(matches)
        val teamName = "Chelsea"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getSearchGames(teamName)).await(),
                    EventSearchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getGameSearch(teamName)

            Mockito.verify(view).showGameItems(matches)
        }
    }
}
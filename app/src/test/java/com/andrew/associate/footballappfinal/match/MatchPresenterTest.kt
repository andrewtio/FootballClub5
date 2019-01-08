package com.andrew.associate.footballappfinal.match

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

class MatchPresenterTest {

    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getPrevMatchList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)
        val league = "4328"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getPrevMatch(league)).await(),
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPrevMatchList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(matches)
            Mockito.verify(view).hideLoading()
        }

    }

    @Test
    fun getNextMatchList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)
        val league = "4328"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getNextMatch(league)).await(),
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatchList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(matches)
            Mockito.verify(view).hideLoading()
        }

    }
}
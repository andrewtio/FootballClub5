package com.andrew.associate.footballappfinal.match.detail

import com.andrew.associate.footballappfinal.TestContextProvider
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.andrew.associate.footballappfinal.match.Match
import com.andrew.associate.footballappfinal.match.MatchPresenter
import com.andrew.associate.footballappfinal.match.MatchResponse
import com.andrew.associate.footballappfinal.match.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    @Mock
    private
    lateinit var view: MatchDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchDetail() {
        val matches: MutableList<MatchDetail> = mutableListOf()
        val response = DetailMatchResponse(matches)
        val event = "441613"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getMatchDetail(event)).await(),
                    DetailMatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchDetail(event)

            Mockito.verify(view).showMatchDetail(matches)
        }
    }
}
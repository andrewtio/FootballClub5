package com.andrew.associate.footballappfinal.teams.detail.players

import com.andrew.associate.footballappfinal.TestContextProvider
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.andrew.associate.footballappfinal.teams.Team
import com.andrew.associate.footballappfinal.teams.TeamResponse
import com.andrew.associate.footballappfinal.teams.TeamsPresenter
import com.andrew.associate.footballappfinal.teams.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayersPresenterTest{
    @Mock
    private
    lateinit var view: PlayersView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayersPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayersPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val players: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(players)
        val idTeam = "133604"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getPlayers(idTeam)).await(),
                    PlayerResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPlayerList(idTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPlayersList(players)
            Mockito.verify(view).hideLoading()
        }
    }
}
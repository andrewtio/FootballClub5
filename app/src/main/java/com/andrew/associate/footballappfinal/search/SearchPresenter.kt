package com.andrew.associate.footballappfinal.search

import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.andrew.associate.footballappfinal.match.MatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(private val v: GameSearchView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson
) {

    fun getGameSearch(clubName: String?) {
        v.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val dataGame = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getSearchGames(clubName)).await()
                , MatchResponse::class.java
            )

            v.showGameItems(dataGame.events)
            v.hideLoading()
        }
    }
}
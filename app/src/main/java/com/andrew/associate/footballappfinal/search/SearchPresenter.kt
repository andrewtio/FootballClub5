package com.andrew.associate.footballappfinal.search

import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.andrew.associate.footballappfinal.match.MatchResponse
import com.andrew.associate.footballappfinal.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(private val v: GameSearchView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getGameSearch(clubName: String?) {
        v.showLoading()
        GlobalScope.launch(context.main) {
            val dataGame = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getSearchGames(clubName)).await()
                , EventSearchResponse::class.java
            )

            v.showGameItems(dataGame.event)
            v.hideLoading()
        }
    }
}
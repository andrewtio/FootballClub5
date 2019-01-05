package com.andrew.associate.footballappfinal.teams.detail.players

import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.andrew.associate.footballappfinal.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayersPresenter(private val view: PlayersView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                       private val context: CoroutineContextProvider = CoroutineContextProvider()

) {

    fun getPlayerList(id: String?) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayers(id)).await(),
                PlayerResponse::class.java
            )
            view.showPlayersList(data.player)
            view.hideLoading()
        }
    }

}
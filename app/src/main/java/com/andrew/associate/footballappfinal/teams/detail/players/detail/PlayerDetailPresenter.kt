package com.andrew.associate.footballappfinal.teams.detail.players.detail

import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.andrew.associate.footballappfinal.teams.detail.players.PlayerResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerDetailPresenter (private val view: PlayerDetailView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson
){

    fun getPlayerDetail(teamId: String){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayers(teamId)).await(),
                PlayerResponse::class.java
            )

            view.hideLoading()
            view.showPlayerDetail(data.player)
        }
    }
}
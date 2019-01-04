package com.andrew.associate.footballappfinal.match.detail

import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.andrew.associate.footballappfinal.teams.TeamResponse
import com.andrew.associate.footballappfinal.teams.detail.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter (private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson
){

    fun getMatchDetail(event: String){


        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(event)).await(),
                DetailMatchResponse::class.java
            )


            view.showMatchDetail(data.events)
        }
    }

    fun getTeamImage(team: String?, teamType: String?) {


        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamImage(team)).await(),
                ImageResponse::class.java
            )



            if (teamType == "Away")
                view.showAwayTeamImage(data.teams)
            else
                view.showHomeTeamImage(data.teams)
        }
    }
}
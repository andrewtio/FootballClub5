package com.andrew.associate.footballappfinal.teams

import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.andrew.associate.footballappfinal.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getTeamList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)).await(),
                TeamResponse::class.java
            )
            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }

    fun getSearchClubData(clubName: String?){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val dataClub = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getSearchClubs(clubName)).await()
                , TeamResponse::class.java
            )

            view.showTeamList(dataClub.teams)
            view.hideLoading()
        }
    }
}
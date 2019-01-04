package com.andrew.associate.footballappfinal.match

import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.api.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter (private val view: MatchView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson){

    fun getPrevMatchList(league: String?){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPrevMatch(league)).await(),
                MatchResponse::class.java
            )
            view.showMatchList(data.events)
            view.hideLoading()
        }
    }

    fun getNextMatchList(league: String?){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(league)).await(),
                MatchResponse::class.java
            )
            view.showMatchList(data.events)
            view.hideLoading()
        }
    }
}
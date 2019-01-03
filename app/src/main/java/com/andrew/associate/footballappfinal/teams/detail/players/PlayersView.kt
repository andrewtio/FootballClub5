package com.andrew.associate.footballappfinal.teams.detail.players

import com.andrew.associate.footballappfinal.teams.Team

interface PlayersView {

    fun showLoading()
    fun hideLoading()
    fun showPlayersList(data: List<Player>)

}
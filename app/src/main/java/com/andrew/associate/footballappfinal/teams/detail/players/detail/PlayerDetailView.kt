package com.andrew.associate.footballappfinal.teams.detail.players.detail

import com.andrew.associate.footballappfinal.teams.detail.players.Player

interface PlayerDetailView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<Player>)
}
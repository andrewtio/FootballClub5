package com.andrew.associate.footballappfinal.teams.detail

import com.andrew.associate.footballappfinal.teams.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}
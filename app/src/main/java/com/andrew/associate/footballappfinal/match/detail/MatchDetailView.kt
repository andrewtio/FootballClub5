package com.andrew.associate.footballappfinal.match.detail


interface MatchDetailView {

    fun showMatchDetail(match: List<MatchDetail>)
    fun showHomeTeamImage (match: List<Image>)
    fun showAwayTeamImage (match: List<Image>)

}
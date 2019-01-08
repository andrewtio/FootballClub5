package com.andrew.associate.footballappfinal.match.detail

import android.widget.ProgressBar

interface MatchDetailView {

    fun showMatchDetail(match: List<MatchDetail>)
    fun showHomeTeamImage (match: List<Image>)
    fun showAwayTeamImage (match: List<Image>)

}
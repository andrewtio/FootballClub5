package com.andrew.associate.footballappfinal.match.detail

import android.widget.ProgressBar

interface MatchDetailView {

    fun showLoading(progBar: ProgressBar)
    fun hideLoading(progBar: ProgressBar)
    fun showMatchDetail(data: List<MatchDetail>)
    fun showHomeTeamImage (data: List<Image>)
    fun showAwayTeamImage (data: List<Image>)

}
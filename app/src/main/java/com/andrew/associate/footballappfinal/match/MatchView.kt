package com.andrew.associate.footballappfinal.match

interface MatchView {

    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)

}
package com.andrew.associate.footballappfinal.search

import com.andrew.associate.footballappfinal.match.Match

interface GameSearchView {
    fun showGameItems(game: List<MatchItems>)
    fun hideLoading()
    fun showLoading()
}
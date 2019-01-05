package com.andrew.associate.footballappfinal.match

import com.google.gson.annotations.SerializedName

data class Match (

    @SerializedName("idEvent")
    var matchId: String? = null,

    @SerializedName("dateEvent")
    var matchDate: String? = null,

    @SerializedName("strTime")
    var matchTime: String? = null,

    @SerializedName("strFilename")
    var strFilename: String? = null,

    // HOME
    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    // AWAY
    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null

    )
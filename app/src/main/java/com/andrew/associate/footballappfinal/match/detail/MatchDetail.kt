package com.andrew.associate.footballappfinal.match.detail

import com.google.gson.annotations.SerializedName

data class MatchDetail(

    @SerializedName("idEvent")
    val matchId: String? = null,

    @SerializedName("idHomeTeam")
    val homeTeamId: String? = null,

    @SerializedName("idAwayTeam")
    val awayTeamId: String? = null,

    @SerializedName("strHomeTeam")
    val homeTeamName: String? = null,

    @SerializedName("strAwayTeam")
    val awayTeamName: String? = null,

    @SerializedName("dateEvent")
    val matchDate: String? = null,

    @SerializedName("strTime")
    val matchTime: String? = null,

    @SerializedName("strFilename")
    val strFilename: String? = null,

    // HOME

    @SerializedName("intHomeScore")
    val homeTeamScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    val homeTeamScoreDetails: String? = null,

    @SerializedName("intHomeShots")
    val homeTeamShots: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    val homeTeamGK: String? = null,

    @SerializedName("strHomeLineupDefense")
    val homeTeamDef: String? = null,

    @SerializedName("strHomeLineupMidfield")
    val homeTeamMid: String? = null,

    @SerializedName("strHomeLineupForward")
    val homeTeamFwd: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    val homeTeamSub: String? = null,

    // AWAY

    @SerializedName("intAwayScore")
    val awayTeamScore: String? = null,

    @SerializedName("strAwayGoalDetails")
    val awayTeamScoreDetails: String? = null,

    @SerializedName("intAwayShots")
    val awayTeamShots: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    val awayTeamGK: String? = null,

    @SerializedName("strAwayLineupDefense")
    val awayTeamDef: String? = null,

    @SerializedName("strAwayLineupMidfield")
    val awayTeamMid: String? = null,

    @SerializedName("strAwayLineupForward")
    val awayTeamFwd: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    val awayTeamSub: String? = null

    )
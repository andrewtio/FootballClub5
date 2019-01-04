package com.andrew.associate.footballappfinal.match

import com.google.gson.annotations.SerializedName

data class Match (
    val id: Int? = null,

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

    ) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID : String = "ID_"
        const val ID_EVENT : String = "ID_EVENT"
        const val STR_HOME_TEAM : String = "STR_HOME_TEAM"
        const val STR_AWAY_TEAM : String = "STR_AWAY_TEAM"
        const val INT_HOME_SCORE : String = "INT_HOME_SCORE"
        const val INT_AWAY_SCORE : String = "INT_AWAY_SCORE"
        const val STR_FILENAME : String = "STR_FILENAME"
        const val DATE_EVENT : String = "DATE_EVENT"
        const val STR_TIME : String = "STR_TIME"
    }
}
package com.andrew.associate.footballappfinal.db

data class FavoriteMatch(val id: Long?,
                        val idEvent: String?,
                        val dateEvent: String?,
                        val matchTime: String?,
                        val homeTeam: String?,
                         val awayTeam: String?,
                        val homeScore: String?,
                        val awayScore: String?,
                         val strFilename: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID : String = "ID_"
        const val ID_EVENT : String = "ID_EVENT"
        const val DATE_EVENT : String = "DATE_EVENT"
        const val STR_HOME_TEAM : String = "STR_HOME_TEAM"
        const val INT_HOME_SCORE : String = "INT_HOME_SCORE"
        const val STR_AWAY_TEAM : String = "STR_AWAY_TEAM"
        const val INT_AWAY_SCORE : String = "INT_AWAY_SCORE"
        const val STR_TIME : String = "STR_TIME"
    }
}
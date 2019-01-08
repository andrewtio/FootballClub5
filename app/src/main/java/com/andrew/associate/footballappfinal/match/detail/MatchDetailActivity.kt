package com.andrew.associate.footballappfinal.match.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.R.menu.detail_menu
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.db.FavoriteMatch
import com.andrew.associate.footballappfinal.db.FavoriteTeam
import com.andrew.associate.footballappfinal.db.database
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    private lateinit var detPres: MatchDetailPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id_event: String
    private var homeTeam : String? = null
    private var awayTeam : String? = null
    private var homeScore : String? = null
    private var awayScore : String? = null
    private var dateEvent : String? = null
    private var timeEvent : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val intent = intent

        id_event    = intent.getStringExtra("id_event")
        homeTeam   = intent.getStringExtra("home_team")
        homeScore  = intent.getStringExtra("home_score")
        awayTeam   = intent.getStringExtra("away_team" )
        awayScore  = intent.getStringExtra("away_score")
        dateEvent  = intent.getStringExtra("date_event")
        timeEvent = intent.getStringExtra("time_event")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val GMTFormat = SimpleDateFormat("E, dd MMM yyyy")
        val parseDate = dateFormat.parse(dateEvent)
        val eventDate = GMTFormat.format(parseDate)

        val timeFormat = SimpleDateFormat("hh:mm:ss")
        timeFormat.timeZone = TimeZone.getTimeZone("UTC")
        val timeFormatGMT = SimpleDateFormat("HH:mm")
        val parseTime = timeFormat.parse(timeEvent)
        val eventTime = timeFormatGMT.format(parseTime)

        detail_date_tv.text  = eventDate
        detail_time_tv.text = eventTime
        fc_home_name.text   = homeTeam
        home_goal_detail_tv.text  = homeScore
        fc_away_name.text   = awayTeam
        away_goal_detail_tv.text  = awayScore

        favoriteState()

        val apiRepository = ApiRepository()
        val gson = Gson()

        detPres = MatchDetailPresenter(this,apiRepository, gson)

        detPres.getMatchDetail(id_event)

        if(homeTeam.equals(null) || awayTeam.equals(null)){
            homeTeam = "Chelsea"
            awayTeam = "Arsenal"
        }

        detPres.getTeamImage(homeTeam, "Home")
        detPres.getTeamImage(awayTeam, "Away")

        toast("Match Detail")
        supportActionBar?.title = "Match Detail"

    }

    override fun showMatchDetail(item: List<MatchDetail>){

        // HOME
        val homeGoalScorer    = getDataList(item[0].homeTeamScoreDetails)
        val homeShot           = getDataList(item[0].homeTeamShots)
        val homeGK     = getDataList(item[0].homeTeamGK)
        val homeDef        = getDataList(item[0].homeTeamDef)
        val homeMF       = getDataList(item[0].homeTeamMid)
        val homeFW        = getDataList(item[0].homeTeamFwd)
        val homeSub    = getDataList(item[0].homeTeamSub)

        setToTextDetails(homeGoalScorer, home_scorer_tv)
        setToTextDetails(homeShot, home_shots_tv)
        setToTextDetails(homeGK, home_gk_tv)
        setToTextDetails(homeDef, home_def_tv)
        setToTextDetails(homeMF, home_mid_tv)
        setToTextDetails(homeFW, home_fw_tv)
        setToTextDetails(homeSub, home_sub_tv)

        // AWAY
        val awayGoalScorer    = getDataList(item[0].awayTeamScoreDetails)
        val awayShot           = getDataList(item[0].awayTeamShots)
        val awayGK     = getDataList(item[0].awayTeamGK)
        val awayDef        = getDataList(item[0].awayTeamDef)
        val awayMF       = getDataList(item[0].awayTeamMid)
        val awayFW        = getDataList(item[0].awayTeamFwd)
        val awaySub    = getDataList(item[0].awayTeamSub)

        setToTextDetails(awayGoalScorer, away_scorer_tv)
        setToTextDetails(awayShot, away_shots_tv)
        setToTextDetails(awayGK, away_gk_tv)
        setToTextDetails(awayDef, away_def_tv)
        setToTextDetails(awayMF, away_mid_tv)
        setToTextDetails(awayFW, away_fw_tv)
        setToTextDetails(awaySub, away_sub_tv)

    }

    private fun getDataString(text: String?, data: String): String {
        return if (data != "null")
            getString(R.string.textDetails,text, data)
        else
            getString(R.string.textDetails, ""," - ")
    }

    private fun getDataList (item: String?): List<String> {
        return item.toString().split(";")
    }

    private fun setToTextDetails(Items: List<String> , textView: TextView){
        for (value in Items) {
            textView.text = getDataString(textView.text.toString(), value.trim() )
        }
    }

    override fun showHomeTeamImage(item: List<Image>){
        Picasso.get()
            .load(item[0].badgeTeam)
            .into(fc_home_logo)
    }

    override fun showAwayTeamImage(item: List<Image>){
        Picasso.get()
            .load(item[0].badgeTeam)
            .into(fc_away_logo)
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                .whereArgs("(ID_EVENT = {id_event})",
                    "id_event" to id_event )
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE_MATCH,
                    FavoriteMatch.ID_EVENT to id_event,
                    FavoriteMatch.DATE_EVENT to dateEvent,
                    FavoriteMatch.STR_TIME to timeEvent,
                    FavoriteMatch.STR_HOME_TEAM to homeTeam,
                    FavoriteMatch.STR_AWAY_TEAM to awayTeam,
                    FavoriteMatch.INT_HOME_SCORE to homeScore,
                    FavoriteMatch.INT_AWAY_SCORE to awayScore)
            }
            toast("Added to favorite match").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    FavoriteMatch.TABLE_FAVORITE_MATCH, "(ID_EVENT = {id_event})",
                    "id_event" to id_event)
            }
            toast("Removed from favorite match").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }
    // test merge
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

}
package com.andrew.associate.footballappfinal.teams.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.R.drawable.ic_add_to_favorites
import com.andrew.associate.footballappfinal.R.drawable.ic_added_to_favorites
import com.andrew.associate.footballappfinal.R.id.add_to_favorite
import com.andrew.associate.footballappfinal.R.menu.detail_menu
import com.andrew.associate.footballappfinal.db.FavoriteTeam
import com.andrew.associate.footballappfinal.db.database
import com.andrew.associate.footballappfinal.teams.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

//        val intent = intent
//        id = intent.getStringExtra("id")

        teams = intent.getParcelableExtra("data")

        Picasso.get()
            .load(teams.teamBadge)
            .into(badge_club_detail)
        club_name_detail_tv.text = teams.teamName
        year_detail_club_tv.text = teams.teamFormedYear
        stadium_club_detail_tv.text = teams.teamStadium

        toast("Match Detail")
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        linearLayout(){
//            lparams(width = matchParent, height = wrapContent)
//            orientation = LinearLayout.VERTICAL
//            backgroundColor = Color.WHITE
//
//            swipeRefresh = swipeRefreshLayout{
//                setColorSchemeResources(colorAccent,
//                    android.R.color.holo_green_light,
//                    android.R.color.holo_orange_light,
//                    android.R.color.holo_red_light)
//
//                scrollView{
//                    isVerticalScrollBarEnabled = false
//                    relativeLayout {
//                        lparams(width = matchParent, height = wrapContent)
//
//                        linearLayout{
//                            lparams(width = matchParent, height = wrapContent)
//                            padding = dip(10)
//                            orientation = LinearLayout.VERTICAL
//                            gravity = Gravity.CENTER_HORIZONTAL
//
//                            teamBadge = imageView {}.lparams(height = dip(75))
//
//                            teamName = textView{
//                                this.gravity = Gravity.CENTER
//                                textSize = 20f
//                                textColor = ContextCompat.getColor(context,colorAccent)
//                            }.lparams{
//                                topMargin = dip(5)
//                            }
//
//                            teamFormedYear = textView{
//                                this.gravity = Gravity.CENTER
//                            }
//
//                            teamStadium = textView{
//                                this.gravity = Gravity.CENTER
//                                textColor = ContextCompat.getColor(context, colorPrimaryText)
//                            }
//
//                            teamDescription = textView().lparams{
//                                topMargin = dip(20)
//                            }
//                        }
//                        progressBar = progressBar{
//                        }.lparams{
//                            centerHorizontally()
//                        }
//                    }
//                }
//            }
//        }

        favoriteState()

//        Picasso.get()
//            .load(teams.teamBadge)
//            .into(badge_club_detail)
//        year_detail_club_tv.text = teams.teamFormedYear
//        stadium_club_detail_tv.text = teams.teamStadium

        viewpager_detail_team.adapter = TeamDetailPagerAdapter(supportFragmentManager)
        team_tabs_detail.setupWithViewPager(viewpager_detail_team)

//        val request = ApiRepository()
//        val gson = Gson()
//        presenter = TeamDetailPresenter(this, request, gson)
//        presenter.getTeamDetail(id)

//        swipeRefresh.onRefresh{
//            presenter.getTeamDetail(id)
//        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to teams.teamId.toString() )
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

//    override fun showLoading() {
//        progressBar.visible()
//    }

//    override fun hideLoading() {
//        progressBar.invisible()
//    }

//    override fun showTeamDetail(data: List<Team>) {
//        teams = Team(data[0].teamId,
//            data[0].teamName,
//            data[0].teamBadge)
//        swipeRefresh.isRefreshing = false
//        Picasso.get().load(data[0].teamBadge).into(teamBadge)
//        teamName.text = data[0].teamName
//        teamDescription.text = data[0].teamDescription
//        teamFormedYear.text = data[0].teamFormedYear
//        teamStadium.text = data[0].teamStadium
//
//    }

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
            add_to_favorite -> {
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
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to teams.teamId,
                    FavoriteTeam.TEAM_NAME to teams.teamName,
                    FavoriteTeam.TEAM_BADGE to teams.teamBadge,
                    FavoriteTeam.TEAM_YEAR to teams.teamFormedYear,
                    FavoriteTeam.TEAM_STADIUM to teams.teamStadium,
                    FavoriteTeam.TEAM_DESC to teams.teamDescription)
            }
            toast("Added to favorite team").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to teams.teamId.toString())
            }
            toast("Removed from favorite team").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }
    // test merge
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
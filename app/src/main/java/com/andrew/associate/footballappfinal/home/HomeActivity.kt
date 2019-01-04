package com.andrew.associate.footballappfinal.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.R.id.*
import com.andrew.associate.footballappfinal.R.layout.activity_home
import com.andrew.associate.footballappfinal.favorites.FavoriteTeamsFragment
import com.andrew.associate.footballappfinal.match.Match
import com.andrew.associate.footballappfinal.match.MatchFragment
import com.andrew.associate.footballappfinal.match.detail.MatchDetailActivity
import com.andrew.associate.footballappfinal.match.nextmatch.NextMatchFragment
import com.andrew.associate.footballappfinal.match.prevmatch.PrevMatchFragment
import com.andrew.associate.footballappfinal.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity(),
    NextMatchFragment.OnFragLinkListener,
    PrevMatchFragment.OnFragLinkListener{

    override fun onFragmentLink(game: Match) {
        startActivity<MatchDetailActivity>(
            "id_event" to game.matchId,
            "date_event" to game.matchDate,
            "home_team" to game.homeTeam,
            "home_score" to game.homeScore,
            "away_team" to game.awayTeam,
            "away_score" to game.awayScore,
            "time_event" to game.matchTime
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                match -> {
                    loadMatchFragment(savedInstanceState)
                }

                team -> {
                    loadTeamsFragment(savedInstanceState)
                }

                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = match
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                .commit()
        }
    }
}
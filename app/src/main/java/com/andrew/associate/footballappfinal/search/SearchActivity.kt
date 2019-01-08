package com.andrew.associate.footballappfinal.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.match.Match
import com.andrew.associate.footballappfinal.match.detail.MatchDetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

class SearchActivity: AppCompatActivity(), GameSearchView {

    private var matchItems: MutableList<MatchItems> = mutableListOf()
    private var searchMatch : MutableList<Match> = mutableListOf()

    private lateinit var adapter: SearchGameAdapter
    private lateinit var rV: RecyclerView
    private lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.hide()

        game_search_view.onActionViewExpanded()
        game_search_view.onQueryTextListener(){
            onQueryTextChange{
                    it ->
                presenter.getGameSearch(it)
                true
            }
        }

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = SearchPresenter(this,apiRepository,gson)

        adapter = SearchGameAdapter(matchItems){
            searchMatch.clear()
            searchMatch.add(Match(
                it.matchId,
                it.matchDate,
                it.matchTime,
                "",
                it.homeTeam,
                it.homeScore,
                it.awayTeam,
                it.awayScore
            ))
            this.startActivity<MatchDetailActivity>(
                "id_event" to searchMatch[0].matchId,
                "home_team" to searchMatch[0].homeTeam,
                "home_score" to searchMatch[0].homeScore,
                "away_team" to searchMatch[0].awayTeam,
                "away_score" to searchMatch[0].awayScore,
                "date_event" to searchMatch[0].matchDate,
                "time_event" to searchMatch[0].matchTime
            )
        }

        rV = rv_game_search
        rV.layoutManager = LinearLayoutManager(this)
        rV.adapter = adapter

    }

    override fun showGameItems(game: List<MatchItems>) {
        matchItems.clear()
        matchItems.addAll(game)
        adapter.notifyDataSetChanged()

    }
}

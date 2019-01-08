package com.andrew.associate.footballappfinal.search

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.match.Match
import com.andrew.associate.footballappfinal.match.detail.MatchDetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SearchActivity: AppCompatActivity(), AnkoComponent<Context>, GameSearchView {

    private var matchItems: MutableList<MatchItems> = mutableListOf()
    private var searchMatch : MutableList<Match> = mutableListOf()

    private lateinit var adapter: SearchGameAdapter
    private lateinit var listMatchSearch: RecyclerView
    private lateinit var presenter: SearchPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var spinner: Spinner

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

        listMatchSearch = rv_game_search
        listMatchSearch.layoutManager = LinearLayoutManager(this)
        listMatchSearch.adapter = adapter

    }

    override fun showGameItems(game: List<MatchItems>) {
        matchItems.clear()
        matchItems.addAll(game)
        adapter.notifyDataSetChanged()

    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = R.id.spinner
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listMatchSearch = recyclerView {
                        id = R.id.list_match_search
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar{
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }
}


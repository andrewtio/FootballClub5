package com.andrew.associate.footballappfinal.teams.detail.players


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.teams.Team
import com.andrew.associate.footballappfinal.teams.detail.players.detail.PlayerDetailActivity
import com.andrew.associate.footballappfinal.utils.invisible
import com.andrew.associate.footballappfinal.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class PlayersFragment : Fragment(), AnkoComponent<Context>, PlayersView {

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var team: Team
    private lateinit var presenter: PlayersPresenter
    private lateinit var adapter: PlayersAdapter

    private lateinit var listPlayers: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var id: String? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        team = activity?.intent!!.getParcelableExtra("data")
        id = team.teamId

        adapter = PlayersAdapter(players){
            context?.startActivity<PlayerDetailActivity>("data_player" to it)
        }

        listPlayers.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayersPresenter(this, request, gson)
        presenter.getPlayerList(id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listPlayers = recyclerView {
                        id = R.id.list_player
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

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayersList(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }
}




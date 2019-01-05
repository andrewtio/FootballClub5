package com.andrew.associate.footballappfinal.match.prevmatch


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.match.Match
import com.andrew.associate.footballappfinal.match.MatchPresenter
import com.andrew.associate.footballappfinal.match.MatchView
import com.andrew.associate.footballappfinal.match.detail.MatchDetailActivity
import com.andrew.associate.footballappfinal.utils.invisible
import com.andrew.associate.footballappfinal.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.lang.RuntimeException

class PrevMatchFragment : Fragment(), AnkoComponent<Context>,
    MatchView {

    private var matches: MutableList<Match> = mutableListOf()
    private var listener: OnFragLinkListener? = null
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: PrevMatchAdapter

    private lateinit var spinner: Spinner

    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = PrevMatchAdapter(matches,listener)

        listMatch.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                when(position) {
                    0 -> {
                        leagueName = spinner.selectedItem.toString().replace("English Premier League", "4328")
                        presenter.getPrevMatchList(leagueName)
                    }
                    1 -> {
                        leagueName = spinner.selectedItem.toString().replace("English League Championship", "4329")
                        presenter.getPrevMatchList(leagueName)
                    }
                    2 -> {
                        leagueName = spinner.selectedItem.toString().replace("German Bundesliga", "4331")
                        presenter.getPrevMatchList(leagueName)
                    }
                    3 -> {
                        leagueName = spinner.selectedItem.toString().replace("Italian Serie A", "4332")
                        presenter.getPrevMatchList(leagueName)
                    }
                    4 -> {
                        leagueName = spinner.selectedItem.toString().replace("French Ligue 1", "4334")
                        presenter.getPrevMatchList(leagueName)
                    }
                    5 -> {
                        leagueName = spinner.selectedItem.toString().replace("Spanish La Liga", "4335")
                        presenter.getPrevMatchList(leagueName)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getPrevMatchList(leagueName)
        }
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

                    listMatch = recyclerView {
                        id = R.id.list_match_prev
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

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

    interface OnFragLinkListener{
        fun onFragmentLink(game: Match)
    }

    override fun onAttach(ctx: Context){
        super.onAttach(ctx)
        if (ctx is OnFragLinkListener){
            listener = ctx
        }else{
            throw RuntimeException(ctx.toString() + "")
        }
    }

    override fun onDetach(){
        super.onDetach()
        listener = null
    }
}

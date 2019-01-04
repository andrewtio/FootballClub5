package com.andrew.associate.footballappfinal.match.nextmatch


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
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
import com.andrew.associate.footballappfinal.match.prevmatch.PrevMatchFragment
import com.andrew.associate.footballappfinal.utils.MyDateFormat
import com.andrew.associate.footballappfinal.utils.invisible
import com.andrew.associate.footballappfinal.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

class NextMatchFragment : Fragment(), AnkoComponent<Context>,
    MatchView {

    private var matches: MutableList<Match> = mutableListOf()
    private var listener: OnFragLinkListener? = null

    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: NextMatchAdapter

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


        initRvMatchEvents()

        listMatch.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = MatchPresenter(this, request, gson)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                when(position) {
                    0 -> {
                        leagueName = spinner.selectedItem.toString().replace("English Premier League", "4328")
                        presenter.getNextMatchList(leagueName)
                    }
                    1 -> {
                        leagueName = spinner.selectedItem.toString().replace("English League Championship", "4329")
                        presenter.getNextMatchList(leagueName)
                    }
                    2 -> {
                        leagueName = spinner.selectedItem.toString().replace("German Bundesliga", "4331")
                        presenter.getNextMatchList(leagueName)
                    }
                    3 -> {
                        leagueName = spinner.selectedItem.toString().replace("Italian Serie A", "4332")
                        presenter.getNextMatchList(leagueName)
                    }
                    4 -> {
                        leagueName = spinner.selectedItem.toString().replace("French Ligue 1", "4334")
                        presenter.getNextMatchList(leagueName)
                    }
                    5 -> {
                        leagueName = spinner.selectedItem.toString().replace("Spanish La Liga", "4335")
                        presenter.getNextMatchList(leagueName)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getNextMatchList(leagueName)
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
                        id = R.id.list_match
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

    private fun initRvMatchEvents(){
        adapter = NextMatchAdapter(matches,true,
            listener,
            {match ->
                addToCalendar(match)
            }
        )
    }

    private fun addToCalendar(match: Match){
        val date = match.matchDate ?: ""
        val time = match.matchTime ?: "00:00"

        if (date.isNotEmpty()) {
            val startTimeInMillis = MyDateFormat.gregorianDateTimeInMillis(date, time)
            val endTimeInMillis =  startTimeInMillis + TimeUnit.MINUTES.toMillis(90)

            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra(CalendarContract.Events.TITLE, "${match.strFilename}")
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "${match.homeTeam}")
            intent.putExtra(CalendarContract.Events.DESCRIPTION, "${match.strFilename}")
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTimeInMillis)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTimeInMillis)
            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
            intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE)
            intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
            startActivity(intent)
        } else{
            toast("Schedule not Available")
        }
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

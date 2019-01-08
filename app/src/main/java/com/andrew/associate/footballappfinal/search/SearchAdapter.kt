package com.andrew.associate.footballappfinal.search

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.match.Match
import com.andrew.associate.footballappfinal.match.prevmatch.PrevMatchViewHolder
import com.andrew.associate.footballappfinal.match.prevmatch.TeamUI
import kotlinx.android.synthetic.main.item_list_match.view.*
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class SearchGameAdapter (private var eDI: List<MatchItems>
                         , private val listener: (MatchItems) -> Unit)
    : RecyclerView.Adapter<SearchViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchViewHolder {
        return SearchViewHolder(SearchUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = eDI.size

    override fun onBindViewHolder(p0: SearchViewHolder, p1: Int) {
        p0.bind(eDI[p1], listener)
    }

}

class SearchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                textView {
                    id = R.id.match_date
                    textSize = 12f
                }.lparams{
                    margin = dip(5)
                    this.gravity = Gravity.CENTER
                }

                textView {
                    id = R.id.match_time
                    textSize = 12f
                }.lparams{
                    margin = dip(5)
                    this.gravity = Gravity.CENTER
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(5)
                    orientation = LinearLayout.HORIZONTAL
                    this.gravity = Gravity.CENTER

                    textView {
                        id = R.id.home_team
                        textSize = 12f
                    }.lparams {
                        margin = dip(5)
                    }

                    textView {
                        id = R.id.home_score
                        textSize = 25f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        margin = dip(15)
                    }

                    textView {
                        text = "VS"
                        textSize = 25f
                        typeface = Typeface.DEFAULT_BOLD
                        gravity = Gravity.CENTER
                    }.lparams {
                        margin = dip(5)
                    }

                    textView {
                        id = R.id.away_score
                        textSize = 25f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        margin = dip(15)
                    }

                    textView {
                        id = R.id.away_team
                        textSize = 12f
                    }.lparams {
                        margin = dip(5)
                    }
                }
            }
        }
    }
}

class SearchViewHolder(v: View): RecyclerView.ViewHolder(v){

    private val matchDate: TextView = v.find(R.id.match_date)
    private val homeTeam: TextView = v.find(R.id.home_team)
    private val awayTeam: TextView = v.find(R.id.away_team)
    private val homeScore: TextView = v.find(R.id.home_score)
    private val awayScore: TextView = v.find(R.id.away_score)
    private val matchTime: TextView = v.find(R.id.match_time)

    fun bind (gameData: MatchItems, listener: (MatchItems) -> Unit){

        val formatDate = SimpleDateFormat("yyyy-MM-dd")
        val formatGMT = SimpleDateFormat("E, dd MMM yyyy")
        val dateParse = formatDate.parse(gameData.matchDate)
        val dateEvent = formatGMT.format(dateParse)

//        val timeFormat = SimpleDateFormat("hh:mm:ss")
//        timeFormat.timeZone = TimeZone.getTimeZone("UTC")
//        val timeFormatGMT = SimpleDateFormat("HH:mm")
//        val parseTime = timeFormat.parse(gameData.matchTime)
//        val eventTime = timeFormatGMT.format(parseTime)

        matchDate.text = dateEvent
        matchTime.text = gameData.matchTime
        homeTeam.text = gameData.homeTeam
        awayTeam.text = gameData.awayTeam
        homeScore.text = gameData.homeScore
        awayScore.text = gameData.awayScore

        itemView.setOnClickListener{
            listener(gameData)
        }
    }

}
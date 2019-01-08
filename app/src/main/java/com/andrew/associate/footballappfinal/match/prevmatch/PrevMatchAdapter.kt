package com.andrew.associate.footballappfinal.match.prevmatch

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.R.id.*
import com.andrew.associate.footballappfinal.match.Match
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.style
import java.text.SimpleDateFormat
import java.util.*

class PrevMatchAdapter(private val matches: List<Match>,
                       private val listener: (match:Match) -> Unit)
    : RecyclerView.Adapter<PrevMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevMatchViewHolder {
        return PrevMatchViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PrevMatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }

    override fun getItemCount(): Int = matches.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                textView {
                    id = match_date
                    textSize = 12f
                }.lparams{
                    margin = dip(5)
                    this.gravity = Gravity.CENTER
                }

                textView {
                    id = match_time
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
                        id = home_team
                        textSize = 12f
                    }.lparams {
                        margin = dip(5)
                    }

                    textView {
                        id = home_score
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
                        id = away_score
                        textSize = 25f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        margin = dip(15)
                    }

                    textView {
                        id = away_team
                        textSize = 12f
                    }.lparams {
                        margin = dip(5)
                    }
                }
            }
        }
    }
}

class PrevMatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val matchDate: TextView = view.find(R.id.match_date)
    private val homeTeam: TextView = view.find(R.id.home_team)
    private val awayTeam: TextView = view.find(R.id.away_team)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayScore: TextView = view.find(R.id.away_score)
    private val matchTime: TextView = view.find(R.id.match_time)


    fun bindItem(match: Match, listener: (match: Match) -> Unit){

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val GMTFormat = SimpleDateFormat("E, dd MMM yyyy")
        val parseDate = dateFormat.parse(match.matchDate)
        val eventDate = GMTFormat.format(parseDate)

        val timeFormat = SimpleDateFormat("hh:mm:ss")
        timeFormat.timeZone = TimeZone.getTimeZone("UTC")
        val timeFormatGMT = SimpleDateFormat("HH:mm")
        val parseTime = timeFormat.parse(match.matchTime)
        val eventTime = timeFormatGMT.format(parseTime)

        matchDate.text = eventDate
        homeTeam.text = match.homeTeam
        awayTeam.text = match.awayTeam
        homeScore.text = match.homeScore
        awayScore.text = match.awayScore
        matchTime.text = eventTime
        itemView.setOnClickListener { listener(match) }
    }
}
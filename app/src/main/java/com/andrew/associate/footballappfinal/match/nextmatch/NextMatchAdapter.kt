package com.andrew.associate.footballappfinal.match.nextmatch

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.R.id.*
import com.andrew.associate.footballappfinal.match.Match
import com.andrew.associate.footballappfinal.utils.gone
import com.andrew.associate.footballappfinal.utils.visible
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class NextMatchAdapter(private val matches: List<Match>,
                       private val alarmEnabled: Boolean,
                       private val listener: (match:Match) -> Unit,
                       private val alarmListener: (match: Match) -> Unit)
    : RecyclerView.Adapter<NextMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindItem(matches[position], alarmEnabled, listener, alarmListener)
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

                imageView(R.drawable.ic_alarm){
                    id = notification_alarm
                }.
                    lparams(width= dip(24), height = dip(24))
                    this.gravity = Gravity.CENTER

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

class NextMatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val notificationAlarm: ImageView = view.find(R.id.notification_alarm)
    private val matchDate: TextView = view.find(R.id.match_date)
    private val homeTeam: TextView = view.find(R.id.home_team)
    private val awayTeam: TextView = view.find(R.id.away_team)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayScore: TextView = view.find(R.id.away_score)
    private val matchTime: TextView = view.find(R.id.match_time)


    fun bindItem(match: Match,
                 alarmEnabled: Boolean,
                 listener: (match: Match) -> Unit,
                 alarmListener: (match: Match) -> Unit) {

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

        if (alarmEnabled) {
            notificationAlarm.visible()
            notificationAlarm.setOnClickListener {
                alarmListener(match)
            }
        } else {
            notificationAlarm.gone()
        }

    }
}
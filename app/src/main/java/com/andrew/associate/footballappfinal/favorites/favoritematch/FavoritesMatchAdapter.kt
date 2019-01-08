package com.andrew.associate.footballappfinal.favorites.favoritematch

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.db.FavoriteMatch
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class FavoritesMatchAdapter(private val matches: List<FavoriteMatch>,
                       private val listener: (FavoriteMatch) -> Unit)
    : RecyclerView.Adapter<FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
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

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val matchDate: TextView = view.find(R.id.match_date)
    private val homeTeam: TextView = view.find(R.id.home_team)
    private val awayTeam: TextView = view.find(R.id.away_team)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayScore: TextView = view.find(R.id.away_score)
    private val matchTime: TextView = view.find(R.id.match_time)

    fun bindItem(favorite: FavoriteMatch, listener: (FavoriteMatch) -> Unit){

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val GMTFormat = SimpleDateFormat("E, dd MMM yyyy")
        val parseDate = dateFormat.parse(favorite.dateEvent)
        val eventDate = GMTFormat.format(parseDate)

        val timeFormat = SimpleDateFormat("hh:mm:ss")
        timeFormat.timeZone = TimeZone.getTimeZone("UTC")
        val timeFormatGMT = SimpleDateFormat("HH:mm")
        val parseTime = timeFormat.parse(favorite.matchTime)
        val eventTime = timeFormatGMT.format(parseTime)

        matchDate.text = eventDate
        homeTeam.text = favorite.homeTeam
        awayTeam.text = favorite.awayTeam
        homeScore.text = favorite.homeScore
        awayScore.text = favorite.awayScore
        matchTime.text = eventTime
        itemView.setOnClickListener { listener(favorite) }
    }
}
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
import com.andrew.associate.footballappfinal.match.Match
import com.andrew.associate.footballappfinal.match.prevmatch.PrevMatchFragment
import com.andrew.associate.footballappfinal.teams.Team
import com.andrew.associate.footballappfinal.utils.formatToGMT
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import java.text.SimpleDateFormat

class FavoritesMatchAdapter(private val matches: List<FavoriteMatch>,
                       private val listener: (FavoriteMatch) -> Unit)
    : RecyclerView.Adapter<FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_match, parent, false)
        return FavoriteMatchViewHolder(v)
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
                        textSize = 5f
                    }.lparams {
                        margin = dip(5)
                    }

                    textView {
                        id = R.id.home_score
                        textSize = 15f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        margin = dip(5)
                    }

                    textView {
                        text = "VS"
                        textSize = 15f
                        typeface = Typeface.DEFAULT_BOLD
                        gravity = Gravity.CENTER
                    }.lparams {
                        margin = dip(5)
                    }

                    textView {
                        id = R.id.away_score
                        textSize = 15f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        margin = dip(5)
                    }

                    textView {
                        id = R.id.away_team
                        textSize = 5f
                    }.lparams {
                        margin = dip(5)
                    }
                }
            }
        }
    }
}

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val matchDate: TextView = view.find(R.id.game_date_tv)
    private val homeTeam: TextView = view.find(R.id.home_club_tv)
    private val awayTeam: TextView = view.find(R.id.away_club_tv)
    private val homeScore: TextView = view.find(R.id.home_point_tv)
    private val awayScore: TextView = view.find(R.id.away_point_tv)
    private val matchTime: TextView = view.find(R.id.game_time_tv)

    fun bindItem(favorite: FavoriteMatch, listener: (FavoriteMatch) -> Unit){

        val timeChanger = formatToGMT(favorite.dateEvent, favorite.matchTime)
        val dateFormat = SimpleDateFormat("E, dd MMM yyyy")
        val timeFormat = SimpleDateFormat("HH:mm")
        val date = dateFormat.format(timeChanger)
        val changedTime = timeFormat.format(timeChanger)

        favorite.matchTime
        favorite.dateEvent
        matchDate.text = date
        homeTeam.text = favorite.homeTeam
        awayTeam.text = favorite.awayTeam
        homeScore.text = favorite.homeScore
        awayScore.text = favorite.awayScore
        matchTime.text = changedTime
        itemView.setOnClickListener { listener(favorite) }
    }
}
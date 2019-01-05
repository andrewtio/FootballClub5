package com.andrew.associate.footballappfinal.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.match.Match
import com.andrew.associate.footballappfinal.utils.formatToGMT
import kotlinx.android.synthetic.main.item_list_match.view.*
import java.text.SimpleDateFormat

class SearchGameAdapter (private var eDI: List<Match>
                         , private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<SearchViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchViewHolder {
        val v = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_list_match,p0,false)
        return SearchViewHolder(v)
    }

    override fun getItemCount(): Int = eDI.size

    override fun onBindViewHolder(p0: SearchViewHolder, p1: Int) {
        p0.bind(eDI[p1], listener)
    }

}

class SearchViewHolder(val v: View): RecyclerView.ViewHolder(v){

    fun bind (gameData: Match, listener: (Match) -> Unit){

        val timeChanger = formatToGMT(gameData.matchDate, gameData.matchTime)
        val dateFormat = SimpleDateFormat("E, dd MMM yyyy")
        val timeFormat = SimpleDateFormat("HH:mm")
        val date = dateFormat.format(timeChanger)
        val changedTime = timeFormat.format(timeChanger)

        itemView.game_date_tv.text = date
        itemView.game_time_tv.text = changedTime
        itemView.home_club_tv.text = gameData.homeTeam
        itemView.away_club_tv.text = gameData.awayTeam
        itemView.home_point_tv.text = gameData.homeScore
        itemView.away_point_tv.text = gameData.awayScore

        itemView.setOnClickListener{
            listener(gameData)
        }
    }

}
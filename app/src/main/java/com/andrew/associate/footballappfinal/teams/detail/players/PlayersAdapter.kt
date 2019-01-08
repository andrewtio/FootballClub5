package com.andrew.associate.footballappfinal.teams.detail.players

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.View.TEXT_ALIGNMENT_GRAVITY
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.R.id.center_vertical
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayersAdapter(private val players: List<Player>,
                     private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun getItemCount(): Int = players.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.player_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.player_name
                    textSize = 16f
                    textAlignment = TEXT_ALIGNMENT_CENTER
                }.lparams{
                    topMargin = dip(18)
                    leftMargin = dip(7)
                    marginStart = dip(7)
                }

                textView {
                    id = R.id.player_position
                    textSize = 16f
                    textAlignment = TEXT_ALIGNMENT_CENTER
                }.lparams{
                    topMargin = dip(18)
                    leftMargin = dip(7)
                    rightMargin = dip(7)
                }

            }
        }
    }

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerBadge: ImageView = view.find(R.id.player_badge)
    private val playerName: TextView = view.find(R.id.player_name)
    private val playerPosition: TextView = view.find(R.id.player_position)

    fun bindItem(player: Player, listener: (Player) -> Unit){
        Picasso.get().load(player.strCutOut).into(playerBadge)
        playerName.text = player.strPlayer
        playerPosition.text = player.strPosition
        itemView.setOnClickListener { listener(player) }
    }
}
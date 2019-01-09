package com.andrew.associate.footballappfinal.teams.detail.players.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.teams.detail.players.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class PlayerDetailActivity : AppCompatActivity() {

    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var players: Player

    private lateinit var progressBar: ProgressBar

    private lateinit var playerFanArt: ImageView
    private lateinit var playerCutOut: ImageView
    private lateinit var playerName: TextView
    private lateinit var playerWeight: TextView
    private lateinit var playerHeight: TextView
    private lateinit var playerPosition: TextView
    private lateinit var playerDescription: TextView

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

//        val intent = intent
//        id = intent.getStringExtra("id")

        players = intent.getParcelableExtra("data_player")

        Picasso.get().load(players.strFanArt1).into(player_fanart)
        Picasso.get().load(players.strCutOut).into(player_cutout)
        player_name_detail.text = players.strPlayer
        player_weight.text = players.strWeight
        player_height.text = players.strHeight
        player_position.text = players.strPosition
        player_description.text = players.strDescriptionEN

        supportActionBar?.title = "Player Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

}

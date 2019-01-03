package com.andrew.associate.footballappfinal.teams.detail.players.detail

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.teams.detail.players.Player
import com.andrew.associate.footballappfinal.utils.invisible
import com.andrew.associate.footballappfinal.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.*

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

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

//        val intent = intent
//        id = intent.getStringExtra("id")

        players = intent.getParcelableExtra("player_data")

        Picasso.get().load(players.strFanArt1).into(player_fanart)
        Picasso.get().load(players.strCutOut).into(player_cutout)
        player_name_detail.text = players.strPlayer
        player_weight.text = players.strWeight
        player_height.text = players.strHeight
        player_position.text = players.strPosition
        player_description.text = players.strDescriptionEN

        supportActionBar?.title = "Player Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


//        linearLayout(){
//            lparams(width = matchParent, height = wrapContent)
//            orientation = LinearLayout.VERTICAL
//            backgroundColor = Color.WHITE
//
//                scrollView{
//                    isVerticalScrollBarEnabled = false
//                    relativeLayout {
//                        lparams(width = matchParent, height = wrapContent)
//
//                        linearLayout{
//                            lparams(width = matchParent, height = wrapContent)
//                            padding = dip(10)
//                            orientation = LinearLayout.VERTICAL
//                            gravity = Gravity.CENTER_HORIZONTAL
//
//                            playerFanArt = imageView {}.lparams(height = dip(75))
//
//                            playerCutOut = imageView {}.lparams(height = dip(75))
//
//                            playerName = textView{
//                                this.gravity = Gravity.CENTER
//                                textSize = 20f
//                            }.lparams{
//                                topMargin = dip(5)
//                            }
//
//                            playerWeight = textView{
//                                this.gravity = Gravity.CENTER
//                            }
//
//                            playerHeight = textView{
//                                this.gravity = Gravity.CENTER
//                            }
//
//                            playerPosition = textView{
//                                this.gravity = Gravity.CENTER
//                            }
//
//                            playerDescription = textView().lparams{
//                                topMargin = dip(20)
//                            }
//                        }
//                    }
//                }
//            }
        }


//    override fun showLoading() {
//        progressBar.visible()
//    }
//
//    override fun hideLoading() {
//        progressBar.invisible()
//    }
//
//    override fun showPlayerDetail(data: List<Player>) {
//        players = Player(data[0].IdTeam,
//                    data[0].strPlayer,
//                    data[0].strCutOut,
//                    data[0].strFanArt1,
//                    data[0].strWeight,
//                    data[0].strHeight,
//                    data[0].strDescriptionEN,
//                    data[0].strPosition)
//        Picasso.get().load(data[0].strFanArt1).into(playerFanArt)
//        Picasso.get().load(data[0].strCutOut).into(playerCutOut)
//        playerName.text = data[0].strPlayer
//        playerWeight.text = data[0].strWeight
//        playerHeight.text = data[0].strHeight
//        playerPosition.text = data[0].strPosition
//        playerDescription.text = data[0].strDescriptionEN
//    }





//        swipeRefresh.onRefresh{
//            presenter.getTeamDetail(id)
//        }
}


//    override fun showLoading() {
//        progressBar.visible()
//    }

//    override fun hideLoading() {
//        progressBar.invisible()
//    }

//    override fun showTeamDetail(data: List<Team>) {
//        teams = Team(data[0].teamId,
//            data[0].teamName,
//            data[0].teamBadge)
//        swipeRefresh.isRefreshing = false
//        Picasso.get().load(data[0].teamBadge).into(teamBadge)
//        teamName.text = data[0].teamName
//        teamDescription.text = data[0].teamDescription
//        teamFormedYear.text = data[0].teamFormedYear
//        teamStadium.text = data[0].teamStadium
//
//    }

package com.andrew.associate.footballappfinal.teams.detail.description


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.R.color.colorAccent
import com.andrew.associate.footballappfinal.api.ApiRepository
import com.andrew.associate.footballappfinal.teams.Team
import com.andrew.associate.footballappfinal.teams.TeamsAdapter
import com.andrew.associate.footballappfinal.teams.TeamsPresenter
import com.andrew.associate.footballappfinal.teams.TeamsView
import com.andrew.associate.footballappfinal.teams.detail.TeamDetailActivity
import com.andrew.associate.footballappfinal.utils.invisible
import com.andrew.associate.footballappfinal.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.abc_alert_dialog_material.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DescriptionFragment : Fragment(), AnkoComponent<Context> {

    private lateinit var teams: Team
    private lateinit var descriptionTeam: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        teams = activity?.intent!!.getParcelableExtra("data")
        descriptionTeam.text = teams.teamDescription

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

                scrollView{
                    isVerticalScrollBarEnabled = true
                    lparams (width = matchParent, height = wrapContent)

                    descriptionTeam = textView().lparams {
                        topMargin = dip(20)
                    }
                }
            }
        }
    }

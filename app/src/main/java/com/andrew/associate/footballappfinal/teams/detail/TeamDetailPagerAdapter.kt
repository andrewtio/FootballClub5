package com.andrew.associate.footballappfinal.teams.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.andrew.associate.footballappfinal.teams.detail.description.DescriptionFragment
import com.andrew.associate.footballappfinal.teams.detail.players.PlayersFragment

class TeamDetailPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        DescriptionFragment(),
        PlayersFragment()
    )

    override fun getItem(p0: Int): Fragment? {
        return when(p0) {
            0 -> DescriptionFragment()
            1 -> PlayersFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(p0: Int):CharSequence?{
        return when(p0){
            0 -> "Description"
            1 -> "Players"
            else -> null
        }
    }

}

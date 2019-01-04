package com.andrew.associate.footballappfinal.match

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.andrew.associate.footballappfinal.match.nextmatch.NextMatchFragment
import com.andrew.associate.footballappfinal.match.prevmatch.PrevMatchFragment

class MatchPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        PrevMatchFragment(),
        NextMatchFragment()
    )

    override fun getItem(p0: Int): Fragment? {
        return when(p0) {
            0 -> NextMatchFragment()
            1 -> PrevMatchFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(p0: Int):CharSequence?{
        return when(p0){
            0 -> "Next Match"
            1 -> "Prev Match"
            else -> null
        }
    }
}
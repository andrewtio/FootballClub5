package com.andrew.associate.footballappfinal.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrew.associate.footballappfinal.R
import kotlinx.android.synthetic.main.fragment_match.*


class MatchFragment : Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        match_viewpager.adapter = MatchPagerAdapter(childFragmentManager)
        tabs_match.setupWithViewPager(match_viewpager)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }
}

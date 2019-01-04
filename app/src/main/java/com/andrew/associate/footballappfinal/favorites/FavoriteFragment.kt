package com.andrew.associate.footballappfinal.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.match.MatchPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_match.*

class FavoriteFragment : Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favorite_viewpager.adapter = FavoritePagerAdapter(childFragmentManager)
        tabs_favorite.setupWithViewPager(favorite_viewpager)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }
}
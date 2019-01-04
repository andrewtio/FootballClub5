package com.andrew.associate.footballappfinal.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.andrew.associate.footballappfinal.favorites.favoritematch.FavoritesMatchFragment
import com.andrew.associate.footballappfinal.favorites.favoriteteam.FavoriteTeamsFragment
import com.andrew.associate.footballappfinal.match.nextmatch.NextMatchFragment
import com.andrew.associate.footballappfinal.match.prevmatch.PrevMatchFragment

class FavoritePagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        FavoritesMatchFragment(),
        FavoriteTeamsFragment()
    )

    override fun getItem(p0: Int): Fragment? {
        return when(p0) {
            0 -> FavoritesMatchFragment()
            1 -> FavoriteTeamsFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(p0: Int):CharSequence?{
        return when(p0){
            0 -> "Favorite Match"
            1 -> "Favorite Team"
            else -> null
        }
    }
}
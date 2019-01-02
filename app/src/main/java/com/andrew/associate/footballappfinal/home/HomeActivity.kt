package com.andrew.associate.footballappfinal.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.R.id.favorites
import com.andrew.associate.footballappfinal.R.id.team
import com.andrew.associate.footballappfinal.R.layout.activity_home
import com.andrew.associate.footballappfinal.favorites.FavoriteTeamsFragment
import com.andrew.associate.footballappfinal.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
//                next_match -> {
//                    loadMatchFragment(savedInstanceState)
//                }

                team -> {
                    loadTeamsFragment(savedInstanceState)
                }

                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = team
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                .commit()
        }
    }
}
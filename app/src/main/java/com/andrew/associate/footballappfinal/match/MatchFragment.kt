package com.andrew.associate.footballappfinal.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.andrew.associate.footballappfinal.R
import com.andrew.associate.footballappfinal.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity


class MatchFragment : Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        match_viewpager.adapter = MatchPagerAdapter(childFragmentManager)
        tabs_match.setupWithViewPager(match_viewpager)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_search_button -> {
                startActivity<SearchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

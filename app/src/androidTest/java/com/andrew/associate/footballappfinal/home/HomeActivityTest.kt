package com.andrew.associate.footballappfinal.home

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.andrew.associate.footballappfinal.R.id.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{

    @Rule
    @JvmField var activityTestRule = ActivityTestRule(HomeActivity::class.java)


    @Test
    fun testFavGameBehaviour(){

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(list_match_next)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(list_match_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Espresso.onView(ViewMatchers.withId(list_match_next))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withText("Added to favorite match"))
//            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(list_match_next)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(list_match_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Espresso.onView(ViewMatchers.withId(list_match_next))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withText("Removed from favorite match"))
//            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    fun testFavClubBehaviour(){

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(bottom_navigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(team)).perform(ViewActions.click())

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(list_team)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(list_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Espresso.onView(ViewMatchers.withId(list_team))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

//        onView(withText("Club Detail")).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
//        onView(withText("Added to Your Favorite Club")).check(matches(isDisplayed()))
        Espresso.pressBack()

        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(list_team)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(list_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Espresso.onView(ViewMatchers.withId(list_team))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

//        onView(withText("Club Detail")).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
//        onView(withText("Removed from your Favorite Club")).check(matches(isDisplayed()))
        Espresso.pressBack()
    }
}
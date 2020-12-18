package com.dicoding.jetpack3

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.dicoding.jetpack3.ui.home.HomeActivity
import com.dicoding.jetpack3.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }
    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun movieTest(){
        onView(withId(R.id.navigation_movies)).perform(click())
        onView(withId(R.id.movie_rv)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_rv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_action)).perform(click())

        onView(withId(R.id.content_detail_name)).perform(click())
        onView(withId(R.id.detail_image)).check(matches(isDisplayed()))
        onView(withId(R.id.content_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.content_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.content_overview)).check(matches(isDisplayed()))

    }

    @Test
    fun seriesTest(){
        onView(withId(R.id.navigation_tv_series)).perform(click())
        onView(withId(R.id.series_rv)).check(matches(isDisplayed()))
        onView(withId(R.id.series_rv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_action)).perform(click())
        onView(withId(R.id.content_detail_name)).perform(click())
        onView(withId(R.id.detail_image)).check(matches(isDisplayed()))
        onView(withId(R.id.content_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.content_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.content_overview)).check(matches(isDisplayed()))

    }

    @Test
    fun favoriteTest(){
        onView(withId(R.id.navigation_favourites)).perform(click())
        onView(withId(R.id.favorite_movie_rv)).check(matches(isDisplayed()))
        onView(ViewMatchers.withText("TV SERIES")).perform(click())
        onView(withId(R.id.favorite_series_rv)).check(matches(isDisplayed()))
    }
}
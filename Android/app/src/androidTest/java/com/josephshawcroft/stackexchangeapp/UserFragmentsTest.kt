package com.josephshawcroft.stackexchangeapp

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.josephshawcroft.stackexchangeapp.userlist.UserListAdapter
import com.josephshawcroft.stackexchangeapp.utils.CountingIdlingResourceSingleton
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class UserFragmentsTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance()
            .register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance()
            .unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun testNavigation() {
        val navController = activityRule.activity.findNavController(R.id.nav_host_fragment)

        onView(withId(R.id.searchText)).perform(clearText(), typeText("EpicPandaForce"))
        onView(withId(R.id.searchButton)).perform(click())
        onView(withId(R.id.userList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<UserListAdapter.ViewHolder>(
                0,
                click()
            )
        )

        //should be on the SelectedUserFragment at this point
        assertEquals(
            "NavController is not on the Selected User Fragment",
            navController.currentDestination?.id,
            R.id.selectedUserFragment
        )

        //navigate backwards and check we are back on the User List
        onView(isRoot()).perform(pressBack())
        assertEquals(
            "NavController is not on the User List Fragment",
            navController.currentDestination?.id,
            R.id.userListFragment
        )
    }


    //this test is to make sure emojis don't break the app more than anything
    @Test
    fun emojiTest() {
        onView(withId(R.id.searchText)).perform(clearText(), replaceText("\"\uD83D\uDE00\"\n"))
        onView(withId(R.id.searchButton)).perform(click())
        onView(withId(R.id.errorText)).check(matches(not(isDisplayed())))
    }
}
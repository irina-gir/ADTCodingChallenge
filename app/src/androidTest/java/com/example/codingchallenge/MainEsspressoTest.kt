package com.example.codingchallenge

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.codingchallenge.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainEsspressoTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun onClick() {
        Espresso
            .onView(
                ViewMatchers.withId(R.id.recycler_view))
            .check(
                ViewAssertions.matches(ViewMatchers.isDisplayed())
            )
    }
}
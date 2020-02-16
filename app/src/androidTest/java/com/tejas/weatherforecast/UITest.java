package com.tejas.weatherforecast;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tejas.weatherforecast.view.WeatherForecastActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {

    @Rule
    public ActivityTestRule<WeatherForecastActivity> activityRule
            = new ActivityTestRule<>(WeatherForecastActivity.class);

    @Test
    public void lastItem_NotDisplayed() {
        // Last item should not exist if the list wasn't scrolled down.
        onView(withText("Tokyo")).check(doesNotExist());
    }

    @Test
    public void checkAppBar_valid_Test() throws Exception {
        onView(withId(R.id.title_text)).check(matches(withText("Weather Forecast")));
    }

    @Test
    public void clickSpinner_Test() throws Exception {
        onView(withId(R.id.spinner)).perform(click());
    }

    @Test
    public void clickSpinnerAndClickSpinnerItemLondon_Test() throws Exception {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("London"))).perform(click());
    }

    @Test
    public void clickSpinnerAndClickSpinnerItemTokyo_Test() throws Exception {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Tokyo"))).perform(click());
    }

    @Test
    public void clickSpinnerAndClickSpinnerItemBangalore_Test() throws Exception {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Bangalore"))).perform(click());
    }

    @Test
    public void list_scroll_Test() throws Exception {
        onView(ViewMatchers.withId(R.id.rv_forecast)).perform(ViewActions.swipeUp());
    }

    @Test
    public void check_Date_Time_Text_Test() throws Exception {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Bangalore"))).perform(click());

        Thread.sleep(3000);

        onView(withRecyclerView(R.id.rv_forecast)
                .atPositionOnView(1, R.id.tv_date_time))
                .check(matches(withText(containsString("Date/Time"))));
    }

    @Test
    public void check_Temperature_Text_Test() throws Exception {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Mumbai"))).perform(click());

        Thread.sleep(3000);

        onView(withRecyclerView(R.id.rv_forecast)
                .atPositionOnView(1, R.id.tv_temp))
                .check(matches(withText(containsString("Temp (Min|Max)"))));
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}
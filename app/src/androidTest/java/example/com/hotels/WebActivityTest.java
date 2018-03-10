package example.com.hotels;


import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.com.hotels.ui.web.WebActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class WebActivityTest {

    @Rule
    public ActivityTestRule<WebActivity> mActivityRule = new ActivityTestRule<>(WebActivity.class, false, false);

    @Before
    public void setUp() {
        mActivityRule.launchActivity(null); //launches the test activity
    }

    @Test
    public void testWebViewBackWithUpArrow() {
        onView(withId(R.id.webView)).check(matches(isDisplayed()));
        onView(withContentDescription(R.string.abc_action_bar_up_description)).check(matches(isDisplayed()));
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
    }

    @Test
    public void testWebViewBackWithBackButton() {
        onView(withId(R.id.webView)).check(matches(isDisplayed()));
        onView(withContentDescription(R.string.abc_action_bar_up_description)).check(matches(isDisplayed()));
    }

}

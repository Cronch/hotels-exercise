package example.com.hotels;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.com.hotels.utils.custom.RecyclerViewMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() {
        mActivityRule.launchActivity(null); //launches the test activity
    }

    @Test
    public void testWebView() {
        checkNavigationMenuVisible();

        onView(withId(R.id.navigation_web)).perform(click());
        onView(withId(R.id.webView)).check(matches(isDisplayed()));
        Espresso.pressBack();
        checkNavigationMenuVisible();
    }

    @Test
    public void testBasicNavigationAndData() {
        checkNavigationMenuVisible();
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ratingBar)).check(matches(isDisplayed()));
        onView(withId(R.id.description)).check(matches(isDisplayed()));
        onView(withId(R.id.detailsContainer)).check(matches(hasDescendant(withId(R.id.name))));
        onView(withId(R.id.detailsContainer)).check(matches(hasDescendant(withId(R.id.image))));

        Espresso.pressBack();
        checkNavigationMenuVisible();
    }

    @Test
    public void testBasicNavigationAndDataWithComments() {
        checkNavigationMenuVisible();

        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        checkDetailsViews();
        onView(withId(R.id.viewComments)).check(matches(isDisplayed()));
        onView(withId(R.id.viewComments)).perform(click());

        // Check first comment
        onView(withRecyclerView(R.id.commentsList).atPosition(0)).check(matches(hasDescendant(withId(R.id.goodComments))));
        onView(withRecyclerView(R.id.commentsList).atPosition(0)).check(matches(hasDescendant(withId(R.id.userName))));

        // Check second comment
        onView(withRecyclerView(R.id.commentsList).atPosition(1)).check(matches(hasDescendant(withId(R.id.goodComments))));
        onView(withRecyclerView(R.id.commentsList).atPosition(1)).check(matches(hasDescendant(withId(R.id.badComments))));
        onView(withRecyclerView(R.id.commentsList).atPosition(1)).check(matches(hasDescendant(withId(R.id.userName))));

        Espresso.pressBack();
        checkDetailsViews();
        Espresso.pressBack();
        checkNavigationMenuVisible();
    }

    private void checkDetailsViews() {
        onView(withId(R.id.ratingBar)).check(matches(isDisplayed()));
        onView(withId(R.id.description)).check(matches(isDisplayed()));
        onView(withId(R.id.detailsContainer)).check(matches(hasDescendant(withId(R.id.name))));
        onView(withId(R.id.detailsContainer)).check(matches(hasDescendant(withId(R.id.image))));
    }

    private void checkNavigationMenuVisible() {
        onView(withId(R.id.navigation)).check(matches(isDisplayed()));
        onView(withId(R.id.navigation_home)).check(matches(isDisplayed()));
        onView(withId(R.id.navigation_web)).check(matches(isDisplayed()));
    }

    private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

}

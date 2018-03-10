package example.com.hotels;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import example.com.hotels.ui.web.WebActivity;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void testMainActivityCreation() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        BottomNavigationView navigationView = activity.findViewById(R.id.navigation);
        Fragment fragment = activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);

        Assert.assertNotNull(navigationView);
        Assert.assertNotNull(fragment);
    }

    @Test
    public void testMainActivityNoFragmentIfInstanceRestored() {
        Bundle savedInstanceState = new Bundle();
        MainActivity activity = Robolectric.buildActivity(MainActivity.class)
                .create(savedInstanceState)
                .get();
        Fragment fragment = activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);

        Assert.assertNotNull(activity);
        Assert.assertNull(fragment);
    }

    @Test
    public void testHomeNavigation() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class)
                .create(new Bundle())
                .get();
        BottomNavigationView navigationView = activity.findViewById(R.id.navigation);
        assertEquals(R.id.navigation_home, navigationView.getSelectedItemId());

        navigationView.setSelectedItemId(R.id.navigation_home);
        Fragment fragment = activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);
        Assert.assertNotNull(fragment);
    }

    @Test
    public void testHomeWeb() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class)
                .create(new Bundle())
                .get();
        BottomNavigationView navigationView = activity.findViewById(R.id.navigation);
        navigationView.setSelectedItemId(R.id.navigation_web);

        Intent expectedIntent = new Intent(activity, WebActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

}
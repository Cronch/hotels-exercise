package example.com.hotels;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.hotels.ui.list.ListFragment;
import example.com.hotels.ui.web.WebActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = (@NonNull MenuItem item) -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Log.d(LOG_TAG, "Home navigation pressed");
                showListScreen();
                return true;
            /*case R.id.navigation_favorites:
                showFavoritesScreen();
                return true;*/
            case R.id.navigation_web:
                Log.d(LOG_TAG, "Web navigation pressed");
                showWebScreen();
                return false;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationListener);

        if (savedInstanceState == null) {
            showListScreen();
        }
    }

    private void showListScreen() {
        cleanFragmentBackStack();
        ListFragment listFragment = new ListFragment();
        final String tag = "list";
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, listFragment, tag)
                .commit();
    }

    private void cleanFragmentBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void showFavoritesScreen() {
        // TODO: Implement
    }

    private void showWebScreen() {
        startActivity(new Intent(this, WebActivity.class));
    }

}

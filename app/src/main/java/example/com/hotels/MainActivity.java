package example.com.hotels;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import example.com.hotels.ui.list.ListFragment;
import example.com.hotels.ui.web.WebActivity;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = (@NonNull MenuItem item) -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showListFragment();
                return true;
            /*case R.id.navigation_favorites:
                showFavorites();
                return true;*/
            case R.id.navigation_web:
                showWeb();
                return false;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationListener);

        showListFragment();
    }

    private void showListFragment() {
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

    private void showFavorites() {
        // TODO: Implement
    }

    private void showWeb() {
        startActivity(new Intent(this, WebActivity.class));
    }

}

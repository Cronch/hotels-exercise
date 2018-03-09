package example.com.hotels;

import android.app.Application;
import android.content.Context;

import example.com.hotels.injection.app.AppComponent;
import example.com.hotels.injection.app.DaggerAppComponent;

public class CustomApplication extends Application {

    private static AppComponent appComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        setUpDagger();
    }

    private void setUpDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();

        appComponent.inject(this);
    }

    // Dependency injection required
    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
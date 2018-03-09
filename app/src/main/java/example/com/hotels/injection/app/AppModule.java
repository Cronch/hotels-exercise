package example.com.hotels.injection.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.com.hotels.CustomApplication;

@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(CustomApplication application) {
        return application.getApplicationContext();
    }

}


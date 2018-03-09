package example.com.hotels.injection.app;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import example.com.hotels.CustomApplication;
import example.com.hotels.ui.details.DetailsFragment;
import example.com.hotels.ui.list.ListFragment;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, ManagerModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(CustomApplication application);

        AppComponent build();
    }

    void inject(CustomApplication application);

    void inject(ListFragment fragment);

    void inject(DetailsFragment fragment);
}

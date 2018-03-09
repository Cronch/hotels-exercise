package example.com.hotels.injection.app;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.com.hotels.data.HotelManager;
import example.com.hotels.data.network.APIServices;

@Module
public class ManagerModule {

    @Provides
    @Singleton
    static HotelManager provideHotelManager(APIServices apiServices) {
        return new HotelManager(apiServices);
    }

}


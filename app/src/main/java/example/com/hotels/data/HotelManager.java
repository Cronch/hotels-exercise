package example.com.hotels.data;

import android.util.Log;

import javax.inject.Inject;

import example.com.hotels.data.model.Hotel;
import example.com.hotels.data.network.APIServices;
import example.com.hotels.data.network.BaseDataManager;
import example.com.hotels.data.network.parser.HotelParser;
import io.reactivex.Observable;

public class HotelManager extends BaseDataManager<HotelParser> {

    private static final String LOG_TAG = "HotelManager";

    @Inject
    public HotelManager(APIServices apiServices) {
        super(apiServices);
    }

    public Observable<HotelParser> getHotels() {
        // TODO: Get data from DB
        Log.d(LOG_TAG, "Get hotels");
        return apiServices.getHotels();
    }

    public Observable<HotelParser> getHotelDetails(Long id) {
        // TODO: Get data from DB
        Log.d(LOG_TAG, "Get hotel details #" + id);
        return apiServices.getHotelDetails(id);
    }

}

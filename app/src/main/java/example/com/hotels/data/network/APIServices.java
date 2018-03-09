package example.com.hotels.data.network;

import example.com.hotels.data.network.parser.HotelParser;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIServices {
    String URL_HOTELS = "/hotels";
    String URL_HOTEL_DETAILS = "/hotels/{id}";

    @GET(URL_HOTELS)
    Observable<HotelParser> getHotels();

    @GET(URL_HOTEL_DETAILS)
    Observable<HotelParser> getHotelDetails(@Path("id") Long id);

}

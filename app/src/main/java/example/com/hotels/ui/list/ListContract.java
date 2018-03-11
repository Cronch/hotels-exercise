package example.com.hotels.ui.list;

import java.util.List;

import example.com.hotels.data.model.Hotel;

public interface ListContract {

    interface View {
        void onSuccess(List<Hotel> list);

        void onError(Throwable throwable);

        void showHotelDetails(Hotel hotel);
    }

    interface Presenter {
        void onViewAttached();

        void onViewDetached();

        void getHotels();

        void onHotelClick(Hotel hotel);
    }

}
package example.com.hotels.ui.details;

import example.com.hotels.data.model.Hotel;

public interface DetailsContract {

    interface View {
        void onError(Throwable throwable);

        void onSuccess(Hotel hotel);

        void showComments(Hotel hotel);

        void zoomImage(Hotel hotel);
    }

    interface Presenter {
        void onViewAttached();

        void onViewDetached();

        void getHotelDetails(Long hotelId);

        void showComments(Hotel hotel);

        void zoomImage(Hotel hotel);
    }

}
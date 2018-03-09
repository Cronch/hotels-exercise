package example.com.hotels.ui.list;

import java.util.List;

import example.com.hotels.data.model.Hotel;

public interface ListContract {

    interface View {
        void onSuccess(List<Hotel> list);

        void onError(Throwable throwable);
    }

    interface Presenter {
        void getHotels();
    }

}
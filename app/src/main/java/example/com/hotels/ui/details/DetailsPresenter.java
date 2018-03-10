package example.com.hotels.ui.details;

import android.util.Log;

import example.com.hotels.data.HotelManager;
import example.com.hotels.data.model.Hotel;
import example.com.hotels.data.network.parser.HotelParser;
import example.com.hotels.ui.list.ListContract;
import io.reactivex.Scheduler;

public class DetailsPresenter implements DetailsContract.Presenter {

    private static final String LOG_TAG = "DetailsPresenter";

    private DetailsContract.View view;
    private HotelManager dataManager;
    private Scheduler processScheduler;
    private Scheduler androidScheduler;

    DetailsPresenter(DetailsContract.View view, HotelManager dataManager, Scheduler androidScheduler, Scheduler processScheduler) {
        this.androidScheduler = androidScheduler;
        this.processScheduler = processScheduler;
        this.view = view;
        this.dataManager = dataManager;
    }

    private void onSuccess(HotelParser parser) {
        Log.d(LOG_TAG, "Hotels retrieved successfully");
        view.onSuccess(parser.getItem());
    }

    @Override
    public void getHotelDetails(Long id) {
        Log.d(LOG_TAG, "Getting hotels");
        dataManager.getHotelDetails(id)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe(this::onSuccess, view::onError);
    }

}
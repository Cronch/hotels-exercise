package example.com.hotels.ui.list;

import android.util.Log;

import example.com.hotels.data.HotelManager;
import example.com.hotels.data.network.parser.HotelParser;
import io.reactivex.Scheduler;

public class ListPresenter implements ListContract.Presenter {

    private static final String LOG_TAG = "ListPresenter";

    private ListContract.View view;
    private HotelManager dataManager;
    private Scheduler processScheduler;
    private Scheduler androidScheduler;

    ListPresenter(ListContract.View view, HotelManager dataManager, Scheduler androidScheduler, Scheduler processScheduler) {
        this.androidScheduler = androidScheduler;
        this.processScheduler = processScheduler;
        this.view = view;
        this.dataManager = dataManager;
    }

    @Override
    public void getHotels() {
        Log.d(LOG_TAG, "Getting hotels");
        dataManager.getHotels()
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe(this::onSuccess, view::onError);
    }

    private void onSuccess(HotelParser parser) {
        Log.d(LOG_TAG, "Hotels retrieved successfully");
        view.onSuccess(parser.getItemList());
    }

}

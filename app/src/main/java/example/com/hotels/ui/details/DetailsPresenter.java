package example.com.hotels.ui.details;

import android.util.Log;

import example.com.hotels.data.HotelManager;
import example.com.hotels.data.model.Hotel;
import example.com.hotels.data.network.parser.HotelParser;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public class DetailsPresenter implements DetailsContract.Presenter {

    private static final String LOG_TAG = "DetailsPresenter";

    private DetailsContract.View view;
    private HotelManager dataManager;
    private Scheduler processScheduler;
    private Scheduler androidScheduler;
    private CompositeDisposable compositeDisposable;

    DetailsPresenter(DetailsContract.View view, HotelManager dataManager, Scheduler androidScheduler, Scheduler processScheduler) {
        this.androidScheduler = androidScheduler;
        this.processScheduler = processScheduler;
        this.view = view;
        this.dataManager = dataManager;
    }

    @Override
    public void onViewAttached() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onViewDetached() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void getHotelDetails(Long id) {
        Log.d(LOG_TAG, "Getting hotels");
        dataManager.getHotelDetails(id)
                .doOnSubscribe(compositeDisposable::add)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe(this::onSuccess, this::onError);
    }

    private void onSuccess(HotelParser parser) {
        Hotel hotel = parser.getItem();
        Log.d(LOG_TAG, "Hotel details success for #" + hotel.getId());
        view.onSuccess(hotel);
    }

    private void onError(Throwable throwable) {
        Log.e(LOG_TAG, "Error retrieving details", throwable);
        view.onError(throwable);
    }

}

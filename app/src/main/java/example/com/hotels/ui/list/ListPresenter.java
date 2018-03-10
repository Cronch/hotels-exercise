package example.com.hotels.ui.list;

import android.util.Log;

import example.com.hotels.data.HotelManager;
import example.com.hotels.data.network.parser.HotelParser;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public class ListPresenter implements ListContract.Presenter {

    private static final String LOG_TAG = "ListPresenter";

    private ListContract.View view;
    private HotelManager dataManager;
    private Scheduler processScheduler;
    private Scheduler androidScheduler;
    private CompositeDisposable compositeDisposable;

    ListPresenter(ListContract.View view, HotelManager dataManager, Scheduler androidScheduler, Scheduler processScheduler) {
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
    public void getHotels() {
        Log.d(LOG_TAG, "Getting hotels");
        dataManager.getHotels()
                .doOnSubscribe(compositeDisposable::add)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe(this::onSuccess, this::onError);
    }

    private void onSuccess(HotelParser parser) {
        Log.d(LOG_TAG, "Hotels retrieved successfully");
        view.onSuccess(parser.getItemList());
    }

    private void onError(Throwable throwable) {
        Log.e(LOG_TAG, "Hotels error", throwable);
        view.onError(throwable);
    }

}

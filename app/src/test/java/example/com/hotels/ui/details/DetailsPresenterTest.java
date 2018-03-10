package example.com.hotels.ui.details;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import example.com.hotels.data.HotelManager;
import example.com.hotels.data.model.Hotel;
import example.com.hotels.data.network.parser.HotelParser;
import example.com.hotels.ui.list.ListContract;
import example.com.hotels.ui.list.ListPresenter;
import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

@RunWith(MockitoJUnitRunner.class)
public class DetailsPresenterTest {

    private DetailsContract.Presenter detailsPresenter;
    private TestScheduler processScheduler;
    private TestScheduler androidScheduler;

    @Mock
    private HotelManager hotelManager;
    @Mock
    private DetailsContract.View view;
    @Mock
    private HotelParser hotelParser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Mock scheduler using RxJava TestScheduler.
        processScheduler = new TestScheduler();
        androidScheduler = new TestScheduler();

        detailsPresenter = new DetailsPresenter(view, hotelManager, androidScheduler, processScheduler);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(hotelManager, view);
    }

    @Test
    public void testGetHotelsOk() {
        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();
        Mockito.when(hotelParser.getItem()).thenReturn(hotel);
        Mockito.when(hotelManager.getHotelDetails(1L)).thenReturn(Observable.just(hotelParser));

        detailsPresenter.onViewAttached();
        detailsPresenter.getHotelDetails(1L);
        processScheduler.triggerActions();
        androidScheduler.triggerActions();

        Mockito.verify(hotelManager).getHotelDetails(Mockito.eq(1L));
        Mockito.verify(view).onSuccess(Mockito.eq(hotel));
    }

    @Test
    public void testGetHotelsError() {
        Throwable error = new Throwable("message");
        Mockito.when(hotelManager.getHotelDetails(1L)).thenReturn(Observable.error(error));

        detailsPresenter.onViewAttached();
        detailsPresenter.getHotelDetails(1L);
        processScheduler.triggerActions();
        androidScheduler.triggerActions();

        Mockito.verify(hotelManager).getHotelDetails(Mockito.eq(1L));
        Mockito.verify(view).onError(Mockito.eq(error));
    }

    @Test
    public void testShowComments() {
        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();

        detailsPresenter.showComments(hotel);

        Mockito.verify(view).showComments(Mockito.eq(hotel));
    }

    @Test
    public void testZoomImage() {
        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();

        detailsPresenter.zoomImage(hotel);

        Mockito.verify(view).zoomImage(Mockito.eq(hotel));
    }

}

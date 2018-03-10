package example.com.hotels.ui.list;


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
import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

@RunWith(MockitoJUnitRunner.class)
public class ListPresenterTest {

    private ListContract.Presenter listPresenter;
    private TestScheduler processScheduler;
    private TestScheduler androidScheduler;

    @Mock
    private HotelManager hotelManager;
    @Mock
    private ListContract.View view;
    @Mock
    private HotelParser hotelParser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Mock scheduler using RxJava TestScheduler.
        processScheduler = new TestScheduler();
        androidScheduler = new TestScheduler();

        listPresenter = new ListPresenter(view, hotelManager, androidScheduler, processScheduler);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(hotelManager, view);
    }

    @Test
    public void testGetHotelsOk() {
        List<Hotel> list = new ArrayList<>();
        Mockito.when(hotelParser.getItemList()).thenReturn(list);
        Mockito.when(hotelManager.getHotels()).thenReturn(Observable.just(hotelParser));

        listPresenter.onViewAttached();
        listPresenter.getHotels();
        processScheduler.triggerActions();
        androidScheduler.triggerActions();

        Mockito.verify(hotelManager).getHotels();
        Mockito.verify(view).onSuccess(Mockito.eq(list));
    }

    @Test
    public void testGetHotelsError() {
        Throwable error = new Throwable("message");
        Mockito.when(hotelManager.getHotels()).thenReturn(Observable.error(error));

        listPresenter.onViewAttached();
        listPresenter.getHotels();
        processScheduler.triggerActions();
        androidScheduler.triggerActions();

        Mockito.verify(hotelManager).getHotels();
        Mockito.verify(view).onError(Mockito.eq(error));
    }

    @Test
    public void testOnHotelClick() {
        Hotel hotel = Hotel.builder()
                .id(1L)
                .name("Hotel")
                .build();

        listPresenter.onHotelClick(hotel);

        Mockito.verify(view).showHotelDetails(Mockito.eq(hotel));
    }

}

package example.com.hotels.data;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import example.com.hotels.data.network.APIServices;

@RunWith(MockitoJUnitRunner.class)
public class HotelManagerTest {

    @Mock
    private APIServices apiServices;

    @InjectMocks
    private HotelManager hotelManager;

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(apiServices);
    }

    @Test
    public void testGetHotels() {
        hotelManager.getHotels();
        Mockito.verify(apiServices).getHotels();
    }

    @Test
    public void testHotelDetails() {
        hotelManager.getHotelDetails(1L);
        Mockito.verify(apiServices).getHotelDetails(Mockito.eq(1L));
    }

}

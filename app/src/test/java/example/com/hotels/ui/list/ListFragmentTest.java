package example.com.hotels.ui.list;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import example.com.hotels.MainActivity;
import example.com.hotels.R;
import example.com.hotels.data.model.Hotel;
import example.com.hotels.ui.details.DetailsFragment;

@RunWith(RobolectricTestRunner.class)
public class ListFragmentTest {

    @Test
    public void testListFragmentShowDetails() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        ListFragment fragment = (ListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);
        Assert.assertNotNull(fragment);

        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();
        fragment.showHotelDetails(hotel);

        DetailsFragment details = (DetailsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);
        Assert.assertNotNull(details);
    }

}
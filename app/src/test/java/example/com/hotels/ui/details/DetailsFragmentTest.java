package example.com.hotels.ui.details;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import example.com.hotels.MainActivity;
import example.com.hotels.R;
import example.com.hotels.data.model.Hotel;
import example.com.hotels.data.model.Review;
import example.com.hotels.data.model.User;
import example.com.hotels.ui.comments.CommentsFragment;
import example.com.hotels.ui.list.ListFragment;

@RunWith(RobolectricTestRunner.class)
public class DetailsFragmentTest {

    @Test
    public void testDetailsFragmentShowComments() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        ListFragment fragment = (ListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);

        User user = User.builder()
                .name("Juan")
                .build();
        Review review = Review.builder()
                .title("Great!")
                .user(user)
                .build();
        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(review);

        Hotel hotel = Hotel.builder()
                .id(1L)
                .reviews(reviewList)
                .build();
        fragment.showHotelDetails(hotel);

        DetailsFragment details = (DetailsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);
        Assert.assertNotNull(details);

        details.showComments(hotel);
        CommentsFragment commentsFragment = (CommentsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);
        Assert.assertNotNull(commentsFragment);
    }

    /*
     * FIXME: Zoom image is throwing an exception related to pending transactions on the fragment manager
     */
    @Test @Ignore
    public void testDetailsFragmentZoomImage() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        ListFragment fragment = (ListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);

        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();
        fragment.showHotelDetails(hotel);
        DetailsFragment details = (DetailsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.mainContainer);
        Assert.assertNotNull(details);

        details.zoomImage(hotel);
        ImageDialogFragment imageFragment = (ImageDialogFragment) activity.getSupportFragmentManager().findFragmentByTag("imageDialog");
        Assert.assertNotNull(imageFragment);
    }

}
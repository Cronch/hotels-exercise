package example.com.hotels;

import android.webkit.WebView;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import example.com.hotels.ui.web.WebActivity;

@RunWith(RobolectricTestRunner.class)
public class WebActivityTest {

    @Test
    public void testWebActivityCreation() {
        WebActivity activity = Robolectric.setupActivity(WebActivity.class);
        WebView webView = activity.findViewById(R.id.webView);

        Assert.assertNotNull(webView);
    }

}
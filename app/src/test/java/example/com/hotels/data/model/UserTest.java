package example.com.hotels.data.model;

import junit.framework.Assert;

import org.junit.Test;

public class UserTest {

    @Test
    public void testUserNameCapitalized() {
        User user = User.builder()
                .name("Roberto sanchez")
                .build();

        Assert.assertEquals("Roberto Sanchez", user.getName());
    }

    @Test
    public void testUserNameSingleWordCapitalized() {
        User user = User.builder()
                .name("juan")
                .build();

        Assert.assertEquals("Juan", user.getName());
    }

}

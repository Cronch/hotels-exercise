package example.com.hotels.data.network.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import example.com.hotels.data.model.AmenitiesEnum;
import example.com.hotels.data.model.Hotel;

public class HotelParser extends BaseParser<Hotel> {

    private List<Item> items;

    private Item hotel;

    private class Item {
        private Long id;
        private String name;
        private String main_picture;
        private Integer stars;

        private String description;
        private List<Amenity> amenities;
        private List<Review> reviews;
    }

    private class Amenity {
        private String id;
        private String description;
    }

    private class Review {
        private Comment comments;
        private User user;
    }

    private class Comment {
        private String title;
        private String good;
        private String bad;
    }

    private class User {
        private String name;
    }

    @Override
    public List<Hotel> getItemList() {
        if (items == null) {
            return Collections.emptyList();
        }

        List<Hotel> hotels = new ArrayList<>();
        for (Item item : items) {
            hotels.add(buildHotel(item));
        }

        return hotels;
    }

    @Override
    public Hotel getItem() {
        return buildHotel(hotel);
    }

    private Hotel buildHotel(Item item) {
        return Hotel.builder()
                .id(item.id)
                .name(item.name)
                .mainPicture(item.main_picture)
                .amenities(buildAmenities(item))
                .description(item.description)
                .stars(item.stars)
                .reviews(buildReviews(item))
                .build();
    }

    private ArrayList<example.com.hotels.data.model.Review> buildReviews(Item item) {
        ArrayList<example.com.hotels.data.model.Review> reviews = new ArrayList<>();

        if (item.reviews != null) {
            for (Review review : item.reviews) {
                reviews.add(buildReview(review));
            }
        }

        return reviews;
    }

    private example.com.hotels.data.model.Review buildReview(Review review) {
        return example.com.hotels.data.model.Review.builder()
                .user(buildUser(review.user))
                .title(review.comments != null && review.comments.title != null ? review.comments.title : null)
                .goodComments(review.comments != null ? review.comments.good : null)
                .badComments(review.comments != null ? review.comments.bad : null)
                .build();
    }

    private example.com.hotels.data.model.User buildUser(User user) {
        if (user == null) {
            return null;
        }

        return example.com.hotels.data.model.User.builder()
                .name(user.name)
                .build();
    }

    private List<example.com.hotels.data.model.Amenity> buildAmenities(Item item) {
        List<example.com.hotels.data.model.Amenity> amenities = new ArrayList<>();

        if (item.amenities != null) {
            for (Amenity amenity : item.amenities) {
                amenities.add(buildAmenity(amenity));
            }
        }

        return amenities;
    }

    private example.com.hotels.data.model.Amenity buildAmenity(Amenity amenity) {
        return example.com.hotels.data.model.Amenity.builder()
                .description(amenity.description)
                .name(AmenitiesEnum.valueOf(amenity.id))
                .build();
    }

}

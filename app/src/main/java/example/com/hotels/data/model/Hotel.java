package example.com.hotels.data.model;


import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private Long id;
    private String name;
    private String mainPicture;
    private String description;
    private List<Amenity> amenities;
    private Integer stars;
    private ArrayList<Review> reviews;

    private Hotel(Builder builder) {
        id = builder.id;
        name = builder.name;
        mainPicture = builder.mainPicture;
        amenities = builder.amenities;
        description = builder.description;
        stars = builder.stars;
        reviews = builder.reviews;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public Integer getStars() {
        return stars;
    }

    public String getName() {
        return name;
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String mainPicture;
        private List<Amenity> amenities;
        private String description;
        private Integer stars;
        private ArrayList<Review> reviews;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder mainPicture(String val) {
            mainPicture = val;
            return this;
        }

        public Builder amenities(List<Amenity> val) {
            amenities = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder stars(Integer val) {
            stars = val;
            return this;
        }

        public Builder reviews(ArrayList<Review> val) {
            reviews = val;
            return this;
        }

        public Hotel build() {
            return new Hotel(this);
        }
    }
}

package example.com.hotels.data.model;

public class Amenity {

    private AmenitiesEnum name;
    private String description;

    private Amenity(Builder builder) {
        name = builder.name;
        description = builder.description;
    }

    public int getResId() {
        return name.getResId();
    }

    public String getDescription() {
        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private AmenitiesEnum name;
        private String description;

        public Builder() {
        }

        public Builder name(AmenitiesEnum val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Amenity build() {
            return new Amenity(this);
        }
    }

}

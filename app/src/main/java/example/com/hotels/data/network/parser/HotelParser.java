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
   }

   private class Amenity {
        private String id;
        private String description;
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

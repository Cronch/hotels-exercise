package example.com.hotels.data.model;


import example.com.hotels.R;

public enum AmenitiesEnum {
    BREAKFST (R.drawable.ic_free_breakfast),
    WIFI (R.drawable.ic_network_wifi),
    PARKING (R.drawable.ic_local_parking),
    PISCN (R.drawable.ic_pool);

    private int resId;

    AmenitiesEnum(int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}

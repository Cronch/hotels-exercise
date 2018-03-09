package example.com.hotels.data.model;


import example.com.hotels.R;

public enum AmenitiesEnum {
    BREAKFST (R.drawable.ic_home_black_24dp),
    WIFI (R.drawable.ic_home_black_24dp),
    PARKING (R.drawable.ic_home_black_24dp),
    PISCN (R.drawable.ic_home_black_24dp);

    private int resId;

    AmenitiesEnum(int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}

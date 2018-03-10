package example.com.hotels.data.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.text.WordUtils;

public class User implements Parcelable {

    private String name;

    private User(Builder builder) {
        name = builder.name;
    }

    private User(Parcel in) {
        this.name = in.readString();
    }

    public String getName() {
        return name != null ? WordUtils.capitalize(name) : name;
    }

    public static final class Builder {
        private String name;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

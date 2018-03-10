package example.com.hotels.data.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {

    private String title;
    private User user;
    private String goodComments;
    private String badComments;

    public User getUser() {
        return user;
    }

    public String getGoodComments() {
        return goodComments;
    }

    public String getBadComments() {
        return badComments;
    }

    public String getTitle() {
        return title;
    }

    private Review(Builder builder) {
        user = builder.user;
        goodComments = builder.goodComments;
        badComments = builder.badComments;
        title = builder.title;
    }

    private Review(Parcel in) {
        this.user = in.readParcelable(User.class.getClassLoader());
        this.goodComments = in.readString();
        this.badComments = in.readString();
        this.title = in.readString();
    }

    public static final class Builder {
        private User user;
        private String goodComments;
        private String badComments;
        private String title;

        public Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder goodComments(String val) {
            goodComments = val;
            return this;
        }

        public Builder badComments(String val) {
            badComments = val;
            return this;
        }

        public Review build() {
            return new Review(this);
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
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.goodComments);
        dest.writeString(this.badComments);
        dest.writeString(this.title);
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel source) {
            return new Review(source);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}

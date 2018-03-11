package example.com.hotels.ui.details;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import example.com.hotels.R;
import example.com.hotels.data.model.Amenity;

public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.ViewHolder> {

    private List<Amenity> amenities = Collections.emptyList();

    void setData(List<Amenity> amenities) {
        this.amenities = amenities;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public AmenitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.amenity, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Amenity amenity = amenities.get(position);
        holder.name.setText(amenity.getDescription());
        holder.image.setImageDrawable(holder.name.getResources().getDrawable(amenity.getResId()));
    }

    @Override
    public int getItemCount() {
        return amenities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;

        ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            image = v.findViewById(R.id.image);
        }
    }

}
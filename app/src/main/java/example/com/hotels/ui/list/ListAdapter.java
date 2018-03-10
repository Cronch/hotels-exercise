package example.com.hotels.ui.list;

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
import example.com.hotels.data.model.Hotel;
import example.com.hotels.injection.glide.GlideApp;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Hotel> hotels = Collections.emptyList();
    private ListFragment.OnHotelClick onHotelClick;

    ListAdapter(ListFragment.OnHotelClick onHotelClick) {
        this.onHotelClick = onHotelClick;
    }

    void setData(List<Hotel> hotels) {
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_header, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);

        holder.container.setOnClickListener((View view) -> onHotelClick.onClick(hotel));

        holder.name.setText(hotel.getName());
        GlideApp.with(holder.name.getContext())
                .load(hotel.getMainPicture())
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View container;
        private TextView name;
        private ImageView image;

        ViewHolder(View v) {
            super(v);
            container = v;
            name = v.findViewById(R.id.name);
            image = v.findViewById(R.id.image);
        }
    }

}
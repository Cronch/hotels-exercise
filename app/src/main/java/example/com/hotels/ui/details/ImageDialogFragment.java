package example.com.hotels.ui.details;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.hotels.R;
import example.com.hotels.data.model.Hotel;
import example.com.hotels.injection.glide.GlideApp;

public class ImageDialogFragment extends DialogFragment {

    private static final String HOTEL_ARG = "hotel";

    private Unbinder unbinder;
    private String url;

    @BindView(R.id.image)
    ImageView imageView;

    static ImageDialogFragment newInstance(Hotel hotel) {
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle args = new Bundle();
        args.putString(HOTEL_ARG, hotel.getMainPicture());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(HOTEL_ARG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_image, container, false);
        unbinder = ButterKnife.bind(this, v);

        GlideApp.with(getActivity())
                .load(url)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imageView);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

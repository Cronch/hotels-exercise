package example.com.hotels.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.hotels.CustomApplication;
import example.com.hotels.R;
import example.com.hotels.data.HotelManager;
import example.com.hotels.data.model.Amenity;
import example.com.hotels.data.model.Hotel;
import example.com.hotels.injection.glide.GlideApp;
import example.com.hotels.ui.comments.CommentsFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DetailsFragment extends Fragment implements DetailsContract.View {

    private final static String ID_ARG = "idArg";

    private Long hotelId;
    private Unbinder unbinder;
    private DetailsContract.Presenter presenter;
    private AmenitiesAdapter amenitiesAdapter;

    @Inject
    HotelManager dataManager;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.detailsContainer)
    View container;
    @BindView(R.id.ratingBar)
    AppCompatRatingBar ratingBar;
    @BindView(R.id.amenities)
    RecyclerView amenities;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.viewComments)
    View viewComments;
    @BindView(R.id.connectionErrorView)
    ImageView connectionErrorView;

    public static DetailsFragment createInstance(Hotel hotel) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putLong(ID_ARG, hotel.getId());
        detailsFragment.setArguments(args);

        return detailsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CustomApplication.getAppComponent().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailsPresenter(this, dataManager, AndroidSchedulers.mainThread(), Schedulers.io());
        presenter.onViewAttached();
    }

    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setUpActionBar();
        setUpAmenityList();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        hotelId = getArguments().getLong(ID_ARG);
        presenter.getHotelDetails(hotelId);
    }

    private void setUpActionBar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.hotel_details);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpAmenityList() {
        amenitiesAdapter = new AmenitiesAdapter();
        amenities.setHasFixedSize(true);
        amenities.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        amenities.setLayoutManager(mLayoutManager);
        amenities.setAdapter(amenitiesAdapter);
    }

    @Override
    public void onError(Throwable throwable) {
        connectionErrorView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Snackbar.make(container, "Connection error. Try again later", Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, (View v) -> presenter.getHotelDetails(hotelId))
                .show();
    }

    @Override
    public void onSuccess(Hotel hotel) {
        connectionErrorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);

        name.setText(hotel.getName());
        description.setText(hotel.getDescription());
        ratingBar.setRating(hotel.getStars());

        if (!hotel.getReviews().isEmpty()) {
            viewComments.setVisibility(View.VISIBLE);
            viewComments.setOnClickListener((View v) -> presenter.showComments(hotel));
        }

        // TODO: Abstract image library
        GlideApp.with(getActivity())
                .load(hotel.getMainPicture())
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(image);
        image.setOnClickListener((View v) -> presenter.zoomImage(hotel));

        List<Amenity> amenities = hotel.getAmenities();
        amenitiesAdapter.setData(amenities);
    }

    @Override
    public void showComments(Hotel hotel) {
        final String tag = "comments";
        CommentsFragment details = CommentsFragment.createInstance(hotel);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, details, tag)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void zoomImage(Hotel hotel) {
        final String TAG = "imageDialog";
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        DialogFragment newFragment = ImageDialogFragment.newInstance(hotel);
        newFragment.show(ft, TAG);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onViewDetached();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

package example.com.hotels.ui.comments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.hotels.R;
import example.com.hotels.data.model.Hotel;
import example.com.hotels.data.model.Review;


public class CommentsFragment extends Fragment {

    private final static String REVIEWS_ARG = "reviewsArg";

    private Unbinder unbinder;
    private List<Review> reviewList;

    @BindView(R.id.commentsList)
    RecyclerView list;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static CommentsFragment createInstance(Hotel hotel) {
        CommentsFragment commentsFragment = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(REVIEWS_ARG, hotel.getReviews());
        commentsFragment.setArguments(bundle);

        return commentsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewList = getArguments().getParcelableArrayList(REVIEWS_ARG);
    }

    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comments, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setUpActionBar();
        setUpHotelList();

        return rootView;
    }

    private void setUpActionBar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.reviews_title);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpHotelList() {
        list.setHasFixedSize(true);
        list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(mLayoutManager);
        CommentAdapter commentAdapter = new CommentAdapter();
        list.setAdapter(commentAdapter);
        commentAdapter.setData(reviewList);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

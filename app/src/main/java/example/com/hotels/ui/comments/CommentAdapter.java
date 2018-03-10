package example.com.hotels.ui.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import example.com.hotels.R;
import example.com.hotels.data.model.Review;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Review> reviews = Collections.emptyList();

    void setData(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviews.get(position);
        Context context = holder.userName.getContext();

        holder.userName.setText(review.getUser().getName());

        if (review.getGoodComments() != null) {
            String goodComments = context.getString(R.string.review_quotes, review.getGoodComments());
            holder.goodComments.setText(goodComments);
            holder.goodCommentsContainer.setVisibility(View.VISIBLE);
        } else {
            holder.goodCommentsContainer.setVisibility(View.GONE);
        }

        if (review.getBadComments() != null) {
            String badComments = context.getString(R.string.review_quotes, review.getBadComments());
            holder.badComments.setText(badComments);
            holder.badCommentsContainer.setVisibility(View.VISIBLE);
        } else {
            holder.badCommentsContainer.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView goodComments;
        private TextView badComments;
        private TextView userName;
        private View badCommentsContainer;
        private View goodCommentsContainer;

        ViewHolder(View v) {
            super(v);
            goodComments = v.findViewById(R.id.goodComments);
            badComments = v.findViewById(R.id.badComments);
            userName = v.findViewById(R.id.userName);
            badCommentsContainer = v.findViewById(R.id.badCommentsContainer);
            goodCommentsContainer = v.findViewById(R.id.goodCommentsContainer);
        }
    }

}
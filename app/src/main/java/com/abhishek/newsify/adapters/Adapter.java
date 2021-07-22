package com.abhishek.newsify.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.newsify.R;
import com.abhishek.newsify.activity.WebViewActivity;
import com.abhishek.newsify.models.NewsModel;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<NewsModel> newsModelArrayList;

    public Adapter(Context context, ArrayList<NewsModel> newsModelArrayList) {
        this.context = context;
        this.newsModelArrayList = newsModelArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", newsModelArrayList.get(position).getUrl());
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, holder.cardView, "card_view");
            context.startActivity(intent, options.toBundle());
        });

        holder.mHeading.setText(newsModelArrayList.get(position).getTitle());
        holder.mContent.setText(newsModelArrayList.get(position).getDescription());
        Glide.with(context).load(newsModelArrayList.get(position).getUrlToImage()).into(holder.imageView);
        holder.mAuthor.setText(newsModelArrayList.get(position).getAuthor());
        holder.mTime.setText(context.getResources().getString(R.string.published_at) + newsModelArrayList.get(position).getPublishedAt());

        Shimmer shimmer = new Shimmer.ColorHighlightBuilder().setBaseColor(Color.parseColor("#F3F3F3"))
                .setBaseAlpha(1)
                .setHighlightColor(Color.parseColor("#E7E7E7"))
                .setHighlightAlpha(1)
                .setDropoff(50)
                .build();

        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);

    }

    @Override
    public int getItemCount() {
        return newsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mHeading, mContent, mAuthor, mTime;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeading = itemView.findViewById(R.id.mainHeading);
            mContent = itemView.findViewById(R.id.content);
            mAuthor = itemView.findViewById(R.id.author);
            mTime = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

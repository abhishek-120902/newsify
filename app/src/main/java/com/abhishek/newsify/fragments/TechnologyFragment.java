package com.abhishek.newsify.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.newsify.R;
import com.abhishek.newsify.adapters.Adapter;
import com.abhishek.newsify.models.NewsModel;
import com.abhishek.newsify.models.mainNews;
import com.abhishek.newsify.utils.ApiUtilities;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechnologyFragment extends Fragment {

    private final String category = "technology";
    String api = "47de04280d214dca971c2e75fef05451";
    ArrayList<NewsModel> newsModelArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewTechnology;
    private ShimmerFrameLayout shimmerFrameLayout;
    private RelativeLayout rlShimmer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_technology, null);

        recyclerViewTechnology = v.findViewById(R.id.recyclerViewTechnology);
        newsModelArrayList = new ArrayList<>();
        shimmerFrameLayout = v.findViewById(R.id.shimmerLayout);
        rlShimmer = v.findViewById(R.id.rlShimmer);
        recyclerViewTechnology.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), newsModelArrayList);
        recyclerViewTechnology.setAdapter(adapter);

        shimmerFrameLayout.startShimmer();
        rlShimmer.setVisibility(View.VISIBLE);
        findNews();

        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country, category, 100, api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    rlShimmer.setVisibility(View.GONE);
                    newsModelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                shimmerFrameLayout.setVisibility(View.GONE);
                rlShimmer.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
            }
        });

    }

}

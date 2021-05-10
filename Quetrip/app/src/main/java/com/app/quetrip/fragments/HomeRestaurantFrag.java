package com.app.quetrip.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.Interfaces.RestaurantItemClickListener;
import com.app.quetrip.R;
import com.app.quetrip.adapters.HomeRestaurantListAdapter;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.main.RestaurantDetailActivity;
import com.app.quetrip.models.Restaurant;

import java.util.ArrayList;

public class HomeRestaurantFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_service, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
        recyclerView.setNestedScrollingEnabled(false);

        for(int i=0;i<50;i++){
            Restaurant restaurant = new Restaurant(i+1, 1, "Marcel Restaurant", "en", "marcel123@gmail.com", "+55 11 3064-3089", "Rua da Consolacao 3555 | Jardins, Sao Paulo, State of Sao Paulo 01416-003, Brazi",
                    "Brazil", "Sao Paulo", "01416-003", -13.0096055,-38.4957249, 12000,
                    "https://media-cdn.tripadvisor.com/media/photo-s/10/c9/2c/bc/sofisticacao-francesa.jpg",
                    15, 150, "USD", "French, Europian", "Lunch, Dinner", "", "", getString(R.string.restaurant_desc), 22, 4.2f, "open");
            restaurants.add(restaurant);
        }

        initRecyclerView(restaurants);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView(ArrayList<Restaurant> items) {
        HomeRestaurantListAdapter adapter = new HomeRestaurantListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RestaurantItemClickListener() {
            @Override
            public void onItemClick(Restaurant restaurant) {
                Commons.restaurant = restaurant;
				Intent intent=new Intent(getActivity(), RestaurantDetailActivity.class);
				startActivity(intent);
				getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    public static Fragment newInstance() {
        return new HomeRestaurantFrag();
    }

}

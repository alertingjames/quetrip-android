package com.app.quetrip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.Interfaces.RestaurantItemClickListener;
import com.app.quetrip.R;
import com.app.quetrip.models.Hotel;
import com.app.quetrip.models.Restaurant;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeRestaurantListAdapter extends RecyclerView.Adapter<HomeRestaurantListAdapter.CustomViewHolder> {
    private List<Restaurant> restaurants;
    Context context;
    private RestaurantItemClickListener onItemClickListener;

    public HomeRestaurantListAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {

        customViewHolder.setRestaurant(restaurants.get(i));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnItemClickListener().onItemClick(restaurants.get(i));
            }
        };
        customViewHolder.nameBox.setOnClickListener(listener);
        customViewHolder.pictureBox.setOnClickListener(listener);
        customViewHolder.descriptionBox.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void add(List<Restaurant> restaurantList) {
        restaurants.addAll(restaurantList);
    }

    public void reset() {
        restaurants.clear();
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView nameBox;
        protected TextView descriptionBox;
        protected RoundedImageView pictureBox;

        public CustomViewHolder(View view) {
            super(view);

            this.nameBox = (TextView) view.findViewById(R.id.nameBox);
            this.descriptionBox = (TextView) view.findViewById(R.id.descriptionBox);
            this.pictureBox = (RoundedImageView) view.findViewById(R.id.pictureBox);
        }

        public void setRestaurant(Restaurant restaurant) {
            nameBox.setText(restaurant.getName());
            descriptionBox.setText(restaurant.getDescription());
            if (restaurant.getPicture_url().length() > 0) {
                Glide.with(context).load(restaurant.getPicture_url()).into(pictureBox);
            }
        }
    }

    public RestaurantItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(RestaurantItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

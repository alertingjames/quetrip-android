package com.app.quetrip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.Interfaces.HotelItemClickListener;
import com.app.quetrip.R;
import com.app.quetrip.models.Hotel;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeHotelListAdapter extends RecyclerView.Adapter<HomeHotelListAdapter.CustomViewHolder> {
    private List<Hotel> hotels;
    Context context;
    private HotelItemClickListener onItemClickListener;

    public HomeHotelListAdapter(Context context, ArrayList<Hotel> hotels) {
        this.hotels = hotels;
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

        customViewHolder.setHotel(hotels.get(i));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnItemClickListener().onItemClick(hotels.get(i));
            }
        };
        customViewHolder.nameBox.setOnClickListener(listener);
        customViewHolder.pictureBox.setOnClickListener(listener);
        customViewHolder.descriptionBox.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void add(List<Hotel> hotelList) {
        hotels.addAll(hotelList);
    }

    public void reset() {
        hotels.clear();
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

        public void setHotel(Hotel hotel) {
            nameBox.setText(hotel.getName());
            descriptionBox.setText(hotel.getDescription());
            if (hotel.getPicture_url().length() > 0) {
                Glide.with(context).load(hotel.getPicture_url()).into(pictureBox);
            }
        }
    }

    public HotelItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(HotelItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

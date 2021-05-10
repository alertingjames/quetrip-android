package com.app.quetrip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.Interfaces.AttractionsItemClickListener;
import com.app.quetrip.Interfaces.RestaurantItemClickListener;
import com.app.quetrip.R;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.Restaurant;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeAttractionsListAdapter extends RecyclerView.Adapter<HomeAttractionsListAdapter.CustomViewHolder> {
    private List<Attractions> attractionsList;
    Context context;
    private AttractionsItemClickListener onItemClickListener;

    public HomeAttractionsListAdapter(Context context, ArrayList<Attractions> attractionsArrayList) {
        this.attractionsList = attractionsArrayList;
        this.context = context;
    }

    @Override
    public HomeAttractionsListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {

        customViewHolder.setAttractions(attractionsList.get(i));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnItemClickListener().onItemClick(attractionsList.get(i));
            }
        };
        customViewHolder.nameBox.setOnClickListener(listener);
        customViewHolder.pictureBox.setOnClickListener(listener);
        customViewHolder.descriptionBox.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return attractionsList.size();
    }

    public void add(List<Attractions> attractionsList) {
        attractionsList.addAll(attractionsList);
    }

    public void reset() {
        attractionsList.clear();
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

        public void setAttractions(Attractions attractions) {
            nameBox.setText(attractions.getName());
            descriptionBox.setText(attractions.getDescription());
            if (attractions.getPicture_url().length() > 0) {
                Glide.with(context).load(attractions.getPicture_url()).into(pictureBox);
            }
        }
    }

    public AttractionsItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AttractionsItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

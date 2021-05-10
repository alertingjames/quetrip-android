package com.app.quetrip.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.R;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.main.CarRentDetailActivity;
import com.app.quetrip.models.CarRent;
import com.app.quetrip.models.Guide;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CarRentListAdapter extends RecyclerView.Adapter<CarRentListAdapter.CustomViewHolder> {
    private List<CarRent> carRents;
    Context context;

    public CarRentListAdapter(Context context, ArrayList<CarRent> carRents) {
        this.carRents = carRents;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_car_rent, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
        customViewHolder.setList(carRents.get(i));
        customViewHolder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Commons.carRent = carRents.get(i);
                Intent intent = new Intent(context, CarRentDetailActivity.class);
                context.startActivity(intent);
            }
        });
        customViewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Commons.servicesActivity != null){
                    Commons.servicesActivity.addCarRentToDailyPlan(carRents.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carRents.size();
    }

    public void add(List<CarRent> rents) {
        carRents.addAll(rents);
    }

    public void reset() {
        carRents.clear();
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView nameBox, cityBox, seatersBox, priceBox, ratingsBox;
        protected RatingBar ratingBar;
        protected TextView moreButton, addButton;
        protected ImageView pictureBox;

        public CustomViewHolder(View view) {
            super(view);

            this.nameBox = (TextView) view.findViewById(R.id.nameBox);
            this.cityBox = (TextView) view.findViewById(R.id.cityNameBox);
            this.seatersBox = (TextView) view.findViewById(R.id.seaterBox);
            this.priceBox = (TextView) view.findViewById(R.id.priceBox);
            this.ratingsBox = (TextView) view.findViewById(R.id.ratingsBox);
            this.ratingBar = (RatingBar) view.findViewById(R.id.ratingbar);
            this.pictureBox = (ImageView) view.findViewById(R.id.pictureBox);
            this.moreButton = (TextView) view.findViewById(R.id.btn_more);
            this.addButton = (TextView)view.findViewById(R.id.btn_add);
        }

        public void setList(CarRent carRent) {
            nameBox.setText(carRent.getName());
            cityBox.setText(carRent.getCity());
            seatersBox.setText(String.valueOf(carRent.getSeaters()) + " " + context.getString(R.string.seaters) + (carRent.getColor().length() > 0?" | " + carRent.getColor():""));
            priceBox.setText(String.valueOf(carRent.getWeekly_price()) + " " + carRent.getUnit());
            ratingsBox.setText(String.valueOf(carRent.getRatings()));
            ratingBar.setRating(carRent.getRatings());
            if (carRent.getPicture_url().length() > 0) {
                Glide.with(context).load(carRent.getPicture_url()).into(pictureBox);
            }
        }
    }

}


























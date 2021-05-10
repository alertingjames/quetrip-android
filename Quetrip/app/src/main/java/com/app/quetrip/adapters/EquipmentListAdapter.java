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
import com.app.quetrip.main.EquipmentDetailActivity;
import com.app.quetrip.models.CarRent;
import com.app.quetrip.models.Equipment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class EquipmentListAdapter extends RecyclerView.Adapter<EquipmentListAdapter.CustomViewHolder> {
    private List<Equipment> equipments;
    Context context;

    public EquipmentListAdapter(Context context, ArrayList<Equipment> equipments) {
        this.equipments = equipments;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_equipment, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
        customViewHolder.setList(equipments.get(i));
        customViewHolder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Commons.equipment = equipments.get(i);
                Intent intent = new Intent(context, EquipmentDetailActivity.class);
                context.startActivity(intent);
            }
        });
        customViewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Commons.servicesActivity != null){
                    Commons.servicesActivity.addEquipmentToDailyPlan(equipments.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return equipments.size();
    }

    public void add(List<Equipment> eqts) {
        equipments.addAll(eqts);
    }

    public void reset() {
        equipments.clear();
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView nameBox, cityBox, sizeBox, priceBox, ratingsBox;
        protected RatingBar ratingBar;
        protected TextView moreButton, addButton;
        protected ImageView pictureBox;

        public CustomViewHolder(View view) {
            super(view);

            this.nameBox = (TextView) view.findViewById(R.id.nameBox);
            this.cityBox = (TextView) view.findViewById(R.id.cityNameBox);
            this.sizeBox = (TextView) view.findViewById(R.id.sizeBox);
            this.priceBox = (TextView) view.findViewById(R.id.priceBox);
            this.ratingsBox = (TextView) view.findViewById(R.id.ratingsBox);
            this.ratingBar = (RatingBar) view.findViewById(R.id.ratingbar);
            this.pictureBox = (ImageView) view.findViewById(R.id.pictureBox);
            this.moreButton = (TextView) view.findViewById(R.id.btn_more);
            this.addButton = (TextView)view.findViewById(R.id.btn_add);
        }

        public void setList(Equipment equipment) {
            nameBox.setText(equipment.getName());
            cityBox.setText(equipment.getCity());
            sizeBox.setText(equipment.getSize());
            priceBox.setText(String.valueOf(equipment.getPrice()) + " " + equipment.getUnit());
            ratingsBox.setText(String.valueOf(equipment.getRatings()));
            ratingBar.setRating(equipment.getRatings());
            if (equipment.getPicture_url().length() > 0) {
                Glide.with(context).load(equipment.getPicture_url()).into(pictureBox);
            }
        }
    }

}


























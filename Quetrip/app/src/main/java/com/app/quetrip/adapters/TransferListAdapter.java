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
import com.app.quetrip.main.TransferDetailActivity;
import com.app.quetrip.models.CarRent;
import com.app.quetrip.models.Transfer;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TransferListAdapter extends RecyclerView.Adapter<TransferListAdapter.CustomViewHolder> {
    private List<Transfer> transfers;
    Context context;

    public TransferListAdapter(Context context, ArrayList<Transfer> transfers) {
        this.transfers = transfers;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transfer, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
        customViewHolder.setList(transfers.get(i));
        customViewHolder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Commons.transfer = transfers.get(i);
                Intent intent = new Intent(context, TransferDetailActivity.class);
                context.startActivity(intent);
            }
        });
        customViewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Commons.servicesActivity != null){
                    Commons.servicesActivity.addTransferToDailyPlan(transfers.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transfers.size();
    }

    public void add(List<Transfer> trfs) {
        transfers.addAll(trfs);
    }

    public void reset() {
        transfers.clear();
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView nameBox, cityBox, minsBox, priceBox, ratingsBox;
        protected RatingBar ratingBar;
        protected TextView moreButton, addButton;
        protected ImageView pictureBox;

        public CustomViewHolder(View view) {
            super(view);

            this.nameBox = (TextView) view.findViewById(R.id.nameBox);
            this.cityBox = (TextView) view.findViewById(R.id.cityNameBox);
            this.minsBox = (TextView) view.findViewById(R.id.minsBox);
            this.priceBox = (TextView) view.findViewById(R.id.priceBox);
            this.ratingsBox = (TextView) view.findViewById(R.id.ratingsBox);
            this.ratingBar = (RatingBar) view.findViewById(R.id.ratingbar);
            this.pictureBox = (ImageView) view.findViewById(R.id.pictureBox);
            this.moreButton = (TextView) view.findViewById(R.id.btn_more);
            this.addButton = (TextView)view.findViewById(R.id.btn_add);
        }

        public void setList(Transfer transfer) {
            nameBox.setText(transfer.getName());
            cityBox.setText(transfer.getCity());
            minsBox.setText(String.valueOf(transfer.getKm_mins()));
            priceBox.setText(String.valueOf(transfer.getKm_price()) + " " + transfer.getUnit());
            ratingsBox.setText(String.valueOf(transfer.getRatings()));
            ratingBar.setRating(transfer.getRatings());
            if (transfer.getPicture_url().length() > 0) {
                Glide.with(context).load(transfer.getPicture_url()).into(pictureBox);
            }
        }
    }

}


























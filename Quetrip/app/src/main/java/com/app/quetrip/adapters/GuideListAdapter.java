package com.app.quetrip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.app.quetrip.R;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Guide;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GuideListAdapter extends RecyclerView.Adapter<GuideListAdapter.CustomViewHolder> {
    private List<Guide> guides;
    Context context;

    public GuideListAdapter(Context context, ArrayList<Guide> guides) {
        this.guides = guides;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_guide, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
        customViewHolder.setList(guides.get(i));
        customViewHolder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Commons.servicesActivity != null){

                }
            }
        });
        customViewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Commons.servicesActivity != null){
                    Commons.servicesActivity.addGuideToDailyPlan(guides.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return guides.size();
    }

    public void add(List<Guide> gds) {
        guides.addAll(gds);
    }

    public void reset() {
        guides.clear();
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView nameBox, cityBox, languageBox, priceBox, ratingsBox;
        protected RatingBar ratingBar;
        protected TextView moreButton, addButton;
        protected CircleImageView pictureBox;
        protected LinearLayout addLayout;

        public CustomViewHolder(View view) {
            super(view);

            this.nameBox = (TextView) view.findViewById(R.id.nameBox);
            this.cityBox = (TextView) view.findViewById(R.id.cityNameBox);
            this.languageBox = (TextView) view.findViewById(R.id.languageBox);
            this.priceBox = (TextView) view.findViewById(R.id.priceBox);
            this.ratingsBox = (TextView) view.findViewById(R.id.ratingsBox);
            this.ratingBar = (RatingBar) view.findViewById(R.id.ratingbar);
            this.pictureBox = (CircleImageView) view.findViewById(R.id.pictureBox);
            this.moreButton = (TextView) view.findViewById(R.id.btn_more);
            this.addButton = (TextView)view.findViewById(R.id.btn_add);
            this.addLayout = (LinearLayout)view.findViewById(R.id.addLayout);
        }

        public void setList(Guide guide) {
            if(Commons.dailyScheduleActivity == null)addLayout.setVisibility(View.GONE);
            nameBox.setText(guide.getName());
            cityBox.setText(guide.getCity());
            languageBox.setText(guide.getLanguage());
            priceBox.setText(String.valueOf(guide.getPrice()) + " " + guide.getUnit());
            ratingsBox.setText(String.valueOf(guide.getRatings()));
            ratingBar.setRating(guide.getRatings());
            if (guide.getPicture_url().length() > 0) {
                Glide.with(context).load(guide.getPicture_url()).into(pictureBox);
            }
        }
    }

}




























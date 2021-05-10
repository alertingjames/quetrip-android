package com.app.quetrip.adapters;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.app.quetrip.R;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.RestaurantMenu;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuListAdapter extends RecyclerView.Adapter<RestaurantMenuListAdapter.CustomViewHolder> {
    private List<RestaurantMenu> menus;
    Context context;

    public RestaurantMenuListAdapter(Context context, ArrayList<RestaurantMenu> menus) {
        this.menus = menus;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_restaurant_menu, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
        customViewHolder.setMenu(menus.get(i));
        customViewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Commons.restaurantDetailActivity != null){
                    Commons.restaurantDetailActivity.addMenuToSchedule(menus.get(i), customViewHolder.quantityBox);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public void add(List<RestaurantMenu> mns) {
        menus.addAll(mns);
    }

    public void reset() {
        menus.clear();
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        EditText quantityBox;
        protected TextView nameBox, sizeBox, priceBox;
        protected TextView descriptionBox;
        protected TextView addButton;
        protected ImageView pictureBox;
        protected LinearLayout addLayout;

        public CustomViewHolder(View view) {
            super(view);

            this.nameBox = (TextView) view.findViewById(R.id.nameBox);
            this.sizeBox = (TextView) view.findViewById(R.id.sizeBox);
            this.priceBox = (TextView) view.findViewById(R.id.priceBox);
            this.descriptionBox = (TextView) view.findViewById(R.id.descriptionBox);
            this.pictureBox = (ImageView) view.findViewById(R.id.pictureBox);
            this.quantityBox = (EditText)view.findViewById(R.id.quantityBox);
            this.addButton = (TextView)view.findViewById(R.id.btn_add);
            this.addLayout = (LinearLayout)view.findViewById(R.id.addLayout);
        }

        public void setMenu(RestaurantMenu menu) {
            if(Commons.dailyScheduleActivity == null)addLayout.setVisibility(View.GONE);
            nameBox.setText(menu.getName());
            if(menu.getSize().length() == 0)sizeBox.setVisibility(View.GONE);
            else {
                sizeBox.setVisibility(View.VISIBLE);
                String menuSizeStr = menu.getSize().substring(0,1).toUpperCase() + menu.getSize().substring(1, menu.getSize().length());
                sizeBox.setText(menuSizeStr);
            }
            priceBox.setText(String.valueOf(menu.getPrice()) + " " + menu.getUnit());
            descriptionBox.setText(menu.getDescription());
            if (menu.getPicture_url().length() > 0) {
                Glide.with(context).load(menu.getPicture_url()).into(pictureBox);
            }
        }
    }

}




























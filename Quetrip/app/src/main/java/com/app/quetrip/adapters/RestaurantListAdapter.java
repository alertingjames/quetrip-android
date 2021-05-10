package com.app.quetrip.adapters;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.app.quetrip.R;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.main.HotelDetailActivity;
import com.app.quetrip.main.RestaurantDetailActivity;
import com.app.quetrip.models.Hotel;
import com.app.quetrip.models.Restaurant;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RestaurantListAdapter  extends BaseAdapter {
    private Context _context;
    private ArrayList<Restaurant> _datas = new ArrayList<>();
    private ArrayList<Restaurant> _alldatas = new ArrayList<>();
    public static DecimalFormat df = new DecimalFormat("0.00");

    public RestaurantListAdapter(Context context){

        super();
        this._context = context;
    }

    public void setDatas(ArrayList<Restaurant> datas) {

        _alldatas = datas;
        _datas.clear();
        _datas.addAll(_alldatas);
    }

    @Override
    public int getCount(){
        return _datas.size();
    }

    @Override
    public Object getItem(int position){
        return _datas.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        CustomHolder holder;

        if (convertView == null) {
            holder = new CustomHolder();

            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.item_restaurant_list, parent, false);

            holder.name = (TextView) convertView.findViewById(R.id.nameBox);
            holder.city = (TextView) convertView.findViewById(R.id.cityNameBox);
            holder.distance = (TextView) convertView.findViewById(R.id.distanceBox);
            holder.price = (TextView) convertView.findViewById(R.id.priceBox);
            holder.status = (TextView) convertView.findViewById(R.id.statusBox);
            holder.cuisines = (TextView) convertView.findViewById(R.id.cuisineBox);
            holder.meals = (TextView) convertView.findViewById(R.id.mealBox);
            holder.reviews = (TextView) convertView.findViewById(R.id.reviews);
            holder.ratings = (TextView) convertView.findViewById(R.id.ratings);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingbar);
            holder.image = (ImageView) convertView.findViewById(R.id.pictureBox);

            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

        final Restaurant entity = (Restaurant) _datas.get(position);

        holder.name.setText(entity.getName());
        holder.city.setText(entity.getCity());
        holder.price.setText(String.valueOf(entity.getMin_price()) + " - " + String.valueOf(entity.getMax_price()) + " " + entity.getUnit());
        holder.status.setText(entity.getStatus().equals("open")?"Open Now":"Closed Now");
        holder.cuisines.setText(entity.getCuisines());
        holder.meals.setText(entity.getMeals());
        holder.ratingBar.setRating(entity.getRatings());
        holder.ratings.setText(String.valueOf(entity.getRatings()));
        holder.reviews.setText(String.valueOf(entity.getReviews()));

        if(Commons.thisUser.getLatLng() != null){
            Location myLocation = new Location("MyLocation");
            myLocation.setLatitude(Commons.thisUser.getLatLng().latitude);
            myLocation.setLongitude(Commons.thisUser.getLatLng().longitude);
            Location restaurant_location = new Location("HomeRestaurantFrag Location");
            restaurant_location.setLatitude(entity.getLatLng().latitude);
            restaurant_location.setLongitude(entity.getLatLng().longitude);
            double distance = myLocation.distanceTo(restaurant_location);
            holder.distance.setText(df.format(distance/1000) + " km");
        }else {
            holder.distance.setText(df.format(entity.getDistance()/1000) + " km");
        }

        if(entity.getPicture_url().length() > 0){
            Glide.with(_context)
                    .load(entity.getPicture_url())
                    .error(R.drawable.noresult)
                    .into(holder.image);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commons.restaurant = entity;
                Intent intent = new Intent(_context, RestaurantDetailActivity.class);
                _context.startActivity(intent);
            }
        });

        return convertView;
    }

    public void filter(String charText){

        charText = charText.toLowerCase();
        _datas.clear();

        if(charText.length() == 0){
            _datas.addAll(_alldatas);
        }else {
            for (Restaurant restaurant : _alldatas){

                if (restaurant != null) {

                    String value = restaurant.getName().toLowerCase();
                    if (value.contains(charText)) {
                        _datas.add(restaurant);
                    }else {
                        value = restaurant.getCity().toLowerCase();
                        if (value.contains(charText)) {
                            _datas.add(restaurant);
                        }else {
                            value = restaurant.getAddress().toLowerCase();
                            if (value.contains(charText)) {
                                _datas.add(restaurant);
                            }
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    class CustomHolder {
        ImageView image;
        RatingBar ratingBar;
        TextView name, city, distance, price, cuisines, meals, status, ratings, reviews;
    }
}
















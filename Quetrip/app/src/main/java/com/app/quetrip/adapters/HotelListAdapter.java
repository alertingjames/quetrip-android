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
import com.app.quetrip.main.AttractionsDetailActivity;
import com.app.quetrip.main.HotelDetailActivity;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.Hotel;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HotelListAdapter extends BaseAdapter {
    private Context _context;
    private ArrayList<Hotel> _datas = new ArrayList<>();
    private ArrayList<Hotel> _alldatas = new ArrayList<>();
    public static DecimalFormat df = new DecimalFormat("0.00");

    public HotelListAdapter(Context context){

        super();
        this._context = context;
    }

    public void setDatas(ArrayList<Hotel> datas) {

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
            convertView = inflater.inflate(R.layout.item_hotel_list, parent, false);

            holder.name = (TextView) convertView.findViewById(R.id.nameBox);
            holder.city = (TextView) convertView.findViewById(R.id.cityNameBox);
            holder.distance = (TextView) convertView.findViewById(R.id.distanceBox);
            holder.price = (TextView) convertView.findViewById(R.id.priceBox);
            holder.services = (TextView) convertView.findViewById(R.id.servicesBox);
            holder.reviews = (TextView) convertView.findViewById(R.id.reviews);
            holder.ratings = (TextView) convertView.findViewById(R.id.ratings);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingbar);
            holder.image = (ImageView) convertView.findViewById(R.id.pictureBox);

            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

        final Hotel entity = (Hotel) _datas.get(position);

        holder.name.setText(entity.getName());
        holder.city.setText(entity.getCity());
        holder.price.setText(String.valueOf(entity.getPrice()) + " " + entity.getUnit());
        holder.services.setText(entity.getServices());
        holder.ratingBar.setRating(entity.getRatings());
        holder.ratings.setText(String.valueOf(entity.getRatings()));
        holder.reviews.setText(String.valueOf(entity.getReviews()));

        if(Commons.thisUser.getLatLng() != null){
            Location myLocation = new Location("MyLocation");
            myLocation.setLatitude(Commons.thisUser.getLatLng().latitude);
            myLocation.setLongitude(Commons.thisUser.getLatLng().longitude);
            Location attractionsLocation = new Location("Attractions Location");
            attractionsLocation.setLatitude(entity.getLatLng().latitude);
            attractionsLocation.setLongitude(entity.getLatLng().longitude);
            double distance = myLocation.distanceTo(attractionsLocation);
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
                Commons.hotel = entity;
                Intent intent = new Intent(_context, HotelDetailActivity.class);
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
            for (Hotel hotel : _alldatas){

                if (hotel != null) {

                    String value = hotel.getName().toLowerCase();
                    if (value.contains(charText)) {
                        _datas.add(hotel);
                    }else {
                        value = hotel.getCity().toLowerCase();
                        if (value.contains(charText)) {
                            _datas.add(hotel);
                        }else {
                            value = hotel.getAddress().toLowerCase();
                            if (value.contains(charText)) {
                                _datas.add(hotel);
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
        TextView name, city, distance, price, services, ratings, reviews;
    }
}




















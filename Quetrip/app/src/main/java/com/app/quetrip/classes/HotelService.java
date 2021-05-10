package com.app.quetrip.classes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quetrip.R;

import java.util.HashMap;
import java.util.Map;

public class HotelService {
    public Map<String, View> service = new HashMap<>();
    public void initHotelServices(Activity cxt) {

        String[] items = new String[]{"pool", "wifi", "spa", "air", "parking", "pet", "shuttle", "breakfast", "restaurant"};
        String[] itemLabels = new String[]{
                cxt.getString(R.string.pool),
                cxt.getString(R.string.wifi),
                cxt.getString(R.string.spa),
                cxt.getString(R.string.air_conditioning),
                cxt.getString(R.string.parking),
                cxt.getString(R.string.pet_friendly),
                cxt.getString(R.string.shuttle),
                cxt.getString(R.string.breakfast),
                cxt.getString(R.string.restaurant),
        };

        int[] itemIcons = new int[]{
                R.drawable.ic_pool,
                R.drawable.ic_wifi,
                R.drawable.ic_spa,
                R.drawable.ic_air_conditioning,
                R.drawable.parking,
                R.drawable.ic_pet,
                R.drawable.ic_shuttle,
                R.drawable.ic_breakfast,
                R.drawable.ic_restaurant2,
        };

        for(int i=0;i<items.length; i++){
            LayoutInflater inflater = cxt.getLayoutInflater();
            View layout = inflater.inflate(R.layout.layout_hotel_service, null);
            TextView textView = (TextView)layout.findViewById(R.id.text);
            ImageView iconView = (ImageView) layout.findViewById(R.id.icon);
            textView.setText(itemLabels[i]);
            iconView.setImageResource(itemIcons[i]);
            service.put(items[i], layout);
        }
    }
}

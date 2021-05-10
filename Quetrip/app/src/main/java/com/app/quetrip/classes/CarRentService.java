package com.app.quetrip.classes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quetrip.R;

import java.util.HashMap;
import java.util.Map;

public class CarRentService {
    public Map<String, View> service = new HashMap<>();
    public void initCarRentServices(Activity cxt) {

        String[] items = new String[]{"transmission", "mileage", "air"};
        String[] itemLabels = new String[]{
                cxt.getString(R.string.transmission),
                cxt.getString(R.string.mileage),
                cxt.getString(R.string.air_conditioning),
        };

        int[] itemIcons = new int[]{
                R.drawable.ic_transmission,
                R.drawable.ic_mileage,
                R.drawable.ic_air_conditioning,
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

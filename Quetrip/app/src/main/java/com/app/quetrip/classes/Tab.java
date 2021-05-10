package com.app.quetrip.classes;

import android.app.Activity;

import com.app.quetrip.R;

import java.util.HashMap;
import java.util.Map;

public class Tab {
    public Map<String, String> tabHash = new HashMap<>();
    public void initTab(Activity cxt) {
        tabHash.put("hotels", cxt.getString(R.string.hotels));
        tabHash.put("restaurants", cxt.getString(R.string.restaurants));
        tabHash.put("attractions", cxt.getString(R.string.attractions));
        tabHash.put("travel packages", cxt.getString(R.string.travel_packages));
        tabHash.put("guides", cxt.getString(R.string.guides));

        tabHash.put("salty_pizza", cxt.getString(R.string.salty_pizza));
        tabHash.put("sweet_pizza", cxt.getString(R.string.sweet_pizza));
        tabHash.put("dessert", cxt.getString(R.string.dessert));
        tabHash.put("drinks", cxt.getString(R.string.drinks));

        tabHash.put("guides", cxt.getString(R.string.guides));
        tabHash.put("rent_car", cxt.getString(R.string.rent_car));
        tabHash.put("equipments", cxt.getString(R.string.equipments));
        tabHash.put("transfer", cxt.getString(R.string.transfer));

        tabHash.put("new", cxt.getString(R.string.new_trips));
        tabHash.put("ongoing", cxt.getString(R.string.ongoing));
        tabHash.put("past", cxt.getString(R.string.past));

    }
}

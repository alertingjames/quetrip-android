package com.app.quetrip.commons;

import android.widget.TextView;

import com.app.quetrip.classes.Tab;
import com.app.quetrip.main.AttractionsListActivity;
import com.app.quetrip.main.CitySummaryActivity;
import com.app.quetrip.main.DailyScheduleActivity;
import com.app.quetrip.main.HotelDetailActivity;
import com.app.quetrip.main.HotelListActivity;
import com.app.quetrip.main.RestaurantDetailActivity;
import com.app.quetrip.main.RestaurantListActivity;
import com.app.quetrip.main.ScheduledCityListActivity;
import com.app.quetrip.main.InCityScheduleActivity;
import com.app.quetrip.main.NewScheduleActivity;
import com.app.quetrip.main.ScheduledTransferDetailActivity;
import com.app.quetrip.main.SelectTransferRouteActivity;
import com.app.quetrip.main.ServicesActivity;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.CarRent;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.Dest;
import com.app.quetrip.models.Equipment;
import com.app.quetrip.models.Hotel;
import com.app.quetrip.models.HotelRoom;
import com.app.quetrip.models.Marker;
import com.app.quetrip.models.QuetripAddress;
import com.app.quetrip.models.Restaurant;
import com.app.quetrip.models.RestaurantMenu;
import com.app.quetrip.models.Schedule;
import com.app.quetrip.models.Service;
import com.app.quetrip.models.Transfer;
import com.app.quetrip.models.User;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class Commons {
    public static String lang = "en";
    public static User thisUser = null;
    public static TextView textView = null;
    public static QuetripAddress quetripAddress = null;
    public static int curMapTypeIndex = 1;
    public static Marker marker = null;
    public static Tab tab = new Tab();
    public static Service service = null;
    public static int imageRes = 0;
    public static NewScheduleActivity newScheduleActivity = null;
    public static CitySummaryActivity citySummaryActivity = null;
    public static InCityScheduleActivity inCityScheduleActivity = null;
    public static ScheduledCityListActivity scheduledCityListActivity = null;
    public static Schedule schedule = null;
    public static DailyPlan dailyPlan = null;
    public static ArrayList<DailyPlan> dailyPlans = new ArrayList<>();
    public static Attractions attractions = null;
    public static DailyScheduleActivity dailyScheduleActivity = null;
    public static AttractionsListActivity attractionsListActivity = null;
    public static GoogleMap googleMap = null;
    public static boolean mapCameraMoveF = false;
    public static Dest dest = null;
    public static Hotel hotel = null;
    public static HotelListActivity hotelListActivity = null;
    public static HotelDetailActivity hotelDetailActivity = null;
    public static HotelRoom room = null;
    public static RestaurantListActivity restaurantListActivity = null;
    public static Restaurant restaurant = null;
    public static RestaurantMenu restaurantMenu = null;
    public static RestaurantDetailActivity restaurantDetailActivity = null;
    public static ServicesActivity servicesActivity = null;
    public static CarRent carRent = null;
    public static Equipment equipment = null;
    public static Transfer transfer = null;
    public static SelectTransferRouteActivity selectTransferRouteActivity = null;
    public static ScheduledTransferDetailActivity scheduledTransferDetailActivity = null;

}



































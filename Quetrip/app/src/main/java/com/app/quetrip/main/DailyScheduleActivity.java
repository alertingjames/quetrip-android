package com.app.quetrip.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.DateMain;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.CarRent;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.Equipment;
import com.app.quetrip.models.Guide;
import com.app.quetrip.models.Hotel;
import com.app.quetrip.models.HotelRoom;
import com.app.quetrip.models.Restaurant;
import com.app.quetrip.models.RestaurantMenu;
import com.app.quetrip.models.Transfer;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.concurrent.Callable;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.app.quetrip.adapters.AttractionsListAdapter.df;

public class DailyScheduleActivity extends BaseActivity {

    TextView tripDaysBox;
    LinearLayout container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_schedule);

        Commons.dailyScheduleActivity = this;

        tripDaysBox = (TextView)findViewById(R.id.tripDaysBox);
        tripDaysBox.setText(String.valueOf(Commons.schedule.getDays()));

        container = (LinearLayout)findViewById(R.id.container);

        initTabs(1);

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData(){
        container.removeAllViews();
        int ii = 0;
        for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
            ii++;
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.item_daily_schedule, null);
            TextView cityNameBox = (TextView)layout.findViewById(R.id.cityNameBox);
            cityNameBox.setText(dailyPlan.getCity_name());

            RoundedImageView cityPictureBox = (RoundedImageView)layout.findViewById(R.id.cityPictureBox);
            cityPictureBox.setImageResource(dailyPlan.getCity_imageRes());

            TextView dateBox = (TextView)layout.findViewById(R.id.dateBox);
            TextView yearBox = (TextView)layout.findViewById(R.id.yearBox);
            TextView daysBox = (TextView)layout.findViewById(R.id.daysBox);

            String dateStr = DateMain.getDateStr(dailyPlan.getDate_timestamp());
            dateBox.setText(dateStr.substring(0, dateStr.length() - 6));
            daysBox.setText(String.valueOf(dailyPlan.getCity_days()));

            yearBox.setText(dateStr.substring(dateStr.length() - 4, dateStr.length()));

            TextView dayNumBox = (TextView)layout.findViewById(R.id.dayNumBox);
            dayNumBox.setText(getString(R.string.day) + String.valueOf(ii));
            TextView attractionsButton = (TextView)layout.findViewById(R.id.btn_add_attractions);
            TextView hotelButton = (TextView)layout.findViewById(R.id.btn_add_hotel);
            TextView restaurantButton = (TextView)layout.findViewById(R.id.btn_add_restaurant);
            TextView servicesButton = (TextView)layout.findViewById(R.id.btn_add_services);
            TextView lb_attrations = (TextView)layout.findViewById(R.id.lb_attractions);
            TextView lb_hotels = (TextView)layout.findViewById(R.id.lb_hotels);
            TextView lb_restaurants = (TextView)layout.findViewById(R.id.lb_restaurants);
            TextView lb_services = (TextView)layout.findViewById(R.id.lb_services);
            LinearLayout attractionsLayout = (LinearLayout)layout.findViewById(R.id.layout_attractions);
            LinearLayout hotelsLayout = (LinearLayout)layout.findViewById(R.id.layout_hotels);
            LinearLayout restaurantsLayout = (LinearLayout)layout.findViewById(R.id.layout_restaurants);
            LinearLayout servicesLayout = (LinearLayout)layout.findViewById(R.id.layout_services);

            attractionsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Commons.dailyPlan = dailyPlan;
                    Log.d("DailyPlan ID", String.valueOf(dailyPlan.getId()));
                    Intent intent = new Intent(getApplicationContext(), AttractionsListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });

            hotelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Commons.dailyPlan = dailyPlan;
                    Log.d("DailyPlan ID", String.valueOf(dailyPlan.getId()));
                    Intent intent = new Intent(getApplicationContext(), HotelListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });

            restaurantButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Commons.dailyPlan = dailyPlan;
                    Log.d("DailyPlan ID", String.valueOf(dailyPlan.getId()));
                    Intent intent = new Intent(getApplicationContext(), RestaurantListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });

            servicesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Commons.dailyPlan = dailyPlan;
                    Log.d("DailyPlan ID", String.valueOf(dailyPlan.getId()));
                    Intent intent = new Intent(getApplicationContext(), ServicesActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });

            ImageView delButton = (ImageView)layout.findViewById(R.id.btn_cancel);
            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialogForQuestion(getString(R.string.warning), getString(R.string.sure_delete_city), DailyScheduleActivity.this, null, new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {

                            return null;
                        }
                    });
                }
            });

            if(dailyPlan.getAttractionsArrayList().size() > 0){
                lb_attrations.setVisibility(View.VISIBLE);
                attractionsLayout.removeAllViews();
                for(Attractions attractions:dailyPlan.getAttractionsArrayList()){
                    LayoutInflater inflater1 = getLayoutInflater();
                    View layout1 = inflater1.inflate(R.layout.item_daily_attractions, null);
                    TextView name = (TextView) layout1.findViewById(R.id.nameBox);
                    TextView distance = (TextView) layout1.findViewById(R.id.distanceBox);
                    TextView walking = (TextView) layout1.findViewById(R.id.walkingBox);
                    TextView departure_time = (TextView) layout1.findViewById(R.id.departureBox);
                    TextView total_time = (TextView) layout1.findViewById(R.id.totalTimeBox);
                    ImageView image = (ImageView) layout1.findViewById(R.id.pictureBox);

                    name.setText(attractions.getName());
                    walking.setText(String.valueOf(attractions.getWalking()/1000) + " km");
                    departure_time.setText(attractions.getDeparture_time());
                    total_time.setText((String.valueOf(attractions.getTotal_time()).endsWith(".0")?String.valueOf(attractions.getTotal_time()).replace(".0",""):
                            String.valueOf(attractions.getTotal_time()))
                            + " " + getString(R.string.hours));

                    if(Commons.thisUser.getLatLng() != null){
                        Location myLocation = new Location("MyLocation");
                        myLocation.setLatitude(Commons.thisUser.getLatLng().latitude);
                        myLocation.setLongitude(Commons.thisUser.getLatLng().longitude);
                        Location attractionsLocation = new Location("Attractions Location");
                        attractionsLocation.setLatitude(attractions.getLatLng().latitude);
                        attractionsLocation.setLongitude(attractions.getLatLng().longitude);
                        double dist = myLocation.distanceTo(attractionsLocation);
                        distance.setText(df.format(dist/1000) + " km");
                    }else {
                        distance.setText(df.format(attractions.getDistance()/1000) + " km");
                    }

                    if(attractions.getPicture_url().length() > 0){
                        Glide.with(getApplicationContext())
                                .load(attractions.getPicture_url())
                                .error(R.drawable.noresult)
                                .into(image);
                    }

                    layout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAlertDialogEdit(dailyPlan, dailyPlan.getAttractionsArrayList().indexOf(attractions), "attractions");
                        }
                    });

                    attractionsLayout.addView(layout1);
                }
            }

            if(dailyPlan.getHotelArrayList().size() > 0){
                lb_hotels.setVisibility(View.VISIBLE);
                hotelsLayout.removeAllViews();
                for(Hotel hotel:dailyPlan.getHotelArrayList()){
                    LayoutInflater inflater1 = getLayoutInflater();
                    View layout1 = inflater1.inflate(R.layout.item_daily_hotel, null);
                    TextView nameBox = (TextView) layout1.findViewById(R.id.nameBox);
                    TextView cityBox = (TextView) layout1.findViewById(R.id.cityNameBox);
                    TextView distance = (TextView) layout1.findViewById(R.id.distanceBox);
                    TextView priceBox = (TextView) layout1.findViewById(R.id.priceBox);
                    TextView servicesBox = (TextView) layout1.findViewById(R.id.servicesBox);
                    ImageView image = (ImageView) layout1.findViewById(R.id.pictureBox);

                    nameBox.setText(hotel.getName());
                    cityBox.setText(hotel.getCity());
                    priceBox.setText(String.valueOf(hotel.getPrice()) + " " + hotel.getUnit());
                    servicesBox.setText(hotel.getServices());

                    if(Commons.thisUser.getLatLng() != null){
                        Location myLocation = new Location("MyLocation");
                        myLocation.setLatitude(Commons.thisUser.getLatLng().latitude);
                        myLocation.setLongitude(Commons.thisUser.getLatLng().longitude);
                        Location hotel_location = new Location("HomeHotelFrag Location");
                        hotel_location.setLatitude(hotel.getLatLng().latitude);
                        hotel_location.setLongitude(hotel.getLatLng().longitude);
                        double dist = myLocation.distanceTo(hotel_location);
                        distance.setText(df.format(dist/1000) + " km");
                    }else {
                        distance.setText(df.format(hotel.getDistance()/1000) + " km");
                    }

                    if(hotel.getPicture_url().length() > 0){
                        Glide.with(getApplicationContext())
                                .load(hotel.getPicture_url())
                                .error(R.drawable.noresult)
                                .into(image);
                    }

                    layout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAlertDialogEdit(dailyPlan, dailyPlan.getHotelArrayList().indexOf(hotel), "hotel");
                        }
                    });

                    hotelsLayout.addView(layout1);
                }
            }

            if(dailyPlan.getRestaurantArrayList().size() > 0){
                lb_restaurants.setVisibility(View.VISIBLE);
                restaurantsLayout.removeAllViews();
                for(Restaurant restaurant:dailyPlan.getRestaurantArrayList()){
                    LayoutInflater inflater1 = getLayoutInflater();
                    View layout1 = inflater1.inflate(R.layout.item_daily_restaurant, null);
                    TextView nameBox = (TextView) layout1.findViewById(R.id.nameBox);
                    TextView cityBox = (TextView) layout1.findViewById(R.id.cityNameBox);
                    TextView distanceBox = (TextView) layout1.findViewById(R.id.distanceBox);
                    TextView statusBox = (TextView) layout1.findViewById(R.id.statusBox);
                    TextView menuBox = (TextView) layout1.findViewById(R.id.menuBox);
                    ImageView image = (ImageView) layout1.findViewById(R.id.pictureBox);

                    nameBox.setText(restaurant.getName());
                    cityBox.setText(restaurant.getCity());
                    statusBox.setText(restaurant.getStatus().equals("open")?"Open Now":"Closed Now");
                    String menus = "";
                    for(RestaurantMenu menu:restaurant.getRestaurantMenuArrayList()){
                        String space = "";
                        if(menus.length() > 0) space = ",";
                        menus += space + menu.getName();
                    }
                    menuBox.setText(menus);

                    if(Commons.thisUser.getLatLng() != null){
                        Location myLocation = new Location("MyLocation");
                        myLocation.setLatitude(Commons.thisUser.getLatLng().latitude);
                        myLocation.setLongitude(Commons.thisUser.getLatLng().longitude);
                        Location restaurant_location = new Location("HomeRestaurantFrag Location");
                        restaurant_location.setLatitude(restaurant.getLatLng().latitude);
                        restaurant_location.setLongitude(restaurant.getLatLng().longitude);
                        double dist = myLocation.distanceTo(restaurant_location);
                        distanceBox.setText(df.format(dist/1000) + " km");
                    }else {
                        distanceBox.setText(df.format(restaurant.getDistance()/1000) + " km");
                    }

                    if(restaurant.getPicture_url().length() > 0){
                        Glide.with(getApplicationContext())
                                .load(restaurant.getPicture_url())
                                .error(R.drawable.noresult)
                                .into(image);
                    }

                    layout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAlertDialogEdit(dailyPlan, dailyPlan.getRestaurantArrayList().indexOf(restaurant), "restaurant");
                        }
                    });

                    restaurantsLayout.addView(layout1);
                }
            }

            if(dailyPlan.getGuideArrayList().size() > 0){
                lb_services.setVisibility(View.VISIBLE);
//                servicesLayout.removeAllViews();
                for(Guide guide:dailyPlan.getGuideArrayList()){
                    LayoutInflater inflater1 = getLayoutInflater();
                    View layout1 = inflater1.inflate(R.layout.item_daily_guide, null);
                    TextView nameBox = (TextView) layout1.findViewById(R.id.nameBox);
                    TextView cityBox = (TextView) layout1.findViewById(R.id.cityNameBox);
                    TextView languageBox = (TextView) layout1.findViewById(R.id.languageBox);
                    TextView priceBox = (TextView) layout1.findViewById(R.id.priceBox);
                    CircleImageView pictureBox = (CircleImageView) layout1.findViewById(R.id.pictureBox);

                    nameBox.setText(guide.getName());
                    cityBox.setText(guide.getCity());
                    languageBox.setText(guide.getLanguage());
                    priceBox.setText(String.valueOf(guide.getPrice()) + " " + guide.getUnit());
                    if (guide.getPicture_url().length() > 0) {
                        Glide.with(getApplicationContext()).load(guide.getPicture_url()).into(pictureBox);
                    }
                    layout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAlertDialogEdit(dailyPlan, dailyPlan.getGuideArrayList().indexOf(guide), "guide");
                        }
                    });
                    servicesLayout.addView(layout1);
                }
            }

            if(dailyPlan.getCarRentArrayList().size() > 0){
                lb_services.setVisibility(View.VISIBLE);
//                servicesLayout.removeAllViews();
                for(CarRent carRent:dailyPlan.getCarRentArrayList()){
                    LayoutInflater inflater1 = getLayoutInflater();
                    View layout1 = inflater1.inflate(R.layout.item_daily_carrent, null);
                    TextView nameBox = (TextView) layout1.findViewById(R.id.nameBox);
                    TextView cityBox = (TextView) layout1.findViewById(R.id.cityNameBox);
                    TextView seaterBox = (TextView) layout1.findViewById(R.id.seaterBox);
                    TextView priceBox = (TextView) layout1.findViewById(R.id.priceBox);
                    ImageView pictureBox = (ImageView) layout1.findViewById(R.id.pictureBox);

                    nameBox.setText(carRent.getName());
                    cityBox.setText(carRent.getCity());
                    seaterBox.setText(String.valueOf(carRent.getSeaters()) + " " + getString(R.string.seaters) + (carRent.getColor().length() > 0?" | " + carRent.getColor():""));
                    priceBox.setText(String.valueOf(carRent.getWeekly_price()) + " " + carRent.getUnit());
                    if (carRent.getPicture_url().length() > 0) {
                        Glide.with(getApplicationContext()).load(carRent.getPicture_url()).into(pictureBox);
                    }
                    layout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAlertDialogEdit(dailyPlan, dailyPlan.getCarRentArrayList().indexOf(carRent), "car_rent");
                        }
                    });
                    servicesLayout.addView(layout1);
                }
            }

            if(dailyPlan.getEquipmentArrayList().size() > 0){
                lb_services.setVisibility(View.VISIBLE);
//                servicesLayout.removeAllViews();
                for(Equipment equipment:dailyPlan.getEquipmentArrayList()){
                    LayoutInflater inflater1 = getLayoutInflater();
                    View layout1 = inflater1.inflate(R.layout.item_daily_equipment, null);
                    TextView nameBox = (TextView) layout1.findViewById(R.id.nameBox);
                    TextView cityBox = (TextView) layout1.findViewById(R.id.cityNameBox);
                    TextView sizeBox = (TextView) layout1.findViewById(R.id.sizeBox);
                    TextView priceBox = (TextView) layout1.findViewById(R.id.priceBox);
                    ImageView pictureBox = (ImageView) layout1.findViewById(R.id.pictureBox);

                    nameBox.setText(equipment.getName());
                    cityBox.setText(equipment.getCity());
                    sizeBox.setText(equipment.getSize());
                    priceBox.setText(String.valueOf(equipment.getPrice()) + " " + equipment.getUnit());
                    if (equipment.getPicture_url().length() > 0) {
                        Glide.with(getApplicationContext()).load(equipment.getPicture_url()).into(pictureBox);
                    }
                    layout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAlertDialogEdit(dailyPlan, dailyPlan.getEquipmentArrayList().indexOf(equipment), "equipment");
                        }
                    });
                    servicesLayout.addView(layout1);
                }
            }

            if(dailyPlan.getTransferArrayList().size() > 0){
                lb_services.setVisibility(View.VISIBLE);
//                servicesLayout.removeAllViews();
                for(Transfer transfer:dailyPlan.getTransferArrayList()){
                    LayoutInflater inflater1 = getLayoutInflater();
                    View layout1 = inflater1.inflate(R.layout.item_daily_transfer, null);
                    TextView nameBox = (TextView) layout1.findViewById(R.id.nameBox);
                    TextView cityBox = (TextView) layout1.findViewById(R.id.cityNameBox);
                    TextView minsBox = (TextView) layout1.findViewById(R.id.minsBox);
                    TextView priceBox = (TextView) layout1.findViewById(R.id.priceBox);
                    TextView fromBox = (TextView) layout1.findViewById(R.id.fromBox);
                    TextView toBox = (TextView) layout1.findViewById(R.id.toBox);
                    ImageView pictureBox = (ImageView) layout1.findViewById(R.id.pictureBox);

                    nameBox.setText(transfer.getName());
                    cityBox.setText(transfer.getCity());
                    minsBox.setText(String.valueOf(transfer.getKm_mins()));
                    priceBox.setText(String.valueOf(transfer.getKm_price()) + " " + transfer.getUnit());
                    fromBox.setText(transfer.getFrom_address());
                    toBox.setText(transfer.getTo_address());
                    if (transfer.getPicture_url().length() > 0) {
                        Glide.with(getApplicationContext()).load(transfer.getPicture_url()).into(pictureBox);
                    }
                    layout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAlertDialogEdit(dailyPlan, dailyPlan.getTransferArrayList().indexOf(transfer), "transfer");
                        }
                    });
                    servicesLayout.addView(layout1);
                }
            }

            container.addView(layout);
        }
    }

    private void initTabs(int pos){

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.ic_home, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.newtrip, R.drawable.ic_browser, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.packages, R.drawable.ic_travel, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.account, R.drawable.ic_account, R.color.colorPrimary);

        bottomNavigation.setAccentColor(getColor(R.color.colorPrimary));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.setCurrentItem(pos);

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...

                if(position == 0){
                    Commons.schedule = null;
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
                else if(position == 3){
                    Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

                return true;
            }
        });

    }

    public void back(View view){
        if(Commons.newScheduleActivity != null){
            Intent intent = new Intent(getApplicationContext(), NewScheduleActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }else {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Commons.dailyScheduleActivity = null;
    }

    private void showAlertDialogEdit(DailyPlan dailyPlan, int idx, String option){
        AlertDialog.Builder builder = new AlertDialog.Builder(DailyScheduleActivity.this);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_alert_delete_book, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        TextView deleteButton = (TextView)view.findViewById(R.id.btn_delete);
        deleteButton.setTypeface(bold);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option.equals("attractions")){
                    dailyPlan.getAttractionsArrayList().remove(idx);
                }else if(option.equals("hotel")){
                    dailyPlan.getHotelArrayList().remove(idx);
                }else if(option.equals("restaurant")){
                    dailyPlan.getRestaurantArrayList().remove(idx);
                }else if(option.equals("guide")){
                    dailyPlan.getGuideArrayList().remove(idx);
                }else if(option.equals("car_rent")){
                    dailyPlan.getCarRentArrayList().remove(idx);
                }else if(option.equals("equipment")){
                    dailyPlan.getEquipmentArrayList().remove(idx);
                }else if(option.equals("transfer")){
                    dailyPlan.getTransferArrayList().remove(idx);
                }
                onResume();

                dialog.dismiss();
            }
        });

        TextView bookButton = (TextView)view.findViewById(R.id.btn_book);
        bookButton.setTypeface(bold);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(option.equals("attractions")){
                    Commons.attractions = dailyPlan.getAttractionsArrayList().get(idx);
                    Intent intent = new Intent(getApplicationContext(), AttractionsDetailActivity.class);
                    intent.putExtra("option", "booking");
                    startActivity(intent);
                }else if(option.equals("hotel")){
                    Commons.hotel = dailyPlan.getHotelArrayList().get(idx);
                    Intent intent = new Intent(getApplicationContext(), ScheduledHotelDetailActivity.class);
                    startActivity(intent);
                }else if(option.equals("restaurant")){
                    Commons.restaurant = dailyPlan.getRestaurantArrayList().get(idx);
                    Intent intent = new Intent(getApplicationContext(), ScheduledRestaurantDetailActivity.class);
                    startActivity(intent);
                }else if(option.equals("guide")){
                    Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else if(option.equals("car_rent")){
                    Commons.carRent = dailyPlan.getCarRentArrayList().get(idx);
                    Intent intent = new Intent(getApplicationContext(), CarRentDetailActivity.class);
                    intent.putExtra("option", "booking");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else if(option.equals("equipment")){
                    Commons.equipment = dailyPlan.getEquipmentArrayList().get(idx);
                    Intent intent = new Intent(getApplicationContext(), EquipmentDetailActivity.class);
                    intent.putExtra("option", "booking");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else if(option.equals("transfer")){
                    Commons.transfer = dailyPlan.getTransferArrayList().get(idx);
                    Intent intent = new Intent(getApplicationContext(), ScheduledTransferDetailActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

                dialog.dismiss();
            }
        });

        // Get screen width and height in pixels
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;

        // Initialize a new window manager layout parameters
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        // Copy the alert dialog window attributes to new layout parameter instance
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        // Set alert dialog width equal to screen width 80%
        int dialogWindowWidth = (int) (displayWidth * 0.8f);
        // Set alert dialog height equal to screen height 80%
        //    int dialogWindowHeight = (int) (displayHeight * 0.8f);

        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        //      layoutParams.height = dialogWindowHeight;

        // Apply the newly created layout parameters to the alert dialog window
        dialog.getWindow().setAttributes(layoutParams);
    }

}






























package com.app.quetrip.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.DateMain;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Schedule;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewScheduleActivity extends BaseActivity {

    EditText captionBox;
    TextView fromToBox, daysBox;
    TextView adultCountBox, seniorCountBox, childCountBox, infantCountBox;
    ImageView adultIncButton, adultDecButton, seniorIncButton, seniorDecButton, childIncButton, childDecButton, infantIncButton, infantDecButton;
    int adultCount = 0, seniorCount = 0, childCount = 0, infantCount = 0;
    public ArrayList<Date> dates = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);

        Commons.newScheduleActivity = this;

        captionBox = (EditText)findViewById(R.id.captionBox);
        fromToBox = (TextView)findViewById(R.id.from_to);
        daysBox = (TextView)findViewById(R.id.daysBox);
        adultCountBox = (TextView)findViewById(R.id.count_adult);
        seniorCountBox = (TextView)findViewById(R.id.count_senior);
        childCountBox = (TextView)findViewById(R.id.count_child);
        infantCountBox = (TextView)findViewById(R.id.count_infant);

        adultIncButton = (ImageView) findViewById(R.id.btn_increase_adult);
        adultDecButton = (ImageView)findViewById(R.id.btn_decrease_adult);
        seniorIncButton = (ImageView)findViewById(R.id.btn_increase_senior);
        seniorDecButton = (ImageView)findViewById(R.id.btn_decrease_senior);
        childIncButton = (ImageView)findViewById(R.id.btn_increase_child);
        childDecButton = (ImageView)findViewById(R.id.btn_decrease_child);
        infantIncButton = (ImageView)findViewById(R.id.btn_increase_infant);
        infantDecButton = (ImageView)findViewById(R.id.btn_decrease_infant);

        fromToBox.setText(DateMain.getDateStr(new Date().getTime()) + " ~ " + DateMain.getDateStr(new Date().getTime()));
        dates.add(new Date());
        daysBox.setText(String.valueOf(dates.size()));

        fromToBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleDatePickerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        adultIncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adultCount++;
                adultCountBox.setText(String.valueOf(adultCount));
            }
        });

        adultDecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adultCount > 0) adultCount--;
                adultCountBox.setText(String.valueOf(adultCount));
            }
        });

        seniorIncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seniorCount++;
                seniorCountBox.setText(String.valueOf(seniorCount));
            }
        });

        seniorDecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seniorCount > 0) seniorCount--;
                seniorCountBox.setText(String.valueOf(seniorCount));
            }
        });

        childIncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childCount++;
                childCountBox.setText(String.valueOf(childCount));
            }
        });

        childDecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (childCount > 0) childCount--;
                childCountBox.setText(String.valueOf(childCount));
            }
        });

        infantIncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infantCount++;
                infantCountBox.setText(String.valueOf(infantCount));
            }
        });

        infantDecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (infantCount > 0) infantCount--;
                infantCountBox.setText(String.valueOf(infantCount));
            }
        });

        setupUI(findViewById(R.id.activity), this);

        initTabs(1);

    }

    public void openScheduleMenu(View view){
        openMenuItems();
    }

    public void setTripDates(){
        fromToBox.setText(DateMain.getDateStr(dates.get(0).getTime()) + " ~ " + DateMain.getDateStr(dates.get(dates.size() - 1).getTime()));
        daysBox.setText(String.valueOf(dates.size()));
    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void saveSchedule(View view){
        if(captionBox.getText().toString().trim().length() == 0){
            showToast(getString(R.string.enter_trip_caption));
            return;
        }
        if(adultCount == 0 && seniorCount == 0 && childCount == 0 && infantCount == 0){
            showToast(getString(R.string.setup_travelers));
            return;
        }

        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setMember_id(Commons.thisUser.getIdx());
        schedule.setTitle(captionBox.getText().toString().trim());
        schedule.setStart_timestamp(dates.get(0).getTime());
        schedule.setS_yy(dates.get(0).getYear());
        schedule.setS_MM(dates.get(0).getMonth());
        schedule.setS_dd(dates.get(0).getDay());
        schedule.setEnd_timestamp(dates.get(dates.size() - 1).getTime());
        schedule.setE_yy(dates.get(dates.size() - 1).getYear());
        schedule.setE_MM(dates.get(dates.size() - 1).getMonth());
        schedule.setE_dd(dates.get(dates.size() - 1).getDay());
        schedule.setDays(dates.size());
        schedule.setAdults(adultCount);
        schedule.setSeniors(seniorCount);
        schedule.setChildren(childCount);
        schedule.setInfants(infantCount);

        Commons.schedule = schedule;
        Commons.dailyPlans.clear();

        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

//        showAlertDialogInput(getString(R.string.trip_name), getString(R.string.write_something));
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

    public void showAlertDialogInput(String title, String hint){
        AlertDialog.Builder builder = new AlertDialog.Builder(NewScheduleActivity.this);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_input, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        TextView titleBox = (TextView) view.findViewById(R.id.titleBox);
        titleBox.setText(title);
        EditText inputBox = (EditText)view.findViewById(R.id.inputBox);
        inputBox.setHint(hint);
        inputBox.setTypeface(bold);
        ImageView submitButton = (ImageView)view.findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Schedule schedule = new Schedule();
                schedule.setId(1);
                schedule.setMember_id(Commons.thisUser.getIdx());
                schedule.setTitle(inputBox.getText().toString().trim());
                schedule.setStart_timestamp(dates.get(0).getTime());
                schedule.setS_yy(dates.get(0).getYear());
                schedule.setS_MM(dates.get(0).getMonth());
                schedule.setS_dd(dates.get(0).getDay());
                schedule.setEnd_timestamp(dates.get(dates.size() - 1).getTime());
                schedule.setE_yy(dates.get(dates.size() - 1).getYear());
                schedule.setE_MM(dates.get(dates.size() - 1).getMonth());
                schedule.setE_dd(dates.get(dates.size() - 1).getDay());
                schedule.setDays(dates.size());
                schedule.setAdults(adultCount);
                schedule.setSeniors(seniorCount);
                schedule.setChildren(childCount);
                schedule.setInfants(infantCount);

                Commons.schedule = schedule;
                Commons.dailyPlans.clear();

                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                dialog.dismiss();
            }
        });
        inputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = inputBox.getText().toString().trim().toLowerCase(Locale.getDefault());
                if(text.length() > 0)submitButton.setVisibility(View.VISIBLE);
                else submitButton.setVisibility(View.GONE);
            }
        });

        ImageView cancelButton = (ImageView)view.findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        int dialogWindowWidth = (int) (displayWidth * 0.85f);
        // Set alert dialog height equal to screen height 80%
        //    int dialogWindowHeight = (int) (displayHeight * 0.8f);

        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        //      layoutParams.height = dialogWindowHeight;

        // Apply the newly created layout parameters to the alert dialog window
        dialog.getWindow().setAttributes(layoutParams);
    }

    private int compareDates(long ts1, long ts2){

        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Commons.newScheduleActivity = null;
    }

    private void openMenuItems() {
        View view = findViewById(R.id.title);
        android.widget.PopupMenu popupMenu = new android.widget.PopupMenu(this, view);
        popupMenu.inflate(R.menu.schedule_menu);
        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = android.widget.PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popupMenu);
            argTypes = new Class[]{boolean.class};
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
        } catch (Exception e) {
            Log.w("Error====>", "error forcing menu icons to show", e);
            popupMenu.show();
            return;
        }
        popupMenu.show();

    }

    public void getMySchedules(MenuItem item){
        switch (item.getItemId()){
            case 0:
                Intent intent = new Intent(getApplicationContext(), MySchedulesActivity.class);
                startActivity(intent);
                break;
            default:
                intent = new Intent(getApplicationContext(), MySchedulesActivity.class);
                startActivity(intent);
        }
    }

}



































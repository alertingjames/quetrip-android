package com.app.quetrip.main;

import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.DateMain;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.DailyPlan;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Date;

public class InCityScheduleActivity extends BaseActivity {

    TextView cityNameBox, tripTimeBox, tripDaysBox;
    RoundedImageView cityPictureBox;
    private SwitchCompat mSwitchAllDay;
    TextView fromToBox, daysBox;
    FloatingActionButton selectButton;
    public ArrayList<Date> dates = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_city_schedule);

        Commons.inCityScheduleActivity = this;

        selectButton = (FloatingActionButton)findViewById(R.id.btn_sel);

        cityNameBox = (TextView)findViewById(R.id.cityNameBox);
        cityNameBox.setText(Commons.marker.getTitle());

        cityPictureBox = (RoundedImageView)findViewById(R.id.cityPictureBox);
        cityPictureBox.setImageResource(Commons.marker.getImageRes());

        tripTimeBox = (TextView)findViewById(R.id.tripTimeBox);
        tripTimeBox.setText(DateMain.getDateStr(Commons.schedule.getStart_timestamp()) + " ~ " + DateMain.getDateStr(Commons.schedule.getEnd_timestamp()));

        tripDaysBox = (TextView)findViewById(R.id.tripDaysBox);
        tripDaysBox.setText(String.valueOf(Commons.schedule.getDays()));
        mSwitchAllDay = (SwitchCompat) findViewById(R.id.switch_all_day);

        fromToBox = (TextView)findViewById(R.id.from_to);
        daysBox = (TextView)findViewById(R.id.daysBox);

        fromToBox.setText(DateMain.getDateStr(new Date().getTime()) + " ~ " + DateMain.getDateStr(new Date().getTime()));
        daysBox.setText(String.valueOf(dates.size()));

        fromToBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleDatePickerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        mSwitchAllDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    dates.clear();
                    long step = 86400 * 1000;
                    for(long l=Commons.schedule.getStart_timestamp(); l<=Commons.schedule.getEnd_timestamp(); l += step){
                        dates.add(new Date(l));
                    }
                    setTripDates();
                }else {
                    dates.clear();
                    fromToBox.setText(DateMain.getDateStr(new Date().getTime()) + " ~ " + DateMain.getDateStr(new Date().getTime()));
                    daysBox.setText(String.valueOf(dates.size()));
                }
            }
        });

        if(Commons.schedule != null && Commons.schedule.getDailyPlans().size() > 0)
            selectButton.setVisibility(View.VISIBLE);
        else
            selectButton.setVisibility(View.GONE);

        initTabs(1);

    }

    public void toEditInScheduleCity(View view){
        Intent intent = new Intent(getApplicationContext(), ScheduledCityListActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    public void setTripDates(){
        fromToBox.setText(DateMain.getDateStr(dates.get(0).getTime()) + " ~ " + DateMain.getDateStr(dates.get(dates.size() - 1).getTime()));
        daysBox.setText(String.valueOf(dates.size()));
    }

    public void saveCitySchedule(View view){
        if(dates.size() == 0){
            showToast(getString(R.string.select_stay_dates));
            return;
        }

        if(mSwitchAllDay.isChecked())Commons.dailyPlans.clear();

        int index = 0;
        if(Commons.dailyPlans.size() > 0){
            index = Commons.dailyPlans.size();
        }
        for(Date date: dates){
            index++;
            DailyPlan dailyPlan = new DailyPlan();
            dailyPlan.setSchedule_id(Commons.schedule.getId());
            dailyPlan.setId(index);
            dailyPlan.setCity_name(Commons.marker.getTitle());
            dailyPlan.setCity_imageRes(Commons.marker.getImageRes());
            dailyPlan.setDate_timestamp(date.getTime());
            dailyPlan.setYy(date.getYear());
            dailyPlan.setMM(date.getMonth());
            dailyPlan.setDd(date.getDay());
            dailyPlan.setStart_timestamp(dates.get(0).getTime());
            dailyPlan.setS_yy(dates.get(0).getYear());
            dailyPlan.setS_MM(dates.get(0).getMonth());
            dailyPlan.setS_dd(dates.get(0).getDay());
            dailyPlan.setEnd_timestamp(dates.get(dates.size() - 1).getTime());
            dailyPlan.setE_yy(dates.get(dates.size() - 1).getYear());
            dailyPlan.setE_MM(dates.get(dates.size() - 1).getMonth());
            dailyPlan.setE_dd(dates.get(dates.size() - 1).getDay());
            dailyPlan.setCity_days(dates.size());
            Commons.dailyPlans.add(dailyPlan);
        }
        Commons.schedule.setDailyPlans(Commons.dailyPlans);

        Intent intent = null;
        if(mSwitchAllDay.isChecked()
                || (dates.get(0).getTime() == Commons.schedule.getStart_timestamp() && dates.get(dates.size() - 1).getTime() == Commons.schedule.getEnd_timestamp())
                || (Commons.schedule.getDailyPlans().size() == Commons.schedule.getDays()))
            intent = new Intent(getApplicationContext(), DailyScheduleActivity.class);
        else {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            if(Commons.citySummaryActivity != null)Commons.citySummaryActivity.finish();
        }
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Commons.inCityScheduleActivity = null;
    }
}



































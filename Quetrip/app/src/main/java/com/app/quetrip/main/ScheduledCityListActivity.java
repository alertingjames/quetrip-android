package com.app.quetrip.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.DateMain;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.DailyPlan;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ScheduledCityListActivity extends BaseActivity {

    LinearLayout container;
    ArrayList<DailyPlan> dailyPlans = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_city_list);

        Commons.scheduledCityListActivity = this;

        container = (LinearLayout)findViewById(R.id.container);

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    class CitySchedule{
        String cityName = "";
        long startTimestamp = 0;
        public CitySchedule(String city, long sTimestamp){
            this.cityName = city;
            this.startTimestamp = sTimestamp;
        }
    }

    private void getData(){
        container.removeAllViews();
        ArrayList<CitySchedule> citySchedules = new ArrayList<>();
        for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.item_in_schedule_city_list, null);
            CitySchedule citySchedule = new CitySchedule(dailyPlan.getCity_name(), dailyPlan.getStart_timestamp());
            if(!isContain(citySchedules, citySchedule)){
                TextView cityNameBox = (TextView)layout.findViewById(R.id.cityNameBox);
                cityNameBox.setText(dailyPlan.getCity_name());

                RoundedImageView cityPictureBox = (RoundedImageView)layout.findViewById(R.id.cityPictureBox);
                cityPictureBox.setImageResource(dailyPlan.getCity_imageRes());

                TextView fromToBox = (TextView)layout.findViewById(R.id.from_to);
                TextView daysBox = (TextView)layout.findViewById(R.id.daysBox);

                fromToBox.setText(DateMain.getDateStr(dailyPlan.getStart_timestamp()) + " ~ " + DateMain.getDateStr(dailyPlan.getEnd_timestamp()));
                daysBox.setText(String.valueOf(dailyPlan.getCity_days()));

                ImageView delButton = (ImageView)layout.findViewById(R.id.btn_cancel);
                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showAlertDialogForQuestion(getString(R.string.warning), getString(R.string.sure_delete_city), ScheduledCityListActivity.this, null, new Callable<Void>() {
                            @Override
                            public Void call() throws Exception {
                                for(int i=0; i<Commons.schedule.getDailyPlans().size(); i++){
                                    DailyPlan plan = Commons.schedule.getDailyPlans().get(i);
                                    if(plan.getCity_name().equals(dailyPlan.getCity_name()) && plan.getStart_timestamp() == dailyPlan.getStart_timestamp()){
                                        Commons.dailyPlans.remove(plan);
                                        Commons.schedule.getDailyPlans().remove(plan);
                                        Log.d("Schedule Size!!!", String.valueOf(Commons.schedule.getDailyPlans().size()));
                                    }
                                    if(i == Commons.schedule.getDailyPlans().size() - 1){
                                        citySchedules.remove(citySchedule);
                                        container.removeView(layout);
                                    }
                                }
                                return null;
                            }
                        });
                    }
                });

                citySchedules.add(citySchedule);
                container.addView(layout);
            }
        }
    }

    private boolean isContain(ArrayList<CitySchedule> citySchedules, CitySchedule citySchedule){
        for(CitySchedule schedule:citySchedules){
            if(schedule.cityName.equals(citySchedule.cityName) && schedule.startTimestamp == citySchedule.startTimestamp){
                return true;
            }
        }
        return false;
    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Commons.scheduledCityListActivity = null;
    }
}


































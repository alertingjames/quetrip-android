package com.app.quetrip.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.DateMain;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.DailyPlan;
import com.savvi.rangedatepicker.CalendarPickerView;
import com.savvi.rangedatepicker.SubTitle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

public class ScheduleDatePickerActivity extends BaseActivity {

    CalendarPickerView calendar;
    ImageView selectButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_date_picker);

        selectButton = (ImageView)findViewById(R.id.btn_select);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 10);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, 0);

        calendar = findViewById(R.id.calendar_view);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
//        calendar.deactivateDates(list);

        ArrayList<Date> arrayList = new ArrayList<>();
        if(Commons.schedule != null && Commons.scheduledCityListActivity == null){
            for(DailyPlan existingPlan:Commons.schedule.getDailyPlans()){
                arrayList.add(new Date(existingPlan.getDate_timestamp()));
            }
        }

        calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault())) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
//                .withDeactivateDates(list)
                .withSubTitles(getSubTitles())
                .withHighlightedDates(arrayList)
        ;

        calendar.scrollToDate(new Date());

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });

    }

    private ArrayList<SubTitle> getSubTitles() {
        final ArrayList<SubTitle> subTitles = new ArrayList<>();
        if(Commons.schedule != null){
            if(Commons.schedule.getDailyPlans().size() > 0){
                for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
                    subTitles.add(new SubTitle(new Date(dailyPlan.getDate_timestamp()), dailyPlan.getCity_name()));
                }
            }else {
                long step = 86400 * 1000;
                for(long l=Commons.schedule.getStart_timestamp(); l<=Commons.schedule.getEnd_timestamp(); l += step){
                    subTitles.add(new SubTitle(new Date(l), Commons.schedule.getTitle()));
                }
            }
        }
        return subTitles;
    }

    private void select(){
        if(Commons.inCityScheduleActivity != null){
            if(calendar.getSelectedDates().size() == 0){
                showToast(getString(R.string.select_stay_dates));
                return;
            }
            if(calendar.getSelectedDates().get(calendar.getSelectedDates().size() - 1).getTime() > Commons.schedule.getEnd_timestamp()
                || calendar.getSelectedDates().get(0).getTime() < Commons.schedule.getStart_timestamp()){
                showToast(getString(R.string.exceeded_city_date));
                return;
            }
            ArrayList<DailyPlan> duplicateds = new ArrayList<>();
            if(Commons.schedule.getDailyPlans().size() > 0){
                for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
                    if(calendar.getSelectedDates().contains(new Date(dailyPlan.getDate_timestamp()))){
                        DailyPlan duplicated = new DailyPlan();
                        duplicated.setDate_timestamp(dailyPlan.getDate_timestamp());
                        duplicated.setCity_name(dailyPlan.getCity_name());
                        duplicateds.add(duplicated);
                    }
                    int index = Commons.schedule.getDailyPlans().indexOf(dailyPlan);
                    if (index == Commons.schedule.getDailyPlans().size() - 1){
                        if(duplicateds.size() > 0){
                            String text = getString(R.string.duplicated) + ":";
                            for(DailyPlan dup:duplicateds){
                                text += "\n" + DateMain.getDateStr(dup.getDate_timestamp()) + " - " + dup.getCity_name();
                            }
                            showAlertDialogForQuestion(getString(R.string.warning), text + "\n" + getString(R.string.you_agree), ScheduleDatePickerActivity.this, new Callable<Void>() {
                                @Override
                                public Void call() throws Exception {

                                    return null;
                                }
                            }, new Callable<Void>() {
                                @Override
                                public Void call() throws Exception {
                                    Commons.inCityScheduleActivity.dates.clear();
                                    Commons.inCityScheduleActivity.dates.addAll(calendar.getSelectedDates());
                                    Commons.inCityScheduleActivity.setTripDates();
                                    finish();
                                    return null;
                                }
                            });
                        }else {
                            Commons.inCityScheduleActivity.dates.clear();
                            Commons.inCityScheduleActivity.dates.addAll(calendar.getSelectedDates());
                            Commons.inCityScheduleActivity.setTripDates();
                            finish();
                        }
                    }
                }
            }else {
                Commons.inCityScheduleActivity.dates.clear();
                Commons.inCityScheduleActivity.dates.addAll(calendar.getSelectedDates());
                Commons.inCityScheduleActivity.setTripDates();
                finish();
            }
        } else if(Commons.newScheduleActivity != null){
            if(calendar.getSelectedDates().size() == 0){
                showToast(getString(R.string.select_trip_dates));
                return;
            }
            Commons.newScheduleActivity.dates.clear();
            Commons.newScheduleActivity.dates.addAll(calendar.getSelectedDates());
            Commons.newScheduleActivity.setTripDates();
            finish();
        }
    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}































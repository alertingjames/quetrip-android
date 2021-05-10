package com.app.quetrip.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.R;
import com.app.quetrip.classes.DateMain;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.main.DailyScheduleActivity;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.CustomViewHolder> {
    private List<Schedule> scheduleList;
            Context context;

    public ScheduleListAdapter(Context context, ArrayList<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_trip, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
        customViewHolder.setSchedule(scheduleList.get(i));
        customViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Commons.schedule = scheduleList.get(i);
                Intent intent = new Intent(context, DailyScheduleActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public void add(List<Attractions> attractionsList) {
        attractionsList.addAll(attractionsList);
    }

    public void reset() {
        scheduleList.clear();
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView titleBox;
        protected TextView tripTimeBox, tripDaysBox, adultsBox, childrenBox;
        protected LinearLayout layout;

        public CustomViewHolder(View view) {
            super(view);

            this.titleBox = (TextView) view.findViewById(R.id.titleBox);
            this.tripTimeBox = (TextView) view.findViewById(R.id.tripTimeBox);
            this.tripDaysBox = (TextView) view.findViewById(R.id.tripDaysBox);
            this.adultsBox = (TextView) view.findViewById(R.id.adultsBox);
            this.childrenBox = (TextView) view.findViewById(R.id.childrenBox);
            this.layout = (LinearLayout) view.findViewById(R.id.layout);
        }

        public void setSchedule(Schedule schedule) {
            titleBox.setText(schedule.getTitle());
            tripTimeBox.setText(DateMain.getDateStr(schedule.getStart_timestamp()) + " ~ " + DateMain.getDateStr(schedule.getEnd_timestamp()));
            tripDaysBox.setText(String.valueOf(schedule.getDays()));
            adultsBox.setText(String.valueOf(schedule.getAdults()));
            childrenBox.setText(String.valueOf(schedule.getChildren()));
        }
    }

}


































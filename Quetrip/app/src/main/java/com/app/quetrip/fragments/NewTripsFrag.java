package com.app.quetrip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.R;
import com.app.quetrip.adapters.ScheduleListAdapter;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.Schedule;

import java.util.ArrayList;

public class NewTripsFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    ArrayList<Schedule> schedules = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_list, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);

        for(int i=0;i<30; i++){
            ArrayList<DailyPlan> dailyPlans = new ArrayList<>();
            Schedule schedule = new Schedule(i+1, Commons.thisUser.getIdx(), "My Trip", 1576791271000l,
                    2019, 11, 19, 1577655271000l, 2019,
                    11, 29, 11, 1,
                    0, 1, 0, "", dailyPlans);

            schedules.add(Commons.schedule != null?Commons.schedule:schedule);
        }
        initRecyclerView(schedules);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView(ArrayList<Schedule> items) {
        ScheduleListAdapter adapter = new ScheduleListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
    }

    public static Fragment newInstance() {
        return new NewTripsFrag();
    }

}
























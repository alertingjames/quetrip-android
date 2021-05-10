package com.app.quetrip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.R;
import com.app.quetrip.adapters.CarRentListAdapter;
import com.app.quetrip.models.CarRent;

import java.util.ArrayList;

public class CarRentFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    ArrayList<CarRent> carRents = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_list, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);

        for(int i=0;i<30; i++){
            CarRent guide = new CarRent(i+1, 1, "Hyundai Elantra", "Portuguese", "+58123456789", "Goias， State of Goias 76600-000， Brazil", "Brazil", "Goias", "76600-000",
                    -16.1499614, -51.149913, 15000, "",
                    "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3nImOPVNAXqciHQsWZ5kmwsK4i93i-bJ9M0-8hnxBED36WK4l&s", 150,450,
                    "USD", "Air conditioning, Automatic transmission, Unlimited mileage",
                    "", 4, "White", "Free cancellation", getString(R.string.car_rent_desc),
                    10, 4.2f, 22, 0, "");
            carRents.add(guide);
        }
        initRecyclerView(carRents);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView(ArrayList<CarRent> items) {
        CarRentListAdapter adapter = new CarRentListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
    }

    public static Fragment newInstance() {
        return new CarRentFrag();
    }

}
























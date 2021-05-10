package com.app.quetrip.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.Interfaces.AttractionsItemClickListener;
import com.app.quetrip.Interfaces.RestaurantItemClickListener;
import com.app.quetrip.R;
import com.app.quetrip.adapters.HomeAttractionsListAdapter;
import com.app.quetrip.adapters.HomeRestaurantListAdapter;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.main.AttractionsDetailActivity;
import com.app.quetrip.main.RestaurantDetailActivity;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.Restaurant;

import java.util.ArrayList;

public class HomeAttractionsFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    ArrayList<Attractions> attractionsArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_service, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
        recyclerView.setNestedScrollingEnabled(false);

        for(int i=0;i<30;i++){
            Attractions attractions = new Attractions(i+1, 1, "Historic Centre of the Town of Goiás", "en", "Goias， State of Goias 76600-000， Brazil", "Brazil", "Goias", "76600-000",
                    -16.1499614, -51.149913, "https://dimg04.c-ctrip.com/images/100n14000000w5mxmBA1D_C_750_350.jpg?proc=source%2ftrip", 12000, 2000, "Medium", "Circular", "8:00 AM", 2, 50,
                    "USD", "Lift Gain", getString(R.string.attractions_desc), 22, 4.2f);
            attractionsArrayList.add(attractions);
        }

        initRecyclerView(attractionsArrayList);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView(ArrayList<Attractions> items) {
        HomeAttractionsListAdapter adapter = new HomeAttractionsListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AttractionsItemClickListener() {
            @Override
            public void onItemClick(Attractions attractions) {
                Commons.attractions = attractions;
                Intent intent=new Intent(getActivity(), AttractionsDetailActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    public static Fragment newInstance() {
        return new HomeAttractionsFrag();
    }

}

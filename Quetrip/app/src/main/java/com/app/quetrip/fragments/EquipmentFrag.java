package com.app.quetrip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.R;
import com.app.quetrip.adapters.EquipmentListAdapter;
import com.app.quetrip.models.Equipment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class EquipmentFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    ArrayList<Equipment> equipments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_list, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);

        for(int i=0;i<30; i++){
            Equipment equipment = new Equipment(i+1, 1, "Fire Extinguisher - Class ABC, 5 lb", "Portuguese", "+58123456789", "Goias， State of Goias 76600-000， Brazil", "Brazil", "Goias", "76600-000",
                    new LatLng(-16.1499614, -51.149913), 15000, "8 x 5 x 16\"", "https://fireextinguisherdepot.com/media/catalog/product/cache/1/thumbnail/600x700/9df78eab33525d08d6e5fb8d27136e95/a/b/abc_10lb.jpg",
                    65.0f, "USD", getString(R.string.equip_desc),22,
                    (float) 4.2, 30,
                    0, "");
            equipments.add(equipment);
        }
        initRecyclerView(equipments);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView(ArrayList<Equipment> items) {
        EquipmentListAdapter adapter = new EquipmentListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
    }

    public static Fragment newInstance() {
        return new EquipmentFrag();
    }

}
























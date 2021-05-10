package com.app.quetrip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.RestaurantMenuListAdapter;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.RestaurantMenu;

import java.util.ArrayList;

public class SweetFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    ArrayList<RestaurantMenu> menus = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_list, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        for(int i=0;i<30; i++){
            RestaurantMenu menu = new RestaurantMenu(i+1, Commons.restaurant,
                    "Calzone", "https://images-gmi-pmc.edge-generalmills.com/6b36a62f-3083-499d-9165-f7219c086206.jpg",
                    "sweet_pizza", 7.00f, "USD", "medium", 0, getString(R.string.menu_desc));
            menus.add(menu);
        }
        initRecyclerView(menus);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView(ArrayList<RestaurantMenu> items) {
        RestaurantMenuListAdapter adapter = new RestaurantMenuListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
    }

    public static Fragment newInstance() {
        return new SweetFrag();
    }

}
































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
import com.app.quetrip.adapters.TransferListAdapter;
import com.app.quetrip.models.CarRent;
import com.app.quetrip.models.Transfer;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class TransferFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    ArrayList<Transfer> transfers = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_list, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);

        for(int i=0;i<30; i++){
            Transfer transfer = new Transfer(i+1, 1, "Minibus", "Portuguese", "+58123456789", "Goias， State of Goias 76600-000， Brazil", "Brazil", "Goias", "76600-000",
                    new LatLng(-16.1499614, -51.149913), 15000, "", null,
                    "", null, "https://global-free-classified-ads-s02.r.worldssl.net/user_images/5516743.jpg", 0.15f,0,
                    "USD", 5.9f, "", "Free cancellation", getString(R.string.transfer_desc), 4.2f, 22, 0, "");
            transfers.add(transfer);
        }

        initRecyclerView(transfers);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView(ArrayList<Transfer> items) {
        TransferListAdapter adapter = new TransferListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
    }

    public static Fragment newInstance() {
        return new TransferFrag();
    }

}
























package com.app.quetrip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.R;
import com.app.quetrip.adapters.GuideListAdapter;
import com.app.quetrip.models.Guide;

import java.util.ArrayList;

public class GuideFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    ArrayList<Guide> guides = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_list, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);

        for(int i=0;i<30; i++){
            Guide guide = new Guide(i+1, "Maria Eduarda", "mariaeduarda@gmail.com", "+58123456789",
                    "https://www.superprof.com.br/imagens/anuncios/professor-home-estudante-matematica-ufsc-blumenau-aula-matematica-para-estudantes-blumenau-cidades-vizinhas.jpg",
                    "Goias， State of Goias 76600-000， Brazil", "Goias", "Portuguese", -16.1499614, -51.149913,
                    10, "USD", 4.2f, getString(R.string.guide_desc));
            guides.add(guide);
        }
        initRecyclerView(guides);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView(ArrayList<Guide> items) {
        GuideListAdapter adapter = new GuideListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
    }

    public static Fragment newInstance() {
        return new GuideFrag();
    }

}
























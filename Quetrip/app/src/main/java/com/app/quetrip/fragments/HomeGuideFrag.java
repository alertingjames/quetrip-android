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
import com.app.quetrip.R;
import com.app.quetrip.adapters.GuideListAdapter;
import com.app.quetrip.adapters.HomeAttractionsListAdapter;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.main.AttractionsDetailActivity;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.Guide;

import java.util.ArrayList;

public class HomeGuideFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
    ArrayList<Guide> guideArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_service, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
        recyclerView.setNestedScrollingEnabled(false);

        for(int i=0;i<30; i++){
            Guide guide = new Guide(i+1, "Maria Eduarda", "mariaeduarda@gmail.com", "+58123456789",
                    "https://www.superprof.com.br/imagens/anuncios/professor-home-estudante-matematica-ufsc-blumenau-aula-matematica-para-estudantes-blumenau-cidades-vizinhas.jpg",
                    "Goias， State of Goias 76600-000， Brazil", "Goias", "Portuguese", -16.1499614, -51.149913,
                    10, "USD", 4.2f, getString(R.string.guide_desc));
            guideArrayList.add(guide);
        }

        initRecyclerView(guideArrayList);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView(ArrayList<Guide> items) {
        GuideListAdapter adapter = new GuideListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new AttractionsItemClickListener() {
//            @Override
//            public void onItemClick(Attractions attractions) {
//                Commons.attractions = attractions;
//                Intent intent=new Intent(getActivity(), AttractionsDetailActivity.class);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//            }
//        });
    }

    public static Fragment newInstance() {
        return new HomeGuideFrag();
    }

}

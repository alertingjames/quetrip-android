/*
 * Copyright (C) 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.app.quetrip.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quetrip.Interfaces.HotelItemClickListener;
import com.app.quetrip.R;
import com.app.quetrip.adapters.HomeHotelListAdapter;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.main.HotelDetailActivity;
import com.app.quetrip.models.Hotel;

import java.util.ArrayList;

public class HomeHotelFrag extends Fragment {
    private RecyclerView recyclerView;
    private View rootView;
	ArrayList<Hotel> hotels = new ArrayList<>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (View) inflater.inflate(R.layout.frag_service, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleView) ;
		recyclerView.setNestedScrollingEnabled(false);

		for(int i=0;i<50;i++){
			Hotel hotel = new Hotel(i+1, 1, "Zank by Toque Hotel", "en", "", "Rua Almirante Barroso, 161, Salvador, BA", "Brazil", "Salvador", "161",
					-13.0096055,-38.4957249, 12000, "https://roteirosdecharme.com.br/imagens/clientes/conteudo/thumb_m/conteudo-zan_b545128ba82717ab181cb34e1f639f4f.jpg",
					136, "USD", "Pool, Free WiFi, Breakfast Included, Air Conditioning", getString(R.string.hotel_desc2), 22, 4.2f);
			hotels.add(hotel);
		}

		initRecyclerView(hotels);

		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	private void initRecyclerView(ArrayList<Hotel> items) {
		HomeHotelListAdapter adapter = new HomeHotelListAdapter(getActivity(), items);
		recyclerView.setAdapter(adapter);
		adapter.setOnItemClickListener(new HotelItemClickListener() {
			@Override
			public void onItemClick(Hotel hotel) {
				Commons.hotel = hotel;
				Intent intent=new Intent(getActivity(), HotelDetailActivity.class);
				startActivity(intent);
				getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
	}

	public static Fragment newInstance() {
		return new HomeHotelFrag();
	}

}

























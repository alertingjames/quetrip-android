package com.app.quetrip.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.app.quetrip.R;
import com.app.quetrip.adapters.HotelListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.Hotel;

import java.util.ArrayList;
import java.util.Locale;

public class HotelListActivity extends BaseActivity {

    ImageView searchButton, cancelButton, filterButton;
    LinearLayout searchBar;
    EditText ui_edtsearch;
    TextView title;
    ListView list;
    FrameLayout progressBar;

    ArrayList<Hotel> hotels = new ArrayList<>();
    HotelListAdapter hotelListAdapter = new HotelListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        Commons.hotelListActivity = this;

        progressBar = (FrameLayout) findViewById(R.id.loading_bar);
        title = (TextView)findViewById(R.id.title);

        searchBar = (LinearLayout)findViewById(R.id.search_bar);
        searchButton = (ImageView)findViewById(R.id.searchButton);
        cancelButton = (ImageView)findViewById(R.id.cancelButton);
        filterButton = (ImageView)findViewById(R.id.filterButton);

        ui_edtsearch = (EditText)findViewById(R.id.edt_search);
        ui_edtsearch.setFocusable(true);
        ui_edtsearch.requestFocus();

        ui_edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = ui_edtsearch.getText().toString().trim().toLowerCase(Locale.getDefault());
                hotelListAdapter.filter(text);
            }
        });

        list = (ListView) findViewById(R.id.list);

        setupUI((FrameLayout)findViewById(R.id.activity), this);

    }

    public void search(View view){
        cancelButton.setVisibility(View.VISIBLE);
        searchButton.setVisibility(View.GONE);
        searchBar.setVisibility(View.VISIBLE);
        title.setVisibility(View.GONE);
    }

    public void cancelSearch(View view){
        cancelButton.setVisibility(View.GONE);
        searchButton.setVisibility(View.VISIBLE);
        searchBar.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Commons.attractionsListActivity = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData(){
        for(int i=0;i<50;i++){
            Hotel hotel = new Hotel(i+1, 1, "Zank by Toque Hotel", "en", "", "Rua Almirante Barroso, 161, Salvador, BA", "Brazil", "Salvador", "161",
                    -13.0096055,-38.4957249, 12000, "https://roteirosdecharme.com.br/imagens/clientes/conteudo/thumb_m/conteudo-zan_b545128ba82717ab181cb34e1f639f4f.jpg",
                    136, "USD", "Pool, Free WiFi, Breakfast Included, Air Conditioning", getString(R.string.hotel_desc2), 22, 4.2f);
            hotels.add(hotel);
        }
        hotelListAdapter.setDatas(hotels);
        list.setAdapter(hotelListAdapter);

    }

}









































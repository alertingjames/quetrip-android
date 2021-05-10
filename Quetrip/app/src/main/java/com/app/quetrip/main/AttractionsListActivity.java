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
import com.app.quetrip.adapters.AttractionsListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Attractions;

import java.util.ArrayList;
import java.util.Locale;

public class AttractionsListActivity extends BaseActivity {

    ImageView searchButton, cancelButton, filterButton;
    LinearLayout searchBar;
    EditText ui_edtsearch;
    TextView title;
    ListView list;
    FrameLayout progressBar;

    ArrayList<Attractions> attractionsArrayList = new ArrayList<>();
    AttractionsListAdapter attractionListAdapter = new AttractionsListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions_list);

        Commons.attractionsListActivity = this;

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
                attractionListAdapter.filter(text);
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
        for(int i=0;i<30;i++){
            Attractions attractions = new Attractions(i+1, 1, "Historic Centre of the Town of Goiás", "en", "Goias， State of Goias 76600-000， Brazil", "Brazil", "Goias", "76600-000",
                    -16.1499614, -51.149913, "https://dimg04.c-ctrip.com/images/100f14000000w6lh070AD_C_760_506.jpg?proc=source%2ftrip", 12000, 2000, "Medium", "Circular", "8:00 AM", 2, 50,
                    "USD", "Lift Gain", getString(R.string.attractions_desc), 22, 4.2f);
            attractionsArrayList.add(attractions);
        }
        attractionListAdapter.setDatas(attractionsArrayList);
        list.setAdapter(attractionListAdapter);

    }

}

















































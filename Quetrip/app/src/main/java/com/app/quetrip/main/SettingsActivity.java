package com.app.quetrip.main;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.iamhabib.easy_preference.EasyPreference;

public class SettingsActivity extends BaseActivity {

    ImageView backButton;
    TextView titleBox;
    Switch myLocationSwitchButton, mapViewSwitchButton;
    RadioGroup radioGroup;
    RadioButton radioButton, btn_en, btn_pt, btn_es;
    Switch notiSwitchButton;
    String selectedLang = "";

    FrameLayout progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        progressBar = (FrameLayout)findViewById(R.id.loading_bar);

        backButton = (ImageView)findViewById(R.id.btn_back);
        titleBox = (TextView)findViewById(R.id.titleBox);

        myLocationSwitchButton = (Switch)findViewById(R.id.locationSetting);
        mapViewSwitchButton = (Switch)findViewById(R.id.mapviewSetting);

        if(Commons.curMapTypeIndex == 2)mapViewSwitchButton.setChecked(true);
        else mapViewSwitchButton.setChecked(false);

        if(Commons.mapCameraMoveF)myLocationSwitchButton.setChecked(true);
        else myLocationSwitchButton.setChecked(false);
        myLocationSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Commons.mapCameraMoveF = true;
                }else {
                    Commons.mapCameraMoveF = false;
                }
            }
        });

        mapViewSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Commons.curMapTypeIndex = 2;
                }else {
                    Commons.curMapTypeIndex = 1;
                }
                Commons.googleMap.setMapType(LocationActivity.MAP_TYPES[Commons.curMapTypeIndex]);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_en = (RadioButton)findViewById(R.id.en);
        btn_pt = (RadioButton)findViewById(R.id.pt);
        btn_es = (RadioButton)findViewById(R.id.es);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton)findViewById(group.getCheckedRadioButtonId());
                if (radioButton.getText().equals(btn_en.getText())){
                    selectedLang = "en";
                }else if (radioButton.getText().equals(btn_pt.getText())){
                    selectedLang = "pt";
                }else if (radioButton.getText().equals(btn_es.getText())){
                    selectedLang = "es";
                }

                EasyPreference.with(getApplicationContext()).addString("lang", selectedLang).save();
                Commons.lang = selectedLang;
                setLanguage(selectedLang);

            }
        });

        String lang = EasyPreference.with(getApplicationContext()).getString("lang", "pt");
        if(lang.equals("en")){
            btn_en.setChecked(true);
            Commons.lang = "en";
            selectedLang = "en";
        }else if(lang.equals("pt")){
            btn_pt.setChecked(true);
            Commons.lang = "pt";
            selectedLang = "ro";
        }else if(lang.equals("es")){
            btn_es.setChecked(true);
            Commons.lang = "es";
            selectedLang = "es";
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}





























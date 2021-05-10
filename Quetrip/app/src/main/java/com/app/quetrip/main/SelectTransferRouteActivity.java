package com.app.quetrip.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.DailyPlan;
import com.bumptech.glide.Glide;

public class SelectTransferRouteActivity extends BaseActivity {

    public EditText fromBox, toBox;
    ImageView pictureBox;
    TextView nameBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_transfer_route);

        Commons.selectTransferRouteActivity = this;

        pictureBox = (ImageView)findViewById(R.id.pictureBox);
        nameBox = (TextView)findViewById(R.id.nameBox);

        Glide.with(getApplicationContext()).load(Commons.transfer.getPicture_url()).into(pictureBox);
        nameBox.setText(Commons.transfer.getName());

        fromBox = (EditText)findViewById(R.id.fromBox);
        fromBox.setEnabled(false);
        fromBox.setTypeface(normal);
        toBox = (EditText)findViewById(R.id.toBox);
        toBox.setEnabled(false);
        toBox.setTypeface(normal);

    }

    public void pickFromLocation(View view){
        Intent intent = new Intent(getApplicationContext(), PickLocationActivity.class);
        intent.putExtra("option", "start");
        startActivity(intent);
    }

    public void pickToLocation(View view){
        Intent intent = new Intent(getApplicationContext(), PickLocationActivity.class);
        intent.putExtra("option", "end");
        startActivity(intent);
    }

    public void submitLocations(View view){

        if(fromBox.getText().length() == 0){
            showToast(getString(R.string.enter_start_location));
            return;
        }

        if(toBox.getText().length() == 0){
            showToast(getString(R.string.enter_end_location));
            return;
        }

        for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
            if(dailyPlan.getId() == Commons.dailyPlan.getId()){
                dailyPlan.getTransferArrayList().add(Commons.transfer);
                break;
            }
        }

        if(Commons.servicesActivity != null){
            Commons.servicesActivity.finish();
        }

        if(Commons.dailyScheduleActivity != null){
            Commons.dailyScheduleActivity.onResume();
        }

        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Commons.selectTransferRouteActivity = null;
    }
}




























package com.app.quetrip.main;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Marker;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

public class MapActivity extends BaseActivity {

    ImageView mapView;
    FrameLayout mapLayout;
    int www = 660;
    int hhh = 1048;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initTabs(1);

        mapView = (ImageView)findViewById(R.id.mapView);
        mapLayout = (FrameLayout)findViewById(R.id.mapLayout);

//        mapView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                float x = motionEvent.getX();
//                float y = motionEvent.getY();
//
//                markerView.setX(x);
//                markerView.setY(y);
//
//                Log.d("Touched at ", String.valueOf(x) + "///" + String.valueOf(y));
//                Log.d("Map size!", String.valueOf(mapView.getMeasuredWidth()) + "///" + String.valueOf(mapView.getMeasuredHeight()));
//
//                return false;
//            }
//        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initCityData();
            }
        }, 1000);

    }

    private void initTabs(int pos){

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.ic_home, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.newtrip, R.drawable.ic_browser, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.packages, R.drawable.ic_travel, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.account, R.drawable.ic_account, R.color.colorPrimary);

        bottomNavigation.setAccentColor(getColor(R.color.colorPrimary));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.setCurrentItem(pos);

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...

                if(position == 0){
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
                else if(position == 3){
                    Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

                return true;
            }
        });

    }

    private void initCityData(){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        ArrayList<Marker> markers = new ArrayList<>();
        markers.add(new Marker(0, 355.66406, 543.4375, getString(R.string.Andaraí), R.drawable.andarai));
        markers.add(new Marker(1, 401.01562, 1011.875, getString(R.string.Ibicoara), R.drawable.ibicoara));
        markers.add(new Marker(2, 374.1211, 630.625, getString(R.string.Igatu), R.drawable.igatu));
        markers.add(new Marker(3, 63.691406, 30.625, getString(R.string.Iraquara), R.drawable.iraquara));
        markers.add(new Marker(4, 659.0625, 719.0625, getString(R.string.Itaete), R.drawable.itaete));
        markers.add(new Marker(5, 315.58594, 330.9375, getString(R.string.Linen), R.drawable.lencois));
        markers.add(new Marker(6, 494.53125, 160.9375, getString(R.string.Hill_of_the_Hat), R.drawable.morro_do_chapeu));
        markers.add(new Marker(7, 308.02734, 695.625, getString(R.string.Mucuge), R.drawable.mucuge));
        markers.add(new Marker(8, 581.54297, 477.5, getString(R.string.New_Redemption), R.drawable.new_redemption));
        markers.add(new Marker(9, 133.82812, 276.5625, getString(R.string.Palm_trees), R.drawable.palmeiras));
        markers.add(new Marker(10, 5.6835938, 826.5625, getString(R.string.Piatan), R.drawable.piata));
        markers.add(new Marker(11, 68.45703, 1047.8125, getString(R.string.Rio_de_Contas), R.drawable.rio_de_contas));
        markers.add(new Marker(12, 205.89844, 417.1875, getString(R.string.Capão_Valley), R.drawable.vale_do_capao));

        for(Marker marker: markers){
            LayoutInflater inflater = getLayoutInflater();
            View markerView = inflater.inflate(R.layout.layout_marker, null);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(70 * width/www, 70 * width/www);
            layoutParams.leftMargin = (int) (mapView.getMeasuredWidth()*marker.getX()/www);
            layoutParams.topMargin = (int) (mapView.getMeasuredHeight()*marker.getY()/hhh);
            markerView.setLayoutParams(layoutParams);
            markerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Commons.marker = marker;
                    Intent intent = new Intent(getApplicationContext(), CitySummaryActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                }
            });
            View titleView = inflater.inflate(R.layout.layout_marker_title, null);
            TextView titleBox = (TextView)titleView.findViewById(R.id.title);
            titleBox.setText(marker.getTitle());
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if(marker.getId() == 8){
                layoutParams2.leftMargin = (int) (mapView.getMeasuredWidth()*(marker.getX() - 150)/www);
                layoutParams2.topMargin = (int) (mapView.getMeasuredHeight()*(marker.getY() + 23)/hhh);
            }else if(marker.getId() == 4){
                layoutParams2.leftMargin = (int) (mapView.getMeasuredWidth()*(marker.getX() - 70)/www);
                layoutParams2.topMargin = (int) (mapView.getMeasuredHeight()*(marker.getY() + 23)/hhh);
            }else if(marker.getId() == 6){
                layoutParams2.leftMargin = (int) (mapView.getMeasuredWidth()*(marker.getX() - 160)/www);
                layoutParams2.topMargin = (int) (mapView.getMeasuredHeight()*(marker.getY() + 23)/hhh);
            }else {
                layoutParams2.leftMargin = (int) (mapView.getMeasuredWidth()*(marker.getX() + 80)/www);
                layoutParams2.topMargin = (int) (mapView.getMeasuredHeight()*(marker.getY() + 23)/hhh);
            }
            titleView.setLayoutParams(layoutParams2);
            mapLayout.addView(markerView);
            mapLayout.addView(titleView);
        }

    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void showAlertDialogLegend(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_legend, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        TextView okButton = (TextView)view.findViewById(R.id.btn_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        // Get screen width and height in pixels
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;

        // Initialize a new window manager layout parameters
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        // Copy the alert dialog window attributes to new layout parameter instance
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        // Set alert dialog width equal to screen width 80%
        int dialogWindowWidth = (int) (displayWidth * 0.8f);
        // Set alert dialog height equal to screen height 80%
        //    int dialogWindowHeight = (int) (displayHeight * 0.8f);

        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        //      layoutParams.height = dialogWindowHeight;

        // Apply the newly created layout parameters to the alert dialog window
        dialog.getWindow().setAttributes(layoutParams);
    }

}

























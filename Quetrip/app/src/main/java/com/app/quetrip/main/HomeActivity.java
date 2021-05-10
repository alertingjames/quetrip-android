package com.app.quetrip.main;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.fragments.HomeAttractionsFrag;
import com.app.quetrip.fragments.HomeGuideFrag;
import com.app.quetrip.fragments.HomeHotelFrag;
import com.app.quetrip.fragments.HomeRestaurantFrag;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.appbar.AppBarLayout;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;
    SmartTabLayout tabLayout;
    ImageView background1, background2;
    Timer timer = null;
    Handler handler = new Handler();
    TimerTask timerTask = null;

    int[] backgrounds = new int[]{
            R.drawable.andarai,
            R.drawable.ibicoara,
            R.drawable.iraquara,
            R.drawable.itaete,
            R.drawable.morro_do_chapeu,
            R.drawable.igatu,
            R.drawable.lencois,
            R.drawable.mucuge,
            R.drawable.new_redemption,
            R.drawable.palmeiras,
            R.drawable.piata,
            R.drawable.rio_de_contas,
            R.drawable.vale_do_capao,
    };

    Animation fadeIn, fadeOut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        background1 = (ImageView)findViewById(R.id.background1);
        background2 = (ImageView)findViewById(R.id.background2);

        tabLayout = (SmartTabLayout) findViewById(R.id.viewpagertab);
        ViewPager viewPager  = (ViewPager) findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);

        setCustomFont();

        initTabs(0);
        startTimer();
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

                if(position == 1){
                    Commons.schedule = null;
                    Intent intent = new Intent(getApplicationContext(), NewScheduleActivity.class);
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


    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int i = 0; i < tabsCount; i++) {
            View tabViewChild = vg.getChildAt(i);
            if (tabViewChild instanceof TextView) {
                //Put your font in assests folder
                //assign name of the font here (Must be case sensitive)
                ((TextView) tabViewChild).setTypeface(bold);
//                ((TextView) tabViewChild).setTextSize(16);
            }
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                / mMaxScrollSize;

//        Log.d("Percentage+++", String.valueOf(currentScrollPercentage));

        if (currentScrollPercentage >= 10) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;

                ViewCompat.animate(findViewById(R.id.btn_get_started)).scaleY(0).scaleX(0).start();
                findViewById(R.id.btn_get_started).setVisibility(View.GONE);

                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view)).setVisibility(View.VISIBLE);
                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view))
                        .animate()
                        .alpha(1.0f)
                        .setDuration(500)
                        .start();
            }
        }else if (currentScrollPercentage <= 20) {
            if (mIsImageHidden) {
                mIsImageHidden = false;

                ViewCompat.animate(findViewById(R.id.btn_get_started)).scaleY(1).scaleX(1).start();
                findViewById(R.id.btn_get_started).setVisibility(View.VISIBLE);

                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view))
                        .animate()
                        .alpha(0.0f)
                        .setDuration(500)
                        .start();
                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view)).setVisibility(View.GONE);
            }
        }

    }

    private static class TabsAdapter extends FragmentPagerAdapter {

        static String[] titles = new String[]{
                Commons.tab.tabHash.get("attractions"),
                Commons.tab.tabHash.get("hotels"),
                Commons.tab.tabHash.get("restaurants"),
                Commons.tab.tabHash.get("travel packages"),
                Commons.tab.tabHash.get("guides")
        };

        private static final int TAB_COUNT = titles.length;

        TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return HomeAttractionsFrag.newInstance();
                case 1:
                    return HomeHotelFrag.newInstance();
                case 2:
                    return HomeRestaurantFrag.newInstance();
                case 4:
                    return HomeGuideFrag.newInstance();
                default:
                    return HomeHotelFrag.newInstance();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getMyLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void startNewTrip(View view){
        Intent intent = new Intent(getApplicationContext(), NewScheduleActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer.purge();
        }
    }

    int ii = 0;

    private void startTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run(){
                        //TODO
                        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_smooth);
                        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_smooth);
                        if(background1.getVisibility() == View.VISIBLE){
                            background2.setImageResource(backgrounds[ii]);
                            background2.setAnimation(fadeIn);
                            background2.setVisibility(View.VISIBLE);
                            background1.setVisibility(View.GONE);
                            background1.setAnimation(fadeOut);
                        }else {
                            background1.setImageResource(backgrounds[ii]);
                            background1.setAnimation(fadeIn);
                            background1.setVisibility(View.VISIBLE);
                            background2.setVisibility(View.GONE);
                            background2.setAnimation(fadeOut);
                        }

                        ii++;
                        if(ii > 12) ii = 0;
                    }
                });
            }
        };

        timer.schedule(timerTask, 500, 5000);
    }

    private static final int ACCESS_COARSE_LOCATION_PERMISSION_REQUEST = 7001;

    private void getMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    ACCESS_COARSE_LOCATION_PERMISSION_REQUEST);

        } else {
            LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                try {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());           ///////////////////////////////////////////////////////////////////////////////////////////////
                    Commons.thisUser.setLatLng(latLng);
                    Log.d("MyLoc!!!", String.valueOf(Commons.thisUser.getLatLng()));

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

































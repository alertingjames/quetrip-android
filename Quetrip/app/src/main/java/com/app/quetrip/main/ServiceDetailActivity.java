package com.app.quetrip.main;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.quetrip.Interfaces.HotelItemClickListener;
import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Service;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.bumptech.glide.Glide;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.material.appbar.AppBarLayout;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

public class ServiceDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;
    SmartTabLayout tabLayout;
    ImageView background;
    LinearLayout descLayout;
    TextView service_desc;
    Toolbar toolbar;
    private RecyclerView recyclerView;
    ArrayList<Service> services = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        background = (ImageView)findViewById(R.id.background);
        recyclerView = (RecyclerView)findViewById(R.id.recycleView) ;

        descLayout = (LinearLayout)findViewById(R.id.descLayout);

        tabLayout = (SmartTabLayout) findViewById(R.id.viewpagertab);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        Glide.with(this).load(Commons.service.getPicture_url()).into(background);
        service_desc = (TextView)findViewById(R.id.service_desc);

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        setCustomFont();

        initTabs(0);

        services.add(new Service(0, "hotel", "Grand Hyatt Rio de Janeiro", getString(R.string.hotel_desc), "https://media-cdn.tripadvisor.com/media/photo-w/16/77/ac/e3/pool.jpg", "en"));
        services.add(new Service(1, "hotel", "Cecomtur HomeHotelFrag", getString(R.string.hotel_desc), "https://media-cdn.tripadvisor.com/media/photo-o/0e/f3/1f/9e/cecomtur-hotel.jpg", "en"));
        services.add(new Service(2, "hotel", "Windsor California HomeHotelFrag", getString(R.string.hotel_desc), "https://media-cdn.tripadvisor.com/media/photo-w/12/64/39/62/apartamento-luxo-do-windsor.jpg", "en"));
        services.add(new Service(3, "hotel", "HomeHotelFrag Atlantico Rio", getString(R.string.hotel_desc), "https://media-cdn.tripadvisor.com/media/oyster/980/0d/6d/8b/a4/standard-triple--v14513581.jpg", "en"));
        services.add(new Service(4, "hotel", "Linx Galeao", getString(R.string.hotel_desc), "https://media-cdn.tripadvisor.com/media/photo-w/04/b6/49/f2/linx-hotel-international.jpg", "en"));

        initRecyclerView(services);
        ((NestedScrollView)findViewById(R.id.scrollView)).fullScroll(View.FOCUS_UP);
    }

    private void initTabs(int pos){

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.newtrip, R.drawable.ic_browser, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.packages, R.drawable.ic_travel, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.mytrips, R.drawable.ic_trip, R.color.colorPrimary);
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

                if(position == 3){
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
            }
        }
    }


    private void initRecyclerView(ArrayList<Service> items) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                / mMaxScrollSize;

        Log.d("Percentage+++", String.valueOf(currentScrollPercentage));

        if (currentScrollPercentage >= 10) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;

                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view)).setVisibility(View.VISIBLE);
                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view))
                        .animate()
                        .alpha(1.0f)
                        .setDuration(500)
                        .start();
            }
        }
        else if (currentScrollPercentage <= 20) {
            if (mIsImageHidden) {
                mIsImageHidden = false;

                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view))
                        .animate()
                        .alpha(0.0f)
                        .setDuration(500)
                        .start();
                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view)).setVisibility(View.GONE);
            }
        }

    }

    public void readMore(View view){
        service_desc.setLines(100);
        ((TextView)findViewById(R.id.read_more)).setVisibility(View.GONE);
    }

    private static class TabsAdapter extends FragmentPagerAdapter {

        static String[] titles = new String[]{
                Commons.tab.tabHash.get("hotels"),
                Commons.tab.tabHash.get("restaurants"),
                Commons.tab.tabHash.get("attractions"),
                Commons.tab.tabHash.get("car_rents"),
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
                    return null;
                case 1:
                    return null;
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }



}



























package com.app.quetrip.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.PictureListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.fragments.CarRentFrag;
import com.app.quetrip.fragments.DessertFrag;
import com.app.quetrip.fragments.DrinksFrag;
import com.app.quetrip.fragments.EquipmentFrag;
import com.app.quetrip.fragments.GuideFrag;
import com.app.quetrip.fragments.SaltyFrag;
import com.app.quetrip.fragments.SweetFrag;
import com.app.quetrip.fragments.TransferFrag;
import com.app.quetrip.models.CarRent;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.Equipment;
import com.app.quetrip.models.Guide;
import com.app.quetrip.models.Picture;
import com.app.quetrip.models.Transfer;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.material.appbar.AppBarLayout;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.viewpagerindicator.LinePageIndicator;
import java.util.ArrayList;

public class ServicesActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;
    SmartTabLayout tabLayout;

    ViewPager pager, servicesViewPager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Commons.servicesActivity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        pager = findViewById(R.id.viewPager0);

        tabLayout = (SmartTabLayout) findViewById(R.id.viewpagertab);
        servicesViewPager = (ViewPager) findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        servicesViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(servicesViewPager);

        servicesViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("Scrolled Page: ", String.valueOf(position));
            }

            @Override
            public void onPageSelected(int position) {
                // Here's your instance
                Log.d("Selected Page: ", String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setCustomFont();
        getServicePictures();

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

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
                Commons.tab.tabHash.get("guides"),
                Commons.tab.tabHash.get("rent_car"),
                Commons.tab.tabHash.get("equipments"),
                Commons.tab.tabHash.get("transfer"),
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
                    return GuideFrag.newInstance();
                case 1:
                    return CarRentFrag.newInstance();
                case 2:
                    return EquipmentFrag.newInstance();
                case 3:
                    return TransferFrag.newInstance();
                default:
                    return GuideFrag.newInstance();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }

    private void getServicePictures(){

        String[] pics = new String[]{
                "https://americanmoversinc.com/wp-content/uploads/2016/06/iStock_000013703464Medium.jpg",
                "http://www.idealtravelplan.com/wp-content/uploads/2018/01/car-rental-stock.jpg",
                "https://www.eqdepot.com/wp-content/uploads/2019/09/HOME_ServiceTraining-c2_v1.jpg",
                "https://portalestrada.com.br/wp-content/uploads/2019/09/786_RIVIAN.jpg",
                "https://bloximages.chicago2.vip.townnews.com/andovertownsman.com/content/tncms/assets/v3/editorial/3/6b/36bb515a-b183-5cf1-97e8-99b688e7913a/5c0de16aeeea8.image.jpg",
        };
        for(String pic:pics){
            Picture picture = new Picture();
            picture.setUrl(pic);
            pictures.add(picture);
        }
        adapter.setDatas(pictures);
        adapter.notifyDataSetChanged();
        pager.setAdapter(adapter);

        LinePageIndicator linePageIndicator = findViewById(R.id.indicator);
        linePageIndicator.setViewPager(pager);

        final float density = getResources().getDisplayMetrics().density;
        linePageIndicator.setSelectedColor(0x88FF0000);
        linePageIndicator.setUnselectedColor(0xFF888888);
        linePageIndicator.setStrokeWidth(4 * density);
        linePageIndicator.setLineWidth(30 * density);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Commons.servicesActivity = null;
    }

    public void addGuideToDailyPlan(Guide guide){
        for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
            if(dailyPlan.getId() == Commons.dailyPlan.getId()){
                dailyPlan.getGuideArrayList().add(guide);
                break;
            }
        }

        if(Commons.dailyScheduleActivity != null){
            Commons.dailyScheduleActivity.onResume();
        }

        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    public void addCarRentToDailyPlan(CarRent carRent){
        for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
            if(dailyPlan.getId() == Commons.dailyPlan.getId()){
                dailyPlan.getCarRentArrayList().add(carRent);
                break;
            }
        }

        if(Commons.dailyScheduleActivity != null){
            Commons.dailyScheduleActivity.onResume();
        }

        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    public void addEquipmentToDailyPlan(Equipment equipment){
        for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
            if(dailyPlan.getId() == Commons.dailyPlan.getId()){
                dailyPlan.getEquipmentArrayList().add(equipment);
                break;
            }
        }

        if(Commons.dailyScheduleActivity != null){
            Commons.dailyScheduleActivity.onResume();
        }

        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    public void addTransferToDailyPlan(Transfer transfer){
        Commons.transfer = transfer;
        Intent intent = new Intent(getApplicationContext(), SelectTransferRouteActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

}




































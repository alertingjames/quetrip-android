package com.app.quetrip.main;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdulhakeem.seemoretextview.SeeMoreTextView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.PictureListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.fragments.HomeAttractionsFrag;
import com.app.quetrip.fragments.HomeHotelFrag;
import com.app.quetrip.fragments.HomeRestaurantFrag;
import com.app.quetrip.models.Picture;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;

public class CitySummaryActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;
    SmartTabLayout tabLayout;

    TextView cityNameBox;
    SeeMoreTextView descriptionBox;
    FloatingActionButton selectButton;
    private View mFab;

    ViewPager pager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_summary);

        Commons.citySummaryActivity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        cityNameBox = (TextView)findViewById(R.id.cityNameBox);
        descriptionBox = (SeeMoreTextView)findViewById(R.id.descriptionBox);
        cityNameBox.setText(Commons.marker.getTitle());

        descriptionBox.setContent(getString(R.string.hotel_desc));
        if(descriptionBox.getText().length() > 250){
            descriptionBox.setTextMaxLength(250);
            descriptionBox.toggle();
            descriptionBox.expandText(false);
            descriptionBox.setSeeMoreTextColor(R.color.green);
            descriptionBox.setSeeMoreText(getString(R.string.read_more),getString(R.string.less));
        }

        mFab = findViewById(R.id.btn_sel);
        selectButton = (FloatingActionButton) findViewById(R.id.btn_sel);

        pager = findViewById(R.id.viewPager0);

        tabLayout = (SmartTabLayout) findViewById(R.id.viewpagertab);
        ViewPager viewPager  = (ViewPager) findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InCityScheduleActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        setCustomFont();
        getCityPictures();

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

        Log.d("Percentage+++", String.valueOf(currentScrollPercentage));

        if (currentScrollPercentage >= 10) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;

                ViewCompat.animate(mFab).scaleY(0).scaleX(0).start();
                mFab.setVisibility(View.GONE);

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

                ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
                mFab.setVisibility(View.VISIBLE);

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
                default:
                    return HomeRestaurantFrag.newInstance();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }

    private void getCityPictures(){

        String[] pics = new String[]{
                "http://www.aboutbrasil.com/modules/images/68.jpg",
                "http://muul.lt/wp-content/uploads/2017/01/brazil-city-wallpaper-3.jpg",
                "https://www.cruisemapper.com/images/ports/1966-1a38197374f.jpg",
                "http://www.aboutbrasil.com/modules/images/85.jpg",
                "https://media.tacdn.com/media/attractions-splice-spp-674x446/06/70/30/62.jpg?fit=crop&w=320&h=140"
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
        Commons.citySummaryActivity = null;
    }
}











































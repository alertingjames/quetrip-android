package com.app.quetrip.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.material.appbar.AppBarLayout;

public class AccountActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    FrameLayout progressBar;
    Toolbar toolbar;
    LinearLayout tabFrame;
    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;
    private int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        toolbar = (Toolbar) findViewById(R.id.flexible_example_toolbar);
        toolbar.setTitle(getString(R.string.manage_account));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.flexible_example_appbar);
        setTitle(getString(R.string.manage_account));

        progressBar = (FrameLayout) findViewById(R.id.progressBar);
        tabFrame = (LinearLayout)findViewById(R.id.tabFrame);

        ((TextView)findViewById(R.id.trips)).setTypeface(bold);
        ((TextView)findViewById(R.id.wishlist)).setTypeface(bold);

        ((TextView)findViewById(R.id.trips2)).setTypeface(bold);
        ((TextView)findViewById(R.id.wishlist2)).setTypeface(bold);

        ((TextView)findViewById(R.id.myProfile)).setTypeface(bold);
        ((TextView)findViewById(R.id.language)).setTypeface(bold);
        ((TextView)findViewById(R.id.help)).setTypeface(bold);
        ((TextView)findViewById(R.id.favorites)).setTypeface(bold);
        ((TextView)findViewById(R.id.feedback)).setTypeface(bold);
        ((TextView)findViewById(R.id.myStores)).setTypeface(bold);
        ((TextView)findViewById(R.id.aboutus)).setTypeface(bold);

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        initTabs(3);

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
                }else if(position == 1){
                    Commons.schedule = null;
                    Intent intent = new Intent(getApplicationContext(), NewScheduleActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

                return true;
            }
        });

    }

    public void toCart(View view){

    }

    public void toAbout(View view){

    }

    public void toMyTrips(View view){
        if(Commons.newScheduleActivity != null)Commons.newScheduleActivity = null;
        Intent intent = new Intent(getApplicationContext(), MyTripsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.fade_out);
    }

    public void toLanguage(View view){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.fade_out);
    }

    public void toWishlist(View view){

    }

    public void toFeedback(View view){

    }

    public void toFavorites(View view){

    }

    public void toProfile(View view){
        Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.bottom_in, R.anim.fade_out);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                / mMaxScrollSize;

        if (currentScrollPercentage >= 10) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;
                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view)).setVisibility(View.VISIBLE);
                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view))
                        .animate()
                        .alpha(1.0f)
                        .setDuration(500)
                        .start();
                tabFrame.animate()
                        .alpha(1.0f)
                        .setDuration(800)
                        .start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tabFrame.setVisibility(View.VISIBLE);
                    }
                }, 500);
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

                tabFrame.animate()
                        .alpha(0.0f)
                        .setDuration(200)
                        .start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tabFrame.setVisibility(View.GONE);
                    }
                }, 200);

            }
        }
    }
}































package com.app.quetrip.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abdulhakeem.seemoretextview.SeeMoreTextView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.PictureListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.fragments.DessertFrag;
import com.app.quetrip.fragments.DrinksFrag;
import com.app.quetrip.fragments.SaltyFrag;
import com.app.quetrip.fragments.SweetFrag;
import com.app.quetrip.models.Comment;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.Hours;
import com.app.quetrip.models.OpenTime;
import com.app.quetrip.models.Picture;
import com.app.quetrip.models.RestaurantMenu;
import com.bumptech.glide.Glide;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.viewpagerindicator.LinePageIndicator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class RestaurantDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;
    SmartTabLayout tabLayout;

    RatingBar ratingBar;
    TextView nameBox, cityNameBox, distanceBox, priceBox, cuisinesBox, mealsBox, statusBox, ratingsBox, reviewsBox;
    SeeMoreTextView descriptionBox;
    FloatingActionButton selectButton;
    private View mFab;

    ViewPager pager, menuViewPager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        Commons.restaurantDetailActivity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        nameBox = (TextView) findViewById(R.id.nameBox);
        cityNameBox = (TextView) findViewById(R.id.cityNameBox);
        distanceBox = (TextView) findViewById(R.id.distanceBox);
        priceBox = (TextView) findViewById(R.id.priceBox);
        cuisinesBox = (TextView) findViewById(R.id.cuisineBox);
        mealsBox = (TextView) findViewById(R.id.mealBox);
        statusBox = (TextView) findViewById(R.id.statusBox);
        ratingsBox = (TextView) findViewById(R.id.ratingsBox);
        reviewsBox = (TextView) findViewById(R.id.reviewsBox);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        descriptionBox = (SeeMoreTextView) findViewById(R.id.descriptionBox);

        nameBox.setText(Commons.restaurant.getName());
        cityNameBox.setText(Commons.restaurant.getCity());
        priceBox.setText(String.valueOf(Commons.restaurant.getMin_price()) + " - " + String.valueOf(Commons.restaurant.getMax_price()) + " " + Commons.restaurant.getUnit());
        statusBox.setText(Commons.restaurant.getStatus().equals("open") ? "Open Now" : "Closed Now");
        cuisinesBox.setText(Commons.restaurant.getCuisines());
        mealsBox.setText(Commons.restaurant.getMeals());
        ratingBar.setRating(Commons.restaurant.getRatings());
        ratingsBox.setText(String.valueOf(Commons.restaurant.getRatings()));
        reviewsBox.setText(String.valueOf(Commons.restaurant.getReviews()));

        descriptionBox.setContent(Commons.restaurant.getDescription());
        if (Commons.restaurant.getDescription().length() > 250) {
            descriptionBox.setTextMaxLength(250);
            descriptionBox.toggle();
            descriptionBox.expandText(false);
            descriptionBox.setSeeMoreTextColor(R.color.green);
            descriptionBox.setSeeMoreText(getString(R.string.read_more), getString(R.string.less));
        }

        mFab = findViewById(R.id.btn_select);
        selectButton = (FloatingActionButton) findViewById(R.id.btn_select);

        pager = findViewById(R.id.viewPager0);

        tabLayout = (SmartTabLayout) findViewById(R.id.viewpagertab);
        menuViewPager = (ViewPager) findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        menuViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(menuViewPager);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if(Commons.restaurant.getRestaurantMenuArrayList().size() == 0){
                    showToast(getString(R.string.add_menu));
                    return;
                }

                for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
                    if(dailyPlan.getId() == Commons.dailyPlan.getId()){
                        dailyPlan.getRestaurantArrayList().add(Commons.restaurant);
                        break;
                    }
                }

                if(Commons.restaurantListActivity != null){
                    Commons.restaurantListActivity.finish();
                    Commons.restaurantListActivity.overridePendingTransition(0,0);
                }

                if(Commons.dailyScheduleActivity != null){
                    Commons.dailyScheduleActivity.onResume();
                }

                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        menuViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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

        if(Commons.dailyScheduleActivity == null)selectButton.setVisibility(View.GONE);
        try{
            if(getIntent().getStringExtra("option").equals("new_menu")){
                selectButton.setVisibility(View.GONE);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        setCustomFont();
        getRestaurantPictures();

    }

    public void contact(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Commons.restaurant.getPhone_number()));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    public void viewLocation(View view){
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Commons.restaurant.getLatLng().latitude, Commons.restaurant.getLatLng().longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    public void viewHours(View view){
        ArrayList<Hours> hoursArrayList = new ArrayList<>();
        ArrayList<Integer> weekdays = new ArrayList<>();
        weekdays.add(1); weekdays.add(2); weekdays.add(3);
        ArrayList<OpenTime> openTimes = new ArrayList<>();
        OpenTime openTime = new OpenTime(1, 7, 0, 9, 0);
        openTimes.add(openTime);
        openTime = new OpenTime(1, 12, 0, 14, 0);
        openTimes.add(openTime);
        openTime = new OpenTime(1, 18, 30, 20, 30);
        openTimes.add(openTime);
        Hours hours = new Hours(1, Commons.restaurant.getId(), weekdays, openTimes, "");
        hoursArrayList.add(hours);

        weekdays.clear();
        openTimes.clear();
        weekdays.add(4); weekdays.add(5); weekdays.add(6);
        openTime = new OpenTime(1, 12, 0, 14, 0);
        openTimes.add(openTime);
        openTime = new OpenTime(1, 18, 30, 20, 30);
        openTimes.add(openTime);
        hours = new Hours(2, Commons.restaurant.getId(), weekdays, openTimes, "");
        hoursArrayList.add(hours);

        String[] weeks = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.layout_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialoglayout);
        builder.setTitle(getString(R.string.hours_));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
        for(Hours hours1:hoursArrayList){
            View layout = inflater.inflate(R.layout.layout_hours, null);
            TextView weekdaysView = (TextView)layout.findViewById(R.id.weekdaysBox);
            TextView hoursBox = (TextView)layout.findViewById(R.id.hoursBox);

            for(Integer weekday: hours1.getWeekdays()){
                String space = "";
                if(weekdaysView.getText().length() > 0){
                    space = ", ";
                }
                weekdaysView.setText(weekdaysView.getText().toString() + space + weeks[weekday]);
            }

            for(OpenTime openTime1: hours1.getOpenTimes()){
                String space = "";
                if(hoursBox.getText().length() > 0){
                    space = "\n";
                }
                hoursBox.setText(hoursBox.getText().toString() + space
                        + String.valueOf(openTime1.getS_hour()) + ":" + String.valueOf(openTime1.getS_minute())
                        + " - " + String.valueOf(openTime1.getE_hour()) + ":" + String.valueOf(openTime1.getE_minute()));
            }
            ((LinearLayout)dialoglayout.findViewById(R.id.container)).addView(layout);
        }

    }

    public void viewFeedback(View view){
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.layout_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialoglayout);
        builder.setTitle(getString(R.string.feedback));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
        for(int i=0;i<20; i++){
            Comment comment = new Comment(i+1, Commons.restaurant.getId(), "restaurant", 2, "Alianna Jane",
                    "https://m.media-amazon.com/images/M/MV5BYTZlM2QyMTItMGU0NS00MGQyLWI2M2MtYTFiYjc0NmUwOGQ1XkEyXkFqcGdeQXVyMTM0NjA4OTk@._V1_.jpg", 4.2f,
                    "", getString(R.string.restaurant_desc), 1576038216 * 000);

            View layout = inflater.inflate(R.layout.item_comment_list, null);
            TextView name = (TextView)layout.findViewById(R.id.name);
            TextView date = (TextView)layout.findViewById(R.id.date);
            TextView ratings = (TextView)layout.findViewById(R.id.ratings);
            TextView subject = (TextView)layout.findViewById(R.id.subject);
            TextView description = (TextView)layout.findViewById(R.id.description);
            CircleImageView picture = (CircleImageView) layout.findViewById(R.id.picture);
            RatingBar ratingBar = (RatingBar) layout.findViewById(R.id.ratingbar);

            name.setText(comment.getMember_name());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String myDate = dateFormat.format(new Date(comment.getDateTimestamp()));
            date.setText(myDate);
            ratings.setText(String.valueOf(comment.getRatings()));
            if(comment.getSubject().length() == 0)subject.setVisibility(View.GONE);
            else {
                subject.setVisibility(View.VISIBLE);
                subject.setText(comment.getSubject());
            }
            description.setText(comment.getDescription());
            Glide.with(getApplicationContext()).load(comment.getMember_picture()).into(picture);
            ratingBar.setRating(comment.getRatings());

            ((LinearLayout)dialoglayout.findViewById(R.id.container)).addView(layout);
        }
    }

    public void viewMore(View view){

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

                if(Commons.dailyScheduleActivity == null)mFab.setVisibility(View.GONE);
                else {
                    ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
                    mFab.setVisibility(View.VISIBLE);
                }

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
                Commons.tab.tabHash.get("salty_pizza"),
                Commons.tab.tabHash.get("sweet_pizza"),
                Commons.tab.tabHash.get("dessert"),
                Commons.tab.tabHash.get("drinks"),
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
                    return SaltyFrag.newInstance();
                case 1:
                    return SweetFrag.newInstance();
                case 2:
                    return DessertFrag.newInstance();
                case 3:
                    return DrinksFrag.newInstance();
                default:
                    return SaltyFrag.newInstance();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }

    private void getRestaurantPictures(){

        String[] pics = new String[]{
                "https://i.pinimg.com/originals/96/40/6e/96406ef045ea850859c6c0ded3efa9e6.jpg",
                "https://rccl-h.assetsadobe.com/is/image/content/dam/royal/data/dining/samba-grill/radiance-samba-grill-house-table-family-waitress-dining.JPG?$750x420$",
                "https://media-cdn.tripadvisor.com/media/photo-s/07/d5/93/8d/hyatt-ziva-rose-hall.jpg",
                "http://thumbor-prod-us-east-1.photo.aws.arc.pub/fAjFe3v8qHFW67-9ia6Hxml_FRw=/arc-anglerfish-arc2-prod-dmn/public/76T7RIYQIFWZQBUQXAXCEG4DQY.jpg",
                "https://barcelonawinebar.com/media/barcelona_westside_final_0046-1000x667.jpg"
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
        Commons.restaurantDetailActivity = null;
    }

    public void addMenuToSchedule(RestaurantMenu menu, EditText editText){

        if(editText.getText().length() == 0){
            showToast(getString(R.string.enter_quantity));
            return;
        }

        menu.setQuantity(Integer.parseInt(editText.getText().toString()));
        Commons.restaurant.getRestaurantMenuArrayList().add(menu);
        showToast(getString(R.string.menu_added));
    }

}






































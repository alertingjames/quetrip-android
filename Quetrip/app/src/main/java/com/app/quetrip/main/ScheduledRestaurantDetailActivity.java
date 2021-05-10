package com.app.quetrip.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.ImageView;
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
import com.app.quetrip.models.HotelRoom;
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
import java.util.concurrent.Callable;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScheduledRestaurantDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;

    RatingBar ratingBar;
    TextView nameBox, cityNameBox, distanceBox, priceBox, cuisinesBox, mealsBox, statusBox, ratingsBox, reviewsBox;
    SeeMoreTextView descriptionBox;
    FloatingActionButton checkoutButton;
    private View mFab;

    LinearLayout container;

    ViewPager pager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_restaurant_detail);

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

        container = (LinearLayout)findViewById(R.id.container);

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

        mFab = findViewById(R.id.btn_checkout);
        checkoutButton = (FloatingActionButton) findViewById(R.id.btn_checkout);

        pager = findViewById(R.id.viewPager0);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        if(Commons.dailyScheduleActivity == null)checkoutButton.setVisibility(View.GONE);
        toolbar.setTitle(getString(R.string.book_now));

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

                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view))
                        .animate()
                        .alpha(0.0f)
                        .setDuration(500)
                        .start();
                ((RealtimeBlurView)findViewById(R.id.real_time_blur_view)).setVisibility(View.GONE);
            }
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

    public void addNewMenu(View view){
        Intent intent = new Intent(getApplicationContext(), RestaurantDetailActivity.class);
        intent.putExtra("option", "new_menu");
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMenus();
    }

    private void getMenus(){
        container.removeAllViews();
        for(RestaurantMenu menu:Commons.restaurant.getRestaurantMenuArrayList()){
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.item_daily_restaurant_menu, null);
            TextView nameBox = (TextView) layout.findViewById(R.id.nameBox);
            TextView sizeBox = (TextView) layout.findViewById(R.id.sizeBox);
            TextView priceBox = (TextView) layout.findViewById(R.id.priceBox);
            TextView descriptionBox = (TextView) layout.findViewById(R.id.descriptionBox);
            ImageView pictureBox = (ImageView) layout.findViewById(R.id.pictureBox);
            EditText quantityBox = (EditText)layout.findViewById(R.id.quantityBox);
            TextView changeButton = (TextView)layout.findViewById(R.id.btn_change);
            TextView deleteButton = (TextView)layout.findViewById(R.id.btn_delete);
            TextView bookButton = (TextView)layout.findViewById(R.id.btn_book);

            nameBox.setText(menu.getName());
            if(menu.getSize().length() == 0)sizeBox.setVisibility(View.GONE);
            else {
                sizeBox.setVisibility(View.VISIBLE);
                String menuSizeStr = menu.getSize().substring(0,1).toUpperCase() + menu.getSize().substring(1, menu.getSize().length());
                sizeBox.setText(menuSizeStr);
            }
            priceBox.setText(String.valueOf(menu.getPrice()) + " " + menu.getUnit());
            quantityBox.setText(String.valueOf(menu.getQuantity()));
            descriptionBox.setText(menu.getDescription());
            if (menu.getPicture_url().length() > 0) {
                Glide.with(getApplicationContext()).load(menu.getPicture_url()).into(pictureBox);
            }

            changeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(quantityBox.getText().length() == 0){
                        showToast(getString(R.string.enter_quantity));
                        return;
                    }
                    menu.setQuantity(Integer.parseInt(quantityBox.getText().toString()));
                    showToast(getString(R.string.saved));
                }
            });

            bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Commons.restaurantMenu = menu;
                    Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialogForQuestion(getString(R.string.warning), getString(R.string.sure_delete), ScheduledRestaurantDetailActivity.this,
                            null, new Callable<Void>() {
                                @Override
                                public Void call() throws Exception {
                                    Commons.restaurant.getRestaurantMenuArrayList().remove(menu);
                                    onResume();
                                    return null;
                                }
                            });
                }
            });

            container.addView(layout);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}































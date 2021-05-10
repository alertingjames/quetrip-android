package com.app.quetrip.main;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abdulhakeem.seemoretextview.SeeMoreTextView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.PictureListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.HotelService;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.Dest;
import com.app.quetrip.models.Hotel;
import com.app.quetrip.models.HotelRoom;
import com.app.quetrip.models.Picture;
import com.bumptech.glide.Glide;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.makeramen.roundedimageview.RoundedImageView;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Callable;

public class HotelDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;

    TextView nameBox, addressBox, ratingsBox, reviewsBox;
    SeeMoreTextView descriptionBox;
    RatingBar ratingBar;
    FlexboxLayout flexboxLayout;

    FloatingActionButton selectButton;
    private View mFab;

    LinearLayout container;

    ViewPager pager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    ArrayList<HotelRoom> rooms = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        Commons.hotelDetailActivity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        nameBox = (TextView)findViewById(R.id.nameBox);
        descriptionBox = (SeeMoreTextView) findViewById(R.id.descriptionBox);

        ratingsBox = (TextView)findViewById(R.id.ratings);
        reviewsBox = (TextView)findViewById(R.id.reviews);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);

        ratingBar.setRating(Commons.hotel.getRatings());
        ratingsBox.setText(String.valueOf(Commons.hotel.getRatings()));
        reviewsBox.setText(String.valueOf(Commons.hotel.getReviews()));

        mFab = findViewById(R.id.btn_sel);
        selectButton = (FloatingActionButton) findViewById(R.id.btn_sel);

        addressBox = (TextView)findViewById(R.id.addressBox);
        addressBox.setText(Commons.hotel.getAddress());
        addressBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Dest dest = new Dest(Commons.hotel.getName(), Commons.hotel.getAddress(), Commons.hotel.getPicture_url(), Commons.hotel.getLatLng());
//                Commons.dest = dest;
//                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Commons.hotel.getLatLng().latitude, Commons.hotel.getLatLng().longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        container = (LinearLayout)findViewById(R.id.container);

        nameBox.setText(Commons.hotel.getName());
        descriptionBox.setContent(Commons.hotel.getDescription());
        if(Commons.hotel.getDescription().length() > 250){
            descriptionBox.setTextMaxLength(250);
            descriptionBox.toggle();
            descriptionBox.expandText(false);
            descriptionBox.setSeeMoreTextColor(R.color.green);
            descriptionBox.setSeeMoreText(getString(R.string.read_more),getString(R.string.less));
        }

        flexboxLayout = (FlexboxLayout) findViewById(R.id.servicesLayout);

        toolbar.setTitle(getString(R.string.choose_room));

        pager = findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Commons.hotel.getRoomArrayList().size() == 0){
                    showToast(getString(R.string.add_room));
                    return;
                }
                String text = getString(R.string.rooms_chosen) + " " + String.valueOf(Commons.hotel.getRoomArrayList().size());
                showAlertDialogForQuestion(getString(R.string.confirm), text, HotelDetailActivity.this, null, new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
                            if(dailyPlan.getId() == Commons.dailyPlan.getId()){
                                dailyPlan.getHotelArrayList().add(Commons.hotel);
                                break;
                            }
                        }
                        if(Commons.hotelListActivity != null){
                            Commons.hotelListActivity.finish();
                            Commons.hotelListActivity.overridePendingTransition(0,0);
                        }
                        if(Commons.dailyScheduleActivity != null){
                            Commons.dailyScheduleActivity.onResume();
                        }

                        finish();
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                        return null;
                    }
                });
            }
        });

        if(Commons.dailyScheduleActivity == null)selectButton.setVisibility(View.GONE);
        try{
            if(getIntent().getStringExtra("option").equals("new_room")){
                selectButton.setVisibility(View.GONE);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        getHotelPictures();
        getHotelServices();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getRooms();
            }
        }, 1000);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Commons.hotelDetailActivity = null;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(i)) * 100
                / mMaxScrollSize;

        Log.d("Percentage+++", String.valueOf(currentScrollPercentage));

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

    private void getHotelPictures(){

        String[] pics = new String[]{
                "https://thumbnails.trvl-media.com/gt_T80DyYthgnn6hRyUGm8yuckA=/773x530/smart/filters:quality(60)/images.trvl-media.com/hotels/3000000/2170000/2161600/2161563/dba6007c_z.jpg",
                "https://www.archicg.name/projects/BeachHouse/BeachHouse.jpg",
                "https://static.tripexpert.com/images/venue_photos/profile/18373.jpg?1518040668",
                "https://assets.wego.com/image/upload/v1524981065/Partner/hotels/266965/112974294.jpg",
                "https://www.uranrodrigues.com/wp-content/uploads/2016/02/DSC2971-536x357.jpg"
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

    private void getHotelServices(){
        flexboxLayout.removeAllViews();
        String[] items = new String[]{"pool", "wifi", "air", "parking", "pet", "breakfast"};
        HotelService hotelService = new HotelService();
        hotelService.initHotelServices(HotelDetailActivity.this);
        for(String item:items){
            View view = hotelService.service.get(item);
            flexboxLayout.addView(view);
        }
    }

    private void getRooms(){
        container.removeAllViews();
        for(int i=0;i<20;i++){
            HotelRoom room = new HotelRoom(i+1, "Standard Tween Room", "http://www.moscow-hotels.net/images/hotels/holiday-inn-moscow-lesnaya-hotel/accommodation/rooms/standard-twin-room.jpg",
                52, "USD", "City View", "2 Twin Beds", 2, 2, 1, "Free Cancellation", getString(R.string.hotel_desc2), 0, "", Commons.hotel);

            rooms.add(room);
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.item_hotel_room, null);
            TextView nameBox = (TextView)layout.findViewById(R.id.nameBox);
            TextView bedBox = (TextView)layout.findViewById(R.id.bedBox);
            TextView viewBox = (TextView)layout.findViewById(R.id.viewBox);
            TextView sleepsBox = (TextView)layout.findViewById(R.id.sleepsBox);
            TextView priceBox = (TextView)layout.findViewById(R.id.priceBox);
            TextView refundableBox = (TextView)layout.findViewById(R.id.refundableBox);
            ImageView pictureBox = (ImageView)layout.findViewById(R.id.pictureBox);
            LinearLayout viewLayout = (LinearLayout) layout.findViewById(R.id.viewLayout);

            nameBox.setText(room.getName());
            bedBox.setText(room.getBed());
            viewBox.setText(room.getViews());
            if(room.getViews().length() == 0)viewLayout.setVisibility(View.GONE);
            else viewLayout.setVisibility(View.VISIBLE);
            sleepsBox.setText(getString(R.string.sleeps) + " " + String.valueOf(room.getSleeps()));
            priceBox.setText(String.valueOf(room.getPrice()) + " " + room.getUnit());
            refundableBox.setText(room.getRefundable());
            Glide.with(getApplicationContext()).load(room.getPicture_url()).into(pictureBox);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Commons.room = room;
                    Intent intent = new Intent(getApplicationContext(), HotelRoomDetailActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            });

            container.addView(layout);
        }
    }

}

































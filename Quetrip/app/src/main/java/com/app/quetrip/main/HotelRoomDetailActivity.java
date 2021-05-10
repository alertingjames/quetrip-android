package com.app.quetrip.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abdulhakeem.seemoretextview.SeeMoreTextView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.PictureListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.ContentHeightListView;
import com.app.quetrip.classes.HotelService;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.Dest;
import com.app.quetrip.models.Picture;
import com.bumptech.glide.Glide;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;

public class HotelRoomDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;

    TextView priceBox, bedBox, viewBox, sleepsBox, adultsBox, childrenBox, refundableBox;
    SeeMoreTextView descriptionBox;
    FlexboxLayout flexboxLayout;
    LinearLayout viewLayout;
    FloatingActionButton selectButton;
    private View mFab;

    ViewPager pager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_room_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        priceBox = (TextView)findViewById(R.id.priceBox);
        descriptionBox = (SeeMoreTextView) findViewById(R.id.descriptionBox);

        viewLayout = (LinearLayout)findViewById(R.id.viewLayout);

        bedBox = (TextView)findViewById(R.id.bedBox);
        viewBox = (TextView)findViewById(R.id.viewBox);
        sleepsBox = (TextView) findViewById(R.id.sleepsBox);
        adultsBox = (TextView) findViewById(R.id.adultsBox);
        childrenBox = (TextView) findViewById(R.id.childrenBox);
        refundableBox = (TextView) findViewById(R.id.refundableBox);

        priceBox.setText(String.valueOf(Commons.room.getPrice()) + " " + Commons.room.getUnit());
        descriptionBox.setContent(Commons.room.getDescription());
        if(Commons.room.getDescription().length() > 250){
            descriptionBox.setTextMaxLength(250);
            descriptionBox.toggle();
            descriptionBox.expandText(false);
            descriptionBox.setSeeMoreTextColor(R.color.green);
            descriptionBox.setSeeMoreText(getString(R.string.read_more),getString(R.string.less));
        }

        bedBox.setText(Commons.room.getBed());
        viewBox.setText(Commons.room.getViews());
        if(Commons.room.getViews().length() == 0)viewLayout.setVisibility(View.GONE);
        else viewLayout.setVisibility(View.VISIBLE);
        sleepsBox.setText(getString(R.string.sleeps) + " " + String.valueOf(Commons.room.getSleeps()));
        refundableBox.setText(Commons.room.getRefundable());

        adultsBox.setText(getString(R.string.adults) + " " + String.valueOf(Commons.room.getAdults()));
        childrenBox.setText(getString(R.string.children) + " " + String.valueOf(Commons.room.getChildren()));

        flexboxLayout = (FlexboxLayout) findViewById(R.id.servicesLayout);

        toolbar.setTitle(Commons.room.getName());

        mFab = findViewById(R.id.btn_sel);
        selectButton = (FloatingActionButton) findViewById(R.id.btn_sel);

        pager = findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        getRoomPictures();
        getHotelServices();

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Commons.hotel.getRoomArrayList().contains(Commons.room)){
                    showToast(getString(R.string.existing_room));
                    return;
                }
                Commons.hotel.getRoomArrayList().add(Commons.room);
                showToast(getString(R.string.room_added));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        if(Commons.dailyScheduleActivity == null)selectButton.setVisibility(View.GONE);

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

    private void getRoomPictures(){

        String[] pics = new String[]{
                "http://www.moscow-hotels.net/images/hotels/holiday-inn-moscow-lesnaya-hotel/accommodation/rooms/standard-twin-room.jpg",
                "https://thumbnails.trvl-media.com/Lr5nNDj8o4vokSckk_Oyx6lmF9o=/773x530/smart/filters:quality(60)/images.trvl-media.com/hotels/3000000/2170000/2161600/2161563/ef197a3e_z.jpg",
                "https://cdn.superiorcruiseandtravel.com/uploads/2018/10/zank-by-toque-hotel-20.jpg",
                "https://cdn.superiorcruiseandtravel.com/uploads/2018/10/zank-by-toque-hotel-39.jpg",
                "https://cdn.ostrovok.ru/t/640x400/second/95/5e/955efc158ba460c8cc6081532ad6803613f38d61.jpeg"
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
        hotelService.initHotelServices(HotelRoomDetailActivity.this);
        for(String item:items){
            View view = hotelService.service.get(item);
            flexboxLayout.addView(view);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}























































































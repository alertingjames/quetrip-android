package com.app.quetrip.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.abdulhakeem.seemoretextview.SeeMoreTextView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.PictureListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.CarRentService;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.Picture;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.Locale;

public class EquipmentDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;

    TextView nameBox, priceBox, sizeBox, cityBox, addressBox;
    SeeMoreTextView descriptionBox;
    FloatingActionButton selectButton;
    private View mFab;

    ViewPager pager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        nameBox = (TextView)findViewById(R.id.nameBox);
        priceBox = (TextView)findViewById(R.id.priceBox);
        cityBox = (TextView)findViewById(R.id.cityNameBox);
        sizeBox = (TextView) findViewById(R.id.sizeBox);
        addressBox = (TextView) findViewById(R.id.addressBox);
        descriptionBox = (SeeMoreTextView) findViewById(R.id.descriptionBox);

        nameBox.setText(Commons.equipment.getName());
        priceBox.setText(String.valueOf(Commons.equipment.getPrice()) + " " + Commons.equipment.getUnit());
        descriptionBox.setContent(Commons.equipment.getDescription());
        if(Commons.equipment.getDescription().length() > 250){
            descriptionBox.setTextMaxLength(250);
            descriptionBox.toggle();
            descriptionBox.expandText(false);
            descriptionBox.setSeeMoreTextColor(R.color.green);
            descriptionBox.setSeeMoreText(getString(R.string.read_more),getString(R.string.less));
        }

        sizeBox.setText(Commons.equipment.getSize());
        addressBox.setText(Commons.equipment.getAddress());
        addressBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Dest dest = new Dest(Commons.equipment.getName(), Commons.equipment.getAddress(), Commons.equipment.getPicture_url(), Commons.equipment.getLatLng());
//                Commons.dest = dest;
//                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Commons.equipment.getLatLng().latitude, Commons.equipment.getLatLng().longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        toolbar.setTitle(Commons.equipment.getName());
        try{
            if(getIntent().getStringExtra("option").equals("booking")){
                toolbar.setTitle(getString(R.string.book_now));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        mFab = findViewById(R.id.btn_sel);
        selectButton = (FloatingActionButton) findViewById(R.id.btn_sel);

        pager = findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        getEquipmentPictures();

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(getIntent().getStringExtra("option").equals("booking")){
                        Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                        startActivity(intent);
                        return;
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                for(DailyPlan dailyPlan:Commons.schedule.getDailyPlans()){
                    if(dailyPlan.getId() == Commons.dailyPlan.getId()){
                        dailyPlan.getEquipmentArrayList().add(Commons.equipment);
                        break;
                    }
                }

                if(Commons.servicesActivity != null){
                    Commons.servicesActivity.finish();
                    Commons.servicesActivity.overridePendingTransition(0,0);
                }

                if(Commons.dailyScheduleActivity != null){
                    Commons.dailyScheduleActivity.onResume();
                }

                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

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

    private void getEquipmentPictures(){

        String[] pics = new String[]{
                "https://cdn.thewirecutter.com/wp-content/uploads/2017/10/fireextinguisher-2x1-fullres-4491-1024x512.jpg",
                "https://www.firedepartment.org/Home/ShowPublishedImage/598/636715219520630000",
                "https://survivallife.com/wp-content/uploads/2017/10/Using-a-fire-extinguisher-to-fight-fire-fire-extinguisher-getty.jpg",
                "http://nebula.wsimg.com/2f495df193ee751820c98be213c926d5?AccessKeyId=9118008A953501D6ECB8&disposition=0&alloworigin=1",
                "https://33kazu2ryz672xqt5m34kkn9-wpengine.netdna-ssl.com/wp-content/uploads/2019/05/fire-extinguisher-inspection-862x575.jpeg"
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
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}











































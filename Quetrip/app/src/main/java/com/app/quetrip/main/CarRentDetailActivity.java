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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abdulhakeem.seemoretextview.SeeMoreTextView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.PictureListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.CarRentService;
import com.app.quetrip.classes.HotelService;
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

public class CarRentDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;

    TextView priceBox, totalPriceBox, seatersBox, refundableBox, addressBox;
    SeeMoreTextView descriptionBox;
    FlexboxLayout flexboxLayout;
    FloatingActionButton selectButton;
    private View mFab;

    ViewPager pager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rent_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        priceBox = (TextView)findViewById(R.id.priceBox);
        totalPriceBox = (TextView)findViewById(R.id.totalPriceBox);
        seatersBox = (TextView) findViewById(R.id.seatersBox);
        addressBox = (TextView) findViewById(R.id.addressBox);
        descriptionBox = (SeeMoreTextView) findViewById(R.id.descriptionBox);
        refundableBox = (TextView) findViewById(R.id.refundableBox);

        priceBox.setText(String.valueOf(Commons.carRent.getWeekly_price()) + " " + Commons.carRent.getUnit());
        totalPriceBox.setText(String.valueOf(Commons.carRent.getTotal_price()) + " " + Commons.carRent.getUnit());
        descriptionBox.setContent(Commons.carRent.getDescription());
        if(Commons.carRent.getDescription().length() > 250){
            descriptionBox.setTextMaxLength(250);
            descriptionBox.toggle();
            descriptionBox.expandText(false);
            descriptionBox.setSeeMoreTextColor(R.color.green);
            descriptionBox.setSeeMoreText(getString(R.string.read_more),getString(R.string.less));
        }

        seatersBox.setText(String.valueOf(Commons.carRent.getSeaters()) + " " + getString(R.string.seaters) + (Commons.carRent.getColor().length() > 0?" | " + Commons.carRent.getColor():""));
        refundableBox.setText(Commons.carRent.getRefundable());
        addressBox.setText(Commons.carRent.getAddress());
        addressBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Dest dest = new Dest(Commons.carRent.getName(), Commons.carRent.getAddress(), Commons.carRent.getPicture_url(), Commons.carRent.getLatLng());
//                Commons.dest = dest;
//                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Commons.carRent.getLatLng().latitude, Commons.carRent.getLatLng().longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        flexboxLayout = (FlexboxLayout) findViewById(R.id.servicesLayout);

        toolbar.setTitle(Commons.carRent.getName());
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

        getCarPictures();
        getCarRentServices();

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
                        dailyPlan.getCarRentArrayList().add(Commons.carRent);
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

    private void getCarPictures(){

        String[] pics = new String[]{
                "https://resources.stuff.co.nz/content/dam/images/1/l/o/8/x/v/image.related.StuffLandscapeSixteenByNine.710x400.1lnw94.png/1505421818189.jpg",
                "https://img2.carmax.com/img/vehicles/18344866/1/750.jpg",
                "https://www.hyundaiusa.com/public_images/2019/ioniq-plug-in-hybrid/2019-hyundai-ioniq-plug-in-ext-14-ceramic-white.jpg",
                "https://www.conyersnissan.com/inventoryphotos/6434/5npdh4ae5gh788007/ip/2.jpg?height=400",
                "https://www.hyundaiusa.com/images/vehicles/pages/vlp/2019/elantra/vlp/hdri/limited/2019-elantra-ltd-quartz-white-pearl.jpg",
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

    private void getCarRentServices(){
        flexboxLayout.removeAllViews();
        String[] items = new String[]{"transmission", "mileage", "air"};
        CarRentService service = new CarRentService();
        service.initCarRentServices(CarRentDetailActivity.this);
        for(String item:items){
            View view = service.service.get(item);
            flexboxLayout.addView(view);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}














































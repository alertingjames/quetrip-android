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
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Picture;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.Locale;

public class ScheduledTransferDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;

    TextView nameBox, priceBox, minsBox, cityBox, addressBox, startBox, endBox;
    SeeMoreTextView descriptionBox;
    FloatingActionButton checkoutButton;
    private View mFab;

    ViewPager pager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_transfer_detail);

        Commons.scheduledTransferDetailActivity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        nameBox = (TextView)findViewById(R.id.nameBox);
        priceBox = (TextView)findViewById(R.id.priceBox);
        cityBox = (TextView)findViewById(R.id.cityNameBox);
        minsBox = (TextView) findViewById(R.id.minsBox);
        addressBox = (TextView) findViewById(R.id.addressBox);
        descriptionBox = (SeeMoreTextView) findViewById(R.id.descriptionBox);

        startBox = (TextView) findViewById(R.id.startBox);
        endBox = (TextView) findViewById(R.id.endBox);

        nameBox.setText(Commons.transfer.getName());
        priceBox.setText(String.valueOf(Commons.transfer.getKm_price()) + " " + Commons.transfer.getUnit());
        descriptionBox.setContent(Commons.transfer.getDescription());
        if(Commons.transfer.getDescription().length() > 250){
            descriptionBox.setTextMaxLength(250);
            descriptionBox.toggle();
            descriptionBox.expandText(false);
            descriptionBox.setSeeMoreTextColor(R.color.green);
            descriptionBox.setSeeMoreText(getString(R.string.read_more),getString(R.string.less));
        }

        minsBox.setText(String.valueOf(Commons.transfer.getKm_mins()));
        addressBox.setText(Commons.transfer.getAddress());
        addressBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Dest dest = new Dest(Commons.transfer.getName(), Commons.transfer.getAddress(), Commons.transfer.getPicture_url(), Commons.transfer.getLatLng());
//                Commons.dest = dest;
//                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Commons.transfer.getLatLng().latitude, Commons.transfer.getLatLng().longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        startBox.setText(Commons.transfer.getFrom_address());
        endBox.setText(Commons.transfer.getTo_address());

        startBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PickLocationActivity.class);
                intent.putExtra("option", "start");
                startActivity(intent);
            }
        });

        endBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PickLocationActivity.class);
                intent.putExtra("option", "end");
                startActivity(intent);
            }
        });

        toolbar.setTitle(getString(R.string.book_now));

        mFab = findViewById(R.id.btn_checkout);
        checkoutButton = (FloatingActionButton) findViewById(R.id.btn_checkout);

        pager = findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        getTransferPictures();

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
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

    private void getTransferPictures(){

        String[] pics = new String[]{
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/2016_Ford_Transit_350_2.2.jpg/1200px-2016_Ford_Transit_350_2.2.jpg",
                "https://www.harrogateminibushire.co.uk/wp-content/uploads/2019/12/hull-minibus.jpg",
                "https://www.asatravel.co.uk/wp-content/uploads/2015/05/Minibus-Hire.jpg",
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Commons.scheduledTransferDetailActivity = null;
    }
}



























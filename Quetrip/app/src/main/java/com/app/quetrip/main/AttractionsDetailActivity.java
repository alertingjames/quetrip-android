package com.app.quetrip.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abdulhakeem.seemoretextview.SeeMoreTextView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.CommentListAdapter;
import com.app.quetrip.adapters.PictureListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.ContentHeightListView;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.Comment;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.Dest;
import com.app.quetrip.models.Picture;
import com.bumptech.glide.Glide;
import com.facebook.common.Common;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.viewpagerindicator.LinePageIndicator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AttractionsDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;

    TextView nameBox, cityBox, extensionBox, departureBox, totalTimeBox, difficultyBox, typeBox, priceBox, addressBox, ratingsBox, reviewsBox;
    SeeMoreTextView descriptionBox;
    RatingBar ratingBar;
    ContentHeightListView listView;
    FloatingActionButton selectButton;
    private View mFab;
    Toolbar toolbar;

    ViewPager pager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    ArrayList<Comment> comments = new ArrayList<>();
    CommentListAdapter commentListAdapter = new CommentListAdapter(this);

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        nameBox = (TextView)findViewById(R.id.nameBox);
        cityBox = (TextView)findViewById(R.id.cityNameBox);
        extensionBox = (TextView)findViewById(R.id.extensionBox);
        departureBox = (TextView)findViewById(R.id.departureBox);
        totalTimeBox = (TextView)findViewById(R.id.totalTimeBox);
        difficultyBox = (TextView)findViewById(R.id.difficultyBox);
        typeBox = (TextView)findViewById(R.id.typeBox);
        priceBox = (TextView)findViewById(R.id.priceBox);
        descriptionBox = (SeeMoreTextView) findViewById(R.id.descriptionBox);

        ratingsBox = (TextView)findViewById(R.id.ratings);
        reviewsBox = (TextView)findViewById(R.id.reviews);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);

        ratingBar.setRating(Commons.attractions.getRatings());
        ratingsBox.setText(String.valueOf(Commons.attractions.getRatings()));
        reviewsBox.setText(String.valueOf(Commons.attractions.getReviews()));

        addressBox = (TextView)findViewById(R.id.addressBox);
        addressBox.setText(Commons.attractions.getAddress());
        addressBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Dest dest = new Dest(Commons.attractions.getName(), Commons.attractions.getAddress(), Commons.attractions.getPicture_url(), Commons.attractions.getLatLng());
//                Commons.dest = dest;
//                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Commons.attractions.getLatLng().latitude, Commons.attractions.getLatLng().longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        nameBox.setText(Commons.attractions.getName());
        cityBox.setText(Commons.attractions.getCity());
        extensionBox.setText(Commons.attractions.getExtension());
        departureBox.setText(Commons.attractions.getDeparture_time());
        totalTimeBox.setText((String.valueOf(Commons.attractions.getTotal_time()).endsWith(".0")?String.valueOf(Commons.attractions.getTotal_time()).replace(".0",""):
                String.valueOf(Commons.attractions.getTotal_time()))
                + " " + getString(R.string.hours));
        difficultyBox.setText(Commons.attractions.getDifficulty());
        typeBox.setText(Commons.attractions.getType());
        priceBox.setText(String.valueOf(Commons.attractions.getPrice()) + " " + Commons.attractions.getUnit());
        descriptionBox.setContent(Commons.attractions.getDescription());
        if(Commons.attractions.getDescription().length() > 250){
            descriptionBox.setTextMaxLength(250);
            descriptionBox.toggle();
            descriptionBox.expandText(false);
            descriptionBox.setSeeMoreTextColor(R.color.green);
            descriptionBox.setSeeMoreText(getString(R.string.read_more),getString(R.string.less));
        }

        listView = (ContentHeightListView) findViewById(R.id.list);

        mFab = findViewById(R.id.btn_sel);
        selectButton = (FloatingActionButton) findViewById(R.id.btn_sel);

        pager = findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

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
                        dailyPlan.getAttractionsArrayList().add(Commons.attractions);
                        break;
                    }
                }
                if(Commons.attractionsListActivity != null){
                    Commons.attractionsListActivity.finish();
                    Commons.attractionsListActivity.overridePendingTransition(0,0);
                }
                if(Commons.dailyScheduleActivity != null){
                    Commons.dailyScheduleActivity.onResume();
                }
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        if(Commons.dailyScheduleActivity == null)selectButton.setVisibility(View.GONE);
        try{
            if(getIntent().getStringExtra("option").equals("booking")){
                toolbar.setTitle(getString(R.string.book_now));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        getAttractionsPictures();
        getComments();

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
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(i)) * 100
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

                if(Commons.dailyScheduleActivity == null)mFab.setVisibility(View.GONE);
                else {
                    mFab.setVisibility(View.VISIBLE);
                    ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
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

    private void getAttractionsPictures(){

        String[] pics = new String[]{
                "https://dimg04.c-ctrip.com/images/100n14000000w5mxmBA1D_C_750_350.jpg?proc=source%2ftrip",
                "https://dimg04.c-ctrip.com/images/100f14000000w6lh070AD_C_760_506.jpg?proc=source%2ftrip",
                "http://greenfilmnet.org/wp-content/uploads/fica.jpg",
                "https://media-cdn.tripadvisor.com/media/photo-s/1a/0d/f2/64/20191116-115429-largejpg.jpg",
                "https://media-cdn.tripadvisor.com/media/photo-s/1a/0d/f2/62/20191116-120436-largejpg.jpg"
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

    private void getComments(){
        comments.clear();
        for(int i=0;i<5; i++){
            Comment comment = new Comment(i+1, Commons.attractions.getId(), "attractions", 2, "Alianna Jane",
                    "https://m.media-amazon.com/images/M/MV5BYTZlM2QyMTItMGU0NS00MGQyLWI2M2MtYTFiYjc0NmUwOGQ1XkEyXkFqcGdeQXVyMTM0NjA4OTk@._V1_.jpg", 4.2f,
                    "", getString(R.string.attractions_comment_desc), 1576038216 * 000);
            comments.add(comment);
        }
        commentListAdapter.setDatas(comments);
        listView.setAdapter(commentListAdapter);
    }

}




































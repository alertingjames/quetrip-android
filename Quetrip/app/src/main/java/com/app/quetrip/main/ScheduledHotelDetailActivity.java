package com.app.quetrip.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abdulhakeem.seemoretextview.SeeMoreTextView;
import com.app.quetrip.R;
import com.app.quetrip.adapters.PictureListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.classes.HotelService;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.models.DailyPlan;
import com.app.quetrip.models.HotelRoom;
import com.app.quetrip.models.Picture;
import com.bumptech.glide.Glide;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Callable;

public class ScheduledHotelDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener  {

    private int mMaxScrollSize;
    private boolean mIsImageHidden = false;

    TextView nameBox, addressBox, ratingsBox, reviewsBox;
    SeeMoreTextView descriptionBox;
    RatingBar ratingBar;
    FlexboxLayout flexboxLayout;
    FloatingActionButton checkoutButton;
    private View mFab;

    LinearLayout container;

    ViewPager pager;
    ArrayList<Picture> pictures = new ArrayList<>();
    PictureListAdapter adapter = new PictureListAdapter(this);

    ArrayList<HotelRoom> rooms = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_hotel_detail);

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
        checkoutButton = (FloatingActionButton) findViewById(R.id.btn_checkout);

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

        pager = findViewById(R.id.viewPager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

        toolbar.setTitle(getString(R.string.book_now));

        getHotelPictures();
        getHotelServices();

    }

    @Override
    public void onResume() {
        super.onResume();
        getRooms();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    private void getHotelPictures(){

        String[] pics = new String[]{
                "https://thumbnails.trvl-media.com/gt_T80DyYthgnn6hRyUGm8yuckA=/773x530/smart/filters:quality(60)/images.trvl-media.com/hotels/3000000/2170000/2161600/2161563/dba6007c_z.jpg",
                "https://www.archicg.name/projects/BeachHouse/BeachHouse.jpg",
                "https://toursgonewild.com/wp-content/gallery/zank-by-toque-salvador-hotel/Zank-by-Toque-Salvador-HomeHotelFrag-2.jpg",
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
        hotelService.initHotelServices(ScheduledHotelDetailActivity.this);
        for(String item:items){
            View view = hotelService.service.get(item);
            flexboxLayout.addView(view);
        }
    }

    public void addNewRoom(View view){
        Intent intent = new Intent(getApplicationContext(), HotelDetailActivity.class);
        intent.putExtra("option", "new_room");
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    private void getRooms(){
        container.removeAllViews();
        for(HotelRoom room:Commons.hotel.getRoomArrayList()){
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.item_daily_hotel_room, null);
            TextView nameBox = (TextView)layout.findViewById(R.id.nameBox);
            TextView bedBox = (TextView)layout.findViewById(R.id.bedBox);
            TextView viewBox = (TextView)layout.findViewById(R.id.viewBox);
            TextView sleepsBox = (TextView)layout.findViewById(R.id.sleepsBox);
            TextView priceBox = (TextView)layout.findViewById(R.id.priceBox);
            TextView refundableBox = (TextView)layout.findViewById(R.id.refundableBox);
            ImageView pictureBox = (ImageView)layout.findViewById(R.id.pictureBox);
            LinearLayout viewLayout = (LinearLayout) layout.findViewById(R.id.viewLayout);

            TextView deleteButton = (TextView)layout.findViewById(R.id.btn_delete);
            TextView bookButton = (TextView)layout.findViewById(R.id.btn_book);

            nameBox.setText(room.getName());
            bedBox.setText(room.getBed());
            viewBox.setText(room.getViews());
            if(room.getViews().length() == 0)viewLayout.setVisibility(View.GONE);
            else viewLayout.setVisibility(View.VISIBLE);
            sleepsBox.setText(getString(R.string.sleeps) + " " + String.valueOf(room.getSleeps()));
            priceBox.setText(String.valueOf(room.getPrice()) + " " + room.getUnit());
            refundableBox.setText(room.getRefundable());
            Glide.with(getApplicationContext()).load(room.getPicture_url()).into(pictureBox);

            bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Commons.room = room;
                    Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialogForQuestion(getString(R.string.warning), getString(R.string.sure_delete), ScheduledHotelDetailActivity.this,
                            null, new Callable<Void>() {
                                @Override
                                public Void call() throws Exception {
                                    int index = Commons.hotel.getRoomArrayList().indexOf(room);
                                    Commons.hotel.getRoomArrayList().remove(index);
                                    getRooms();
                                    return null;
                                }
                            });
                }
            });

            container.addView(layout);
        }
    }


}






























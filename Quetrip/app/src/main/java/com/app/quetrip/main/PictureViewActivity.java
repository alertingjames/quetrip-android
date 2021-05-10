package com.app.quetrip.main;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.app.quetrip.R;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PictureViewActivity  extends BaseActivity {

    ImageView downloader;
    private PhotoView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);

        image = (PhotoView) findViewById(R.id.image);
        try{
            String imageStr = getIntent().getStringExtra("image_url");
            if(imageStr.length() > 0)
                Picasso.with(getApplicationContext()).load(imageStr).into(image);
        }catch (NullPointerException e){
            e.printStackTrace();
            if(Commons.imageRes > 0)image.setImageResource(Commons.imageRes);
        }
    }

    public void back(View view){
        onBackPressed();
    }

}
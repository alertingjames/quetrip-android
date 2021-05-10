package com.app.quetrip.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.app.quetrip.R;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.main.PictureViewActivity;
import com.app.quetrip.models.Picture;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PictureListAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Picture> _datas = new ArrayList<>();
    private ArrayList<Picture> _alldatas = new ArrayList<>();

    public PictureListAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setDatas(ArrayList<Picture> datas) {

        _alldatas = datas;
        _datas.clear();
        _datas.addAll(_alldatas);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_image_list, collection, false);
        ImageView pagerImage = (ImageView) layout.findViewById(R.id.picrure);

        final Picture entity = (Picture) _datas.get(position);
        Log.d("IMAGE!!!", entity.getUrl());
        if(entity.getBitmap() != null){
            pagerImage.setImageBitmap(entity.getBitmap());
            ((ProgressBar) layout.findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
        }else if(entity.getImageRes() >0){
            pagerImage.setImageResource(entity.getImageRes());
            ((ProgressBar) layout.findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
        }else if(entity.getFile() != null){
            Picasso.with(context)
                    .load(entity.getFile())
                    .into(pagerImage);
            ((ProgressBar) layout.findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
        }else if(entity.getUrl().length() > 0){
            Picasso.with(context)
                    .load(entity.getUrl())
                    .into(pagerImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            ((ProgressBar) layout.findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError() {
                            ((ProgressBar) layout.findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
                        }
                    });
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PictureViewActivity.class);
                if(entity.getUrl().length() > 0)
                    intent.putExtra("image_url", entity.getUrl());
                else if(entity.getImageRes() >0) {
                    Commons.imageRes = entity.getImageRes();
                }
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, v, context.getString(R.string.transition));
                context.startActivity(intent, options.toBundle());
            }
        });

        collection.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return this._datas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
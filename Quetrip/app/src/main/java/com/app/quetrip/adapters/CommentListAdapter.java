package com.app.quetrip.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;

import com.app.quetrip.R;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.main.AttractionsDetailActivity;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.Comment;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentListAdapter extends BaseAdapter {
    private Context _context;
    private ArrayList<Comment> _datas = new ArrayList<>();
    private ArrayList<Comment> _alldatas = new ArrayList<>();
    public static DecimalFormat df = new DecimalFormat("0.00");

    public CommentListAdapter(Context context){

        super();
        this._context = context;
    }

    public void setDatas(ArrayList<Comment> datas) {

        _alldatas = datas;
        _datas.clear();
        _datas.addAll(_alldatas);
    }

    @Override
    public int getCount(){
        return _datas.size();
    }

    @Override
    public Object getItem(int position){
        return _datas.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        CustomHolder holder;

        if (convertView == null) {
            holder = new CustomHolder();

            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.item_comment_list, parent, false);

            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.date = (TextView)convertView.findViewById(R.id.date);
            holder.ratings = (TextView)convertView.findViewById(R.id.ratings);
            holder.subject = (TextView)convertView.findViewById(R.id.subject);
            holder.description = (TextView)convertView.findViewById(R.id.description);
            holder.picture = (CircleImageView) convertView.findViewById(R.id.picture);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingbar);

            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

        final Comment entity = (Comment) _datas.get(position);

        holder.name.setText(entity.getMember_name());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String myDate = dateFormat.format(new Date(entity.getDateTimestamp()));
        holder.date.setText(myDate);
        holder.ratings.setText(String.valueOf(entity.getRatings()));
        if(entity.getSubject().length() == 0)holder.subject.setVisibility(View.GONE);
        else {
            holder.subject.setVisibility(View.VISIBLE);
            holder.subject.setText(entity.getSubject());
        }
        holder.description.setText(entity.getDescription());
        Glide.with(_context).load(entity.getMember_picture()).into(holder.picture);
        holder.ratingBar.setRating(entity.getRatings());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    class CustomHolder {
        CircleImageView picture;
        RatingBar ratingBar;
        TextView name, date, ratings, subject, description;
    }
}














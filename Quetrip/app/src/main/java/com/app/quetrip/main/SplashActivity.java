package com.app.quetrip.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.app.quetrip.R;
import com.app.quetrip.adapters.RestaurantListAdapter;
import com.app.quetrip.base.BaseActivity;
import com.app.quetrip.commons.Commons;
import com.app.quetrip.commons.ReqConst;
import com.app.quetrip.models.Attractions;
import com.app.quetrip.models.User;
import com.google.android.gms.maps.model.LatLng;
import com.iamhabib.easy_preference.EasyPreference;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends BaseActivity {

    AVLoadingIndicatorView progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = (AVLoadingIndicatorView)findViewById(R.id.progressBar);
        Commons.tab.initTab(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String email = EasyPreference.with(getApplicationContext(), "user_info").getString("email", "");
                String password = EasyPreference.with(getApplicationContext(), "user_info").getString("password", "");
                if(email.length() > 0 && password.length() > 0){
                    login(email, password);
                }else {
                    Commons.thisUser = null;
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1500);


    }

    private void login(String email, String password){

        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post(ReqConst.SERVER_URL + "login")
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("RESPONSE!!!", response.toString());
                        progressBar.setVisibility(View.GONE);
                        try {
                            String result = response.getString("result_code");
                            if(result.equals("0")){
                                JSONObject object = response.getJSONObject("data");
                                User user = new User();
                                user.setIdx(object.getInt("id"));
                                user.setName(object.getString("name"));
                                user.setEmail(object.getString("email"));
                                user.setPassword(object.getString("password"));
                                user.setPicture_url(object.getString("picture_url"));
                                user.setRegistered_time(object.getString("registered_time"));
                                user.setPhone_number(object.getString("phone_number"));
                                user.setAddress(object.getString("address"));
                                double lat = 0.0d, lng = 0.0d;
                                if(object.getString("latitude").length() > 0){
                                    lat = Double.parseDouble(object.getString("latitude"));
                                    lng = Double.parseDouble(object.getString("longitude"));
                                }
                                user.setLatLng(new LatLng(lat, lng));
                                user.setAuth_status(object.getString("auth_status"));

                                Commons.thisUser = user;

                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);    // Homepage
                                startActivity(intent);
                                finish();
                            }else if(result.equals("1")){
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                showToast(getString(R.string.server_issue));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showToast(getString(R.string.server_issue));
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        progressBar.setVisibility(View.GONE);
                        showToast(getString(R.string.server_issue));
                    }
                });

    }

}


























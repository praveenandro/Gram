package com.admin.flubbes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

public class NumGet extends AppCompatActivity {
    Toolbar toolbar;
    EditText etNum;
    SharedPreferences sp;
    private AsLoginCheck mAsLoginCheck = null;
    private View mHome_progress;
    private String AddUserURL = "http://flubbes.com/Apis/fetch_userdetails.php";
    private Context conx;
    String TAG = "NumGet",jName,jPhone,jOwnRefCode,jWallet,jEmail,sPhone;

    // RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_get);
        etNum = (EditText) findViewById(R.id.eNum);
        conx = this;
        mHome_progress = findViewById(R.id.home_progress);
       /* rl=(RelativeLayout)findViewById(R.id.rel);
       // rl.setBackgroundResource(R.drawable.cabsmenu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           // rl.setBackground(getDrawable(R.drawable.cabsmenu));
            rl.setBackgroundResource(R.drawable.cabsmenu);
        }*/
        toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        sp = getSharedPreferences("Userphone", Context.MODE_PRIVATE);

        //  toolbar.setLogo(getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        }
// implement setNavigationOnClickListener event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// add code here that execute on click of navigation button
                Intent i = new Intent(NumGet.this, SignupActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    public void numget(View view) {

        if (etNum.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Number", Toast.LENGTH_SHORT).show();
        } else {

           // getDetails();
            Bundle b = new Bundle();
            b.putString("phnum", etNum.getText().toString());
            sPhone=etNum.getText().toString();
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("phone",etNum.getText().toString());
            editor.apply();
           Intent i = new Intent(NumGet.this, InfoGetActivity.class);
            i.putExtras(b);
            startActivity(i);

            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

    }

    private void getDetails() {
        Bundle b = new Bundle();
        b.putString("phnum", etNum.getText().toString());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("phone",etNum.getText().toString());
        editor.apply();
        mAsLoginCheck = new AsLoginCheck();
        mAsLoginCheck.execute();
    }

    private class AsLoginCheck extends AsyncTask<String, Void, String> {
        String name, user, pass;

        @Override
        protected void onPreExecute() {

            showProgress(true);
          /*  name = nam.getText().toString().trim();
            user = unam.getText().toString().trim();
            pass = pwd.getText().toString().trim();*/
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            String resp = "";
            try {

                // for GET Method
                String URL = "";

                URL = AddUserURL + "?phone=" + sPhone;

                URL = URL.replace(" ", "%20");
                HttpClient Client = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(URL);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                resp = Client.execute(httpget, responseHandler).trim();

            } catch (Exception e) {
                resp = e.toString();
            }

            // TODO: register the new account here.
            return resp;
        }

        @Override
        protected void onPostExecute(final String result) {

            try {
                if (result.startsWith("Error")) {
                    Toast.makeText(conx, result, Toast.LENGTH_LONG).show();
                } else {
                    boolean isJsArr = false;
                    try {
                        JSONArray jsArray1 = new JSONArray(result);
                        isJsArr = true;
                    } catch (Exception e) {
                        isJsArr = false;
                    }

                    if (isJsArr) {

                        Intent in = new Intent(NumGet.this, Home.class);
                        startActivity(in);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        JSONArray jsArray = new JSONArray(result);
                        JSONObject jsObj;
                        int len = jsArray.length();

                        for (int i = 0; i < len; i++) {
                            jsObj = jsArray.getJSONObject(i);
                            jName = jsObj.getString("user_name");
                            jPhone = jsObj.getString("phone");
                            jOwnRefCode = jsObj.getString("ownref_code");
                            jWallet = jsObj.getString("wallet");
                            jEmail = jsObj.getString("email");
                            Log.e(TAG, "name: " + jName);
                            Log.e(TAG, "phone: " + jPhone);
                            Log.e(TAG, "ownrefcode: " + jOwnRefCode);
                            Log.e(TAG, "wallet: " + jWallet);
                            Log.e(TAG, "email: " + jEmail);
                        }

                    } else {

                        Toast.makeText(conx, result, Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error is " + e.toString());
            }

            showProgress(false);

        }

        @Override
        protected void onCancelled() {
            mAsLoginCheck = null;
            showProgress(false);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mHome_progress.setVisibility(show ? View.VISIBLE : View.GONE);
            mHome_progress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mHome_progress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {

            mHome_progress.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}

package com.admin.flubbes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class Refer extends AppCompatActivity {
    private Refer.AddRefertask mAddRefertask = null;
    private View mHome_progress;
    private Context conx;
    String num;
    TextView tNum;
    SharedPreferences spnum;

    String TAG = "Refer",finalphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);
        tNum=(TextView)findViewById(R.id.idrefercode);
        conx = this;
        mHome_progress = findViewById(R.id.home_progress);
        spnum = getSharedPreferences("Userphone", MODE_PRIVATE);
        getDetails();
    }



    public void getDetails() {
        mAddRefertask = new AddRefertask();
        mAddRefertask.execute();
    }

    public void refer(View view) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "I have a Flubbess coupon worth Rs.50 for you.Sign up with my code "+tNum.getText().toString()+ " to avail the coupon and ride for free! Download: //play.google.com/store/apps/details?id=com.admin.flubbes");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private class AddRefertask extends AsyncTask<String, Void, String> {
        String username, phone, email;

        @Override
        protected void onPreExecute() {

            showProgress(true);

            // Toast.makeText(getApplicationContext(), erefcode.getText().toString(), Toast.LENGTH_SHORT).show();


            phone = spnum.getString("phone","");
//tNum.setText(phone);
         //   Toast.makeText(getApplicationContext(), phone, Toast.LENGTH_SHORT).show();
            //pass = pwd.getText().toString().trim();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            String resp = "";
            try {

                // for GET Method
                // Toast.makeText(getApplicationContext(), erefcode.getText().toString(), Toast.LENGTH_SHORT).show();
                String URL = "";

                String addUserURL = "http://flubbes.com/Apis/refercode_user.php";
                URL = addUserURL + "?phone=" + phone;

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

            tNum.setText(result);



            showProgress(false);

        }

        @Override
        protected void onCancelled() {
            mAddRefertask = null;
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

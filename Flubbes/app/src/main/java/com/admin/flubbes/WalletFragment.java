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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.Objects;

public class WalletFragment extends AppCompatActivity {
    private WalletFragment.AddWallettask mAddWallettask = null;
    private View mHome_progress;
    private Context conx;
    String num;
    Toolbar toolbar;
    TextView tWaletbalance;
    SharedPreferences spnum;

    String TAG = "WalletFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_fragment);
        tWaletbalance = (TextView) findViewById(R.id.idWalletbalance);
        conx = this;
        mHome_progress = findViewById(R.id.home_progress);

        //--
        toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        toolbar.setTitle("PAYMENTS");


        //  toolbar.setLogo(getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        }
// implement setNavigationOnClickListener event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// add code here that execute on click of navigation button
                Intent i = new Intent(WalletFragment.this, Home.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        //--
        spnum = getSharedPreferences("Userphone", MODE_PRIVATE);
        getDetails();
    }

    private void getDetails() {
        mAddWallettask = new AddWallettask();
        mAddWallettask.execute();
    }

    private class AddWallettask extends AsyncTask<String, Void, String> {
        String username, phone, email;

        @Override
        protected void onPreExecute() {

            showProgress(true);

            // Toast.makeText(getApplicationContext(), erefcode.getText().toString(), Toast.LENGTH_SHORT).show();


            phone = spnum.getString("phone", "");
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

                String addUserURL = "http://flubbes.com/Apis/wallet_user.php";
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
        protected void onPostExecute(String result) {
           // Toast.makeText(conx, String.valueOf(result.length()), Toast.LENGTH_SHORT).show();

            if ((String.valueOf(result.length()).equals("0"))) {
                tWaletbalance.setText("Wallet Balance: Rs.0");

            } else {
                tWaletbalance.setText("Wallet Balance: Rs." + result);
            }


            showProgress(false);

        }

        @Override
        protected void onCancelled() {
            mAddWallettask = null;
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

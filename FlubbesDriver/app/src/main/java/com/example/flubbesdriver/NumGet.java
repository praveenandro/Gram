package com.example.flubbesdriver;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.Objects;

public class NumGet extends AppCompatActivity {
    private NumGet.AddLogintask mAddLogintask = null;
    private View mHome_progress;
    private Context conx;
    Toolbar toolbar;
    EditText eNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_get);
        eNum = (EditText) findViewById(R.id.eNum);

        conx = this;
        mHome_progress = findViewById(R.id.home_progress);


    }

    private void getDetails() {


        mAddLogintask = new AddLogintask();
        mAddLogintask.execute();


    }

    public void numget(View view) {
        if (eNum.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Number", Toast.LENGTH_SHORT).show();
        } else {
            getDetails();
        }

    }

    private class AddLogintask extends AsyncTask<String, Void, String> {
        String username, phone, email;

        @Override
        protected void onPreExecute() {

            showProgress(true);


            phone = eNum.getText().toString();
           // Toast.makeText(getApplicationContext(),phone,Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            String resp = "";
            try {


                String URL = "";

                String addUserURL = "http://flubbes.com/Apis/loginfetch_driver.php";
                Log.d("Praveennn",phone);
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


            if (result.startsWith("User Already Added")) {
                SharedPreferences sp = getSharedPreferences("Userphone", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("phone", phone);
                editor.apply();
                Intent i = new Intent(NumGet.this, MapsActivity.class);
                startActivity(i);
            } else {
               Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();
            }

            showProgress(false);

        }

        @Override
        protected void onCancelled() {
            mAddLogintask = null;
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

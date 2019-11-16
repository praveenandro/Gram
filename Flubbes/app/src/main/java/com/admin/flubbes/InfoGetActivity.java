package com.admin.flubbes;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

public class InfoGetActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tPhnum;
    EditText ename, eemail, erefcode;
    Button reg;
    private AddCustomersTask mAddCustomersTask = null;
    private View mHome_progress;
    private String AddUserURL = "http://flubbes.com/Apis/registration_user.php";
    private Context conx;
    SharedPreferences spnum;
    String num,rand,ref_code;
    String TAG = "InfoGetActivity";
    //StringBuilder randomStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_get);
        conx = this;
        mHome_progress = findViewById(R.id.home_progress);

        tPhnum = (TextView) findViewById(R.id.idPhnum);
        ename = (EditText) findViewById(R.id.idEdName);
        eemail = (EditText) findViewById(R.id.idEdEmail);
        erefcode = (EditText) findViewById(R.id.idEdRefCode);
        reg = (Button) findViewById(R.id.regbut);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String name = ename.getText().toString();
                String email = eemail.getText().toString();
                SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("a", name);
                ed.putString("b", email);
                ed.commit();*/
                register(v);

            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar);

        //  toolbar.setLogo(getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        }
// implement setNavigationOnClickListener event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// add code here that execute on click of navigation button
                Intent i = new Intent(InfoGetActivity.this, NumGet.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

      /*  Intent i = getIntent();
        Bundle b = i.getExtras();
        num = (b.getString("phnum"));*/

         spnum = getSharedPreferences("Userphone", MODE_PRIVATE);
        //SharedPreferences.Editor ede = spnum.edit();
        tPhnum.setText("+91" + spnum.getString("phone", num));
       // ede.putString("phone", num);

       // ede.apply();
    }

    public void register(View view) {

        if (ename.getText().toString().isEmpty() && eemail.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Details Properly", Toast.LENGTH_SHORT).show();
        } else {

            //Toast.makeText(getApplicationContext(), erefcode.getText().toString(), Toast.LENGTH_SHORT).show();
           adddetails();
           

        }

    }

    private void adddetails() {

        mAddCustomersTask = new AddCustomersTask();
        mAddCustomersTask.execute();
    }

    private class AddCustomersTask extends AsyncTask<String, Void, String> {
        String username, phone, email;

        @Override
        protected void onPreExecute() {

            showProgress(true);
            random();
           // Toast.makeText(getApplicationContext(), erefcode.getText().toString(), Toast.LENGTH_SHORT).show();
            username = ename.getText().toString().trim();
            email = eemail.getText().toString().trim();
            phone = spnum.getString("phone", num);
            if(erefcode.getText().toString().isEmpty()){
                ref_code = "";
            }else{
                ref_code = erefcode.getText().toString();
            }

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

                URL = AddUserURL + "?phone=" + phone + "&user=" + username + "&email=" + email + "&ref_code=" + ref_code+ "&Ownref_code=" + rand.toUpperCase() + "&wallet=" + "" ;

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
                       // Toast.makeText(conx, "executed", Toast.LENGTH_SHORT).show();

                        Intent in = new Intent(InfoGetActivity.this, PermissionsActivity.class);
                        startActivity(in);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        JSONArray jsArray = new JSONArray(result);
                        JSONObject jsObj;
                        int len = jsArray.length();

                        for (int i = 0; i < len; i++) {
                            jsObj = jsArray.getJSONObject(i);

                            String username = jsObj.getString("user_name");
                            String phone = jsObj.getString("phone");
                            String email = jsObj.getString("email");
                            String ref_code = jsObj.getString("ref_code");

                            Log.e(TAG, "username: " + username);
                            Log.e(TAG, "phone: " + phone);
                            Log.e(TAG, "email: " + email);
                            Log.e(TAG, "ref_code: " + ref_code);
                        }

                    } else {
                        Toast.makeText(conx, result, Toast.LENGTH_LONG).show();
                       /* Intent in = new Intent(InfoGetActivity.this, Home.class);
                        startActivity(in);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);*/
                    }


                }
            } catch (Exception e) {
                Log.e(TAG, "Error is " + e.toString());
            }

            showProgress(false);

        }

        @Override
        protected void onCancelled() {
            mAddCustomersTask = null;
            showProgress(false);
        }
        void random() {
            final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
            final Random random=new Random();
            final StringBuilder sb=new StringBuilder(4);
            for(int i=0;i<4;++i) {
                sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
            }
            // return sb.toString();
            rand = ename.getText().toString().substring(0,3)+sb.toString();
            // randomStringBuilder;
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

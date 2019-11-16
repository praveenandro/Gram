package com.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {

    Button reg,otp;
    EditText nam,unam,pwd,num,msg;
    private AddCustomersTask mAddCustomersTask = null;
    private SendOTPTask mSendOTPTask = null;
    private View mHome_progress;
    private String AddUserURL = "http://flubbes.com/Apis/newcustomer_bk.php";
    private Context conx;
    String TAG = "MainActivty";
    private String OPT_URL = "http://bhashsms.com/api/sendmsg.php?user=";
    private String User ="uniq";
    private String passwd = "123456";
    private String sid = "UNIQTE";
    private String mtype = "normal";
    private String DR = "Y";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conx = this;
        mHome_progress = findViewById(R.id.home_progress);
        nam = findViewById(R.id.nam);
        unam = findViewById(R.id.unam);
        num = findViewById(R.id.num);
        msg = findViewById(R.id.msg);
        pwd = findViewById(R.id.pwd);
        reg = findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddCustomersTask = new AddCustomersTask();
                mAddCustomersTask.execute();
            }
        });
        otp = findViewById(R.id.otp);
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSendOTPTask = new SendOTPTask();
                mSendOTPTask.execute();
            }
        });
    }

    public class AddCustomersTask extends AsyncTask<String, Void, String> {
        String name,user,pass;

        @Override
        protected void onPreExecute() {

            showProgress(true);
            name = nam.getText().toString().trim();
            user = unam.getText().toString().trim();
            pass = pwd.getText().toString().trim();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            String resp="";
            try {

                // for GET Method
                String URL = "";

                URL =AddUserURL + "?name=" + name+"&user="+user+"&pass="+pass;

                URL = URL.replace(" ","%20");
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

            try{
                if(result.startsWith("Error")){
                    Toast.makeText(conx,result,Toast.LENGTH_LONG).show();
                }else {
                    boolean isJsArr = false;
                    try{
                        JSONArray jsArray1 = new JSONArray(result);
                        isJsArr = true;
                    }catch (Exception e){
                        isJsArr = false;
                    }

                    if(isJsArr) {
                        JSONArray jsArray = new JSONArray(result);
                        JSONObject jsObj;
                        int len = jsArray.length();

                        for (int i = 0; i < len; i++) {
                            jsObj = jsArray.getJSONObject(i);
                            String name = jsObj.getString("name");
                            String username = jsObj.getString("username");
                            String password = jsObj.getString("password");
                            Log.e(TAG,"name: "+name);
                            Log.e(TAG,"username: "+username);
                            Log.e(TAG,"password: "+password);
                        }

                    }else{
                        Toast.makeText(conx, result, Toast.LENGTH_LONG).show();
                    }
                }
            }catch (Exception e){
                Log.e(TAG,"Error is "+e.toString());
            }

            showProgress(false);

        }

        @Override
        protected void onCancelled() {
            mAddCustomersTask = null;
            showProgress(false);
        }
    }

    public class SendOTPTask extends AsyncTask<String, Void, String> {
        String number,msgs;

        @Override
        protected void onPreExecute() {

            showProgress(true);
            number = num.getText().toString().trim();
            msgs = msg.getText().toString().trim();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            String resp="";
            try {

                // for GET Method
                String URL = "";
//"http://bhashsms.com/api/sendmsg.php?user=" + User + "&pass=" + passwd + "&phone=" + mobilenumber + "&sender=" + sid + "&priority=ndnd&stype=normal&text="+message
                URL =OPT_URL + User + "&pass=" + passwd + "&phone=" + number + "&sender=" + sid + "&priority=ndnd&stype=normal&text="+msgs;

                URL = URL.replace(" ","%20");
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

            try{
                if(result.startsWith("Error")){
                    Toast.makeText(conx,result,Toast.LENGTH_LONG).show();
                }else {
                    boolean isJsArr = false;
                    try{
                        JSONArray jsArray1 = new JSONArray(result);
                        isJsArr = true;
                    }catch (Exception e){
                        isJsArr = false;
                    }

                    if(isJsArr) {
                        JSONArray jsArray = new JSONArray(result);
                        JSONObject jsObj;
                        int len = jsArray.length();

                        for (int i = 0; i < len; i++) {
                            jsObj = jsArray.getJSONObject(i);
                            String name = jsObj.getString("name");
                            String username = jsObj.getString("username");
                            String password = jsObj.getString("password");
                            Log.e(TAG,"name: "+name);
                            Log.e(TAG,"username: "+username);
                            Log.e(TAG,"password: "+password);
                        }

                    }else{
                        Toast.makeText(conx, result, Toast.LENGTH_LONG).show();
                    }
                }
            }catch (Exception e){
                Log.e(TAG,"Error is "+e.toString());
            }

            showProgress(false);

        }

        @Override
        protected void onCancelled() {
            mAddCustomersTask = null;
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

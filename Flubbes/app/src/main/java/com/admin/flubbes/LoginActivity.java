package com.admin.flubbes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    ImageView im;
    private LoginActivity.AddNotificationtask mAddNotificationtask = null;
   // private View mHome_progress;
    private Context conx;
    Dialog dialog;
    String TAG = "Notification", jMessage, jImgURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        im = (ImageView) findViewById(R.id.imageView);
        conx = this;
       // mHome_progress = findViewById(R.id.home_progress);

        getNotification();
    }

    public void getNotification() {

        mAddNotificationtask = new AddNotificationtask();
        mAddNotificationtask.execute();
    //   loadIMG();
    }
    private void loadIMG() {
        dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.notification_model);
        dialog.setCancelable(false);

        TextView msg = (TextView) dialog.findViewById(R.id.idMsg);
        Button submit = (Button) dialog.findViewById(R.id.idClose);
        ImageView img = (ImageView) dialog.findViewById(R.id.idImg);

        //msg.setText(jMessage);
        msg.setText("Hey Customer Enjoy Your RIDES WITH Flubbes!.... Earn Money through your reference... Have a Fabulous Journey");

        //Picasso.get().load("http://flubbes.com/Apis/images/" + jImgURL).into(img);
        Picasso.with(conx).load("http://flubbes.com/Apis/images/refer.png").into(img);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void login(View view) {
        Intent i = new Intent(LoginActivity.this, LoginNumGet.class);
        startActivity(i);
    }

    public void register(View view) {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }

    private class AddNotificationtask extends AsyncTask<String, Void, String> {
        String username, phone, email;

        @Override
        protected void onPreExecute() {

           // showProgress(true);


        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            String resp = "";
            try {

                // for GET Method
                // Toast.makeText(getApplicationContext(), erefcode.getText().toString(), Toast.LENGTH_SHORT).show();
                String URL = "";

                String addUserURL = "http://flubbes.com/Apis/newnotification.php";
              ////  URL = addUserURL + "phone=" + "1";

                URL = URL.replace(" ", "%20");
                HttpClient Client = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(addUserURL);
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
                    Toast.makeText(conx, "err: "+result, Toast.LENGTH_LONG).show();
                } else {
                    boolean isJsArr = false;
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        isJsArr = true;
                    } catch (Exception e) {
                        isJsArr = false;
                    }

                    if (!isJsArr) {

                        JSONArray jsArray = new JSONArray(result);
                        JSONObject jsObj;
                        int len = jsArray.length();

                        for (int i = 0; i < len; i++) {
                            jsObj = jsArray.getJSONObject(i);
                            jMessage = jsObj.getString("message");

                              jImgURL = jsObj.getString("image");

                            Log.e(TAG, "Message: " + jMessage);
                           Log.e(TAG, "Image Url: " + jImgURL);

                        }

                        loadIMG();

                    } else {

                        Toast.makeText(conx, result, Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Error is " + result);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Error is " + e.toString());
            }


          //  showProgress(false);

        }

        private void loadIMG() {
            dialog = new Dialog(LoginActivity.this);
            dialog.setContentView(R.layout.notification_model);
            dialog.setCancelable(false);

            TextView msg = (TextView) dialog.findViewById(R.id.idMsg);
            Button submit = (Button) dialog.findViewById(R.id.idClose);
            ImageView img = (ImageView) dialog.findViewById(R.id.idImg);

            msg.setText(jMessage);

           Picasso.with(conx).load(jImgURL).into(img);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        @Override
        protected void onCancelled() {
            mAddNotificationtask = null;
           // showProgress(false);
        }

    }

  /*  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
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
    }*/
}

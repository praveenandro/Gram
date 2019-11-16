package com.admin.flubbes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class PermissionsActivity extends AppCompatActivity {
Context context;
Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
context=PermissionsActivity.this;
        final Activity activity = this;


    }

    public void permission(View view) {
        int Permission_All = 1;
        String[] Permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE};

        if (!hasPermissions(this, Permissions)) {
           // ActivityCompat.requestPermissions(this, Permissions, Permission_All);

        } else {
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }
    }
    public static boolean hasPermissions(Context context, String... permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {

            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {



                }

            }

                /**/
            allow(context);

        }
        return true;
    }

    public static void allow(Context context) {

        Intent i = new Intent(context, Home.class);
        context.startActivity(i);
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


}

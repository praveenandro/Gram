package thangam.photostudio;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * Created by Admin on 22-01-2018.
 */

public class FourthFragment extends Fragment{

    View myView;
    EditText e4,e1,e2,e3;
    ExpandableTextView expandableTextView;
 Button b;

    private final int SMS_REQUEST_CODE = 2;
    private final int REQUEST_READ_PHONE_STATE=3;

    String lt="We are happy to help with any questions you have about our services. Please complete the enquiry form below and a member of AAA photos team will contact you shortly. For urgent enquiries please contact one of our branches listed in the \"Contact Us\" page, we thank you for your patronage.";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fourth, container, false);
        e1 = (EditText) myView.findViewById(R.id.ee1);
        e2 = (EditText) myView.findViewById(R.id.ee2);
        e3 = (EditText) myView.findViewById(R.id.ee3);
        e4 = (EditText) myView.findViewById(R.id.ee4);
        b = (Button) myView.findViewById(R.id.ebut1);
        TextView tv1 = (TextView)myView.findViewById(R.id.sttext1);
        TextView tv = (TextView)myView.findViewById(R.id.expandable_text);
        TextView tv2 = (TextView)myView.findViewById(R.id.et);
        Typeface tf2 =  Typeface.createFromAsset(getActivity().getAssets(),"zagga.otf");
        Typeface tf3 =  Typeface.createFromAsset(getActivity().getAssets(),"ghotel.otf");
        Typeface tf1 =  Typeface.createFromAsset(getActivity().getAssets(),"fff.ttf");
        tv1.setTypeface(tf2);
        tv.setTypeface(tf3);
        tv2.setTypeface(tf1);
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }

        expandableTextView = (ExpandableTextView) myView.findViewById(R.id.expand_text_view);
        expandableTextView.setText(lt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sms(view);

            }
        });


        return myView;

    }
    public void sms(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Are you sure,You want to submit Form?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        String n1 = e1.getText().toString();
                        String n2 = e2.getText().toString();
                        String n3 = e3.getText().toString();
                        String n4 = e4.getText().toString();
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                            askPermission(Manifest.permission.SEND_SMS, SMS_REQUEST_CODE);
                        } else {
                            SmsManager sms = SmsManager.getDefault();

                            sms.sendTextMessage("+919894667404", null, "Name :" + n1 + "\n Mobile:" + n2 + "\n Email:" + n3 + "\nRequirement" + n4, null, null);
                            Toast.makeText(getActivity(), "sent", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        ).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });


        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog dialog;
        dialog = builder.create();
        dialog.setTitle("Alert dialog Example");
        dialog.show();
    }
    private void askPermission(String permission, int requestCode) {
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case SMS_REQUEST_CODE:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(getActivity(), "Access Granted", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(getActivity(), "Access Denied", Toast.LENGTH_SHORT).show();
            }
                break;
            case REQUEST_READ_PHONE_STATE:

                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

            }
        }

    }
    }




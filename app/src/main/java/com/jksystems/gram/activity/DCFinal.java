package com.jksystems.gram.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jksystems.gram.R;

import org.json.JSONArray;
import org.json.JSONObject;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;

public class DCFinal extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvName,tvDoorno,tvAccessNo,tvAddress,tvArrTax,tvArrLibTax,tvCurTax,tvCurLiTax,tvTotal;
    Button bConfirm;
    String sType,sDoorNo, sAccessNo;
    // APIinterface apiInterface;
    ProgressDialog progressDialog;
   // Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcfinal);
        
        tvName = (TextView)findViewById(R.id.id_name);
        tvDoorno = (TextView)findViewById(R.id.id_door_no);
        tvAccessNo = (TextView)findViewById(R.id.id_assess_no);
        tvAddress = (TextView)findViewById(R.id.id_address);
        tvArrTax = (TextView)findViewById(R.id.id_arr_tax);
        tvArrLibTax = (TextView)findViewById(R.id.id_Arrlib_tax);
        tvCurTax = (TextView)findViewById(R.id.id_cur_tax);
        tvCurLiTax = (TextView)findViewById(R.id.id_CurLib_tax);
        tvTotal = (TextView)findViewById(R.id.id_Total);

        bConfirm = (Button)findViewById(R.id.id_confirm); 

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("COLLECTION");
        Intent i=getIntent();
        Bundle  bundle=i.getExtras();
        sType=bundle.getString("v1");
        sAccessNo=bundle.getString("v2");
        sDoorNo=bundle.getString("v3");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressDialog=new ProgressDialog(DCFinal.this);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, "http://thejksystems.com/APIs/publicdetails.php?type="+sType+"&assess="+sAccessNo+"&doorno="+sDoorNo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arr = jsonObject.getJSONArray("public_details");
                    Toast.makeText(DCFinal.this, arr.length()+"", Toast.LENGTH_SHORT).show();

                    for (int i=0;i<arr.length();i++){

                        JSONObject obj = arr.getJSONObject(i);
                        tvName.setText(obj.get("Name_T").toString());
                        tvAccessNo.setText(obj.get("AssessNo").toString());
                        tvDoorno.setText(obj.get("NewDoorNo").toString());
                        tvAddress.setText(obj.get("Address_T").toString());
                        tvArrTax.setText(obj.get("ArrearAmount").toString());
                        tvArrLibTax.setText(obj.get("ArrearLibAmt").toString());
                        tvCurTax.setText(obj.get("CurrentAmount").toString());
                        tvCurLiTax.setText(obj.get("CurrentLibAmt").toString());
                        int i1=Integer.parseInt(obj.get("ArrearAmount").toString());
                        int i2=Integer.parseInt(obj.get("ArrearLibAmt").toString());
                        int i3=Integer.parseInt(obj.get("CurrentAmount").toString());
                        int i4=Integer.parseInt(obj.get("CurrentLibAmt").toString());

                        tvTotal.setText(String.valueOf(i1+i2+i3+i4));
                    }
                }
                catch (Exception e){

                }



                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params=new HashMap<String, String>();
//
//                params.put("type",sType);
//                params.put("assess",sAccessNo);
//                params.put("doorno",sDoorNo);
//                return params;
//            }


        });

        RequestQueue requestQueue = Volley.newRequestQueue(DCFinal.this);
        requestQueue.add(request);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });
        
        
        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                displayDialog();
            }
        });
    }

    private void displayDialog() {

        /*dialog = new Dialog(DCFinal.this);
        dialog.setContentView(R.layout.payment_confirmation);
        dialog.setCancelable(true);*/

        new EZDialog.Builder(DCFinal.this)
                .setTitle("PAYMENT CONFIRMATION")
                .setMessage("TOTAL AMOUNT - 24.00 RS")
                .setPositiveBtnText("DONE")
                .setNegativeBtnText("CANCEL")
                .setCancelableOnTouchOutside(false)
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        //todo
                    }
                })
                .OnNegativeClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        //todo



                    }
                })
                .build();

    }

}

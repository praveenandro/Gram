package com.jksystems.gram.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jksystems.gram.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DemandCollection extends AppCompatActivity {

    RadioButton radioButton;
    RadioGroup radioGroup;
    EditText eAssess, eDoor;
    Button bProcced;
    TextView status;
    int selectedId;
    String sDoorNo, sAccessNo;
    // APIinterface apiInterface;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_collection);

        // apiInterface = APIClient.getClient().create(APIinterface.class);

        radioGroup = (RadioGroup) findViewById(R.id.id_radGroup);

        eAssess = (EditText) findViewById(R.id.id_ed_assess);
        eDoor = (EditText) findViewById(R.id.id_ed_door);

        bProcced = (Button) findViewById(R.id.id_but_proceed);
        status=(TextView)findViewById(R.id.status);

        bProcced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(DemandCollection.this);
                progressDialog.setMessage("Fetching Data...");
                progressDialog.show();


                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

                sDoorNo = eDoor.getText().toString();
                sAccessNo = eAssess.getText().toString();
                String rad = radioButton.getText().toString();

                if (sDoorNo.length() > 0) {

                    // Toast.makeText(getApplicationContext(),String.valueOf(rad.charAt(0)),Toast.LENGTH_SHORT).show();
                    getDetails(String.valueOf(rad.charAt(0)), sDoorNo, "");
                } else {

                    //  Toast.makeText(getApplicationContext(),String.valueOf(rad.charAt(0)),Toast.LENGTH_SHORT).show();
                    getDetails(String.valueOf(rad.charAt(0)), "", sAccessNo);
                }

//

            }
        });
    }

    private void getDetails(String type, final String sDoorNo, final String sAccessNo) {

        final String sType = type;
        final String sDoorno = sDoorNo;
        final String sAccessno = sAccessNo;

        StringRequest request = new StringRequest(Request.Method.POST, "http://thejksystems.com/APIs/publicdetails.php?type="+sType+"&assess="+sAccessno+"&doorno="+sDoorno, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arr = jsonObject.getJSONArray("public_details");
                    Toast.makeText(DemandCollection.this, arr.length()+"", Toast.LENGTH_SHORT).show();

                     if (arr.length()==0){

                        status.setText("Invalid Assessno or Doorno...!!!");
                     }else{

                         Bundle bundle=new Bundle();
                         bundle.putString("v1",sType);
                         bundle.putString("v2",sAccessNo);
                         bundle.putString("v3",sDoorno);
                         Intent i = new Intent(DemandCollection.this, DCFinal.class);
                         i.putExtras(bundle);
                         startActivity(i);
                     }
                    for (int i=0;i<arr.length();i++){

                        JSONObject obj = arr.getJSONObject(i);

                        Log.d("output", "onResponse: "+obj.get("Name_T"));
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

        RequestQueue requestQueue = Volley.newRequestQueue(DemandCollection.this);
        requestQueue.add(request);
    }
}

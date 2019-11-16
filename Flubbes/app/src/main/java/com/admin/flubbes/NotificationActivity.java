package com.admin.flubbes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class NotificationActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://flubbes.com/Apis/notificationnew.php";
    private RecyclerView recyclerView;
    Toolbar toolbar;
    List<HashMap<String, String>> MyArrList;
    private RecyclerView.Adapter adapter;
    int lengthJsonArr;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar);




        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listItems = new ArrayList<>();

        loadRecyclerviewData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        }
// implement setNavigationOnClickListener event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// add code here that execute on click of navigation button
                Intent i = new Intent(NotificationActivity.this, Home.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

    }

    private void loadRecyclerviewData() {



        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {

                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonResponse = jsonArray.getJSONObject(0);

                            String status = jsonResponse.optString("code").toString();
                            Log.e("status", status);
                          //  MyArrList = new ArrayList<HashMap<String, String>>();
                            if (status.equals("success")) {

                                JSONArray jsonMainNode = jsonResponse.optJSONArray("data");
                                Log.e("jsonMainNode", jsonMainNode.toString());


                                lengthJsonArr = jsonMainNode.length();

                                for (int i = 0; i < lengthJsonArr; i++) {

                                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);



                                    ListItem item = new ListItem(
                                            jsonChildNode.getString("title"),
                                            jsonChildNode.getString("message"),
                                            jsonChildNode.getString("image")
                                    );
                                    listItems.add(item);


                                }

                                adapter = new MyAdapter(listItems,getApplicationContext());
                                recyclerView.setAdapter(adapter);
                                //list_stock_request.setAdapter(new ImageAdapter(getApplicationContext(), MyArrList));

                            } else {
                                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"praveen"+error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("praveen",error.getMessage().toString());

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

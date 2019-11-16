package thangam.photostudio;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Timer;
import java.util.TimerTask;

public class Lamination extends AppCompatActivity {
    ImageView im1,im2,im3,im4,im5,im6,im7,im8,im9;
    Button b;
    ExpandableTextView expandableTextView;
    String lt="A perfect gift to be treasured for generations! Give your loved one a laminated family photograph that can become a family heirloom. AAA's Vacuum Lamination & Framing will transform your photo, art or document by sealing your original under your choice. Laminating is water proof and your originals are protected from common picture problems, such as UV light, wrinkling, sagging, dirt, stains, smoke, moisture and mildew. Laminating keeps your photos and documents look fresh and crisp forever.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamination);
        final Handler mHandler = new Handler();
        b=(Button)findViewById(R.id.lambut1);
        im1=(ImageView)findViewById(R.id.lamim1);
        im2=(ImageView)findViewById(R.id.lamim2);
        im3=(ImageView)findViewById(R.id.lamim3);
        im4=(ImageView)findViewById(R.id.lamim4);
        im5=(ImageView)findViewById(R.id.lamim5);
        im6=(ImageView)findViewById(R.id.lamim6);
        im7=(ImageView)findViewById(R.id.lamim7);
        im8=(ImageView)findViewById(R.id.lamim8);
        im9=(ImageView)findViewById(R.id.lamim9);
        TextView tv=(TextView)findViewById(R.id.lamtext1);
        TextView tv1 = (TextView)findViewById(R.id.expandable_text);
        Typeface tf3 =  Typeface.createFromAsset(Lamination.this.getAssets(),"ghotel.otf");
        tv1.setTypeface(tf3);
        Typeface tf2 =  Typeface.createFromAsset(Lamination.this.getAssets(),"zagga.otf");
        tv.setTypeface(tf2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                im1.setVisibility(View.VISIBLE);
                im2.setVisibility(View.VISIBLE);
                im3.setVisibility(View.VISIBLE);
                im4.setVisibility(View.VISIBLE);
                im5.setVisibility(View.VISIBLE);
                im6.setVisibility(View.VISIBLE);
                im7.setVisibility(View.VISIBLE);
                im8.setVisibility(View.VISIBLE);
                im9.setVisibility(View.VISIBLE);


            }
        });
        expandableTextView=(ExpandableTextView)findViewById(R.id.expand_text_view);
        expandableTextView.setText(lt);


    }
    }


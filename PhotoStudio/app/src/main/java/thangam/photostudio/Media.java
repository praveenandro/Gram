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

public class Media extends AppCompatActivity {
    ImageView slidingimage;
    Button b;
    ExpandableTextView expandableTextView;
    String lt="We accept images from a variety of media - CD's, DVD's, CompactFlash, Secure Digital, Memory Stick and Smartmedia cards. How many fantastic photos do you have stored on your mobile phone? How many times do you get to see them? Wouldn’t it be great to have your favourite photos printed so that you can see them all the time? AAA now offers a printing service at the studio which can be done while you wait in most cases.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        final Handler mHandler = new Handler();
        b=(Button)findViewById(R.id.mediabut1);
        slidingimage=(ImageView)findViewById(R.id.mediaim1);
        TextView tv=(TextView)findViewById(R.id.mediatext1);
        TextView tv1 = (TextView)findViewById(R.id.expandable_text);
        Typeface tf3 =  Typeface.createFromAsset(Media.this.getAssets(),"ghotel.otf");
        tv1.setTypeface(tf3);
        Typeface tf2 =  Typeface.createFromAsset(Media.this.getAssets(),"zagga.otf");
        tv.setTypeface(tf2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingimage.setVisibility(View.VISIBLE);


            }
        });
        expandableTextView=(ExpandableTextView)findViewById(R.id.expand_text_view);
        expandableTextView.setText(lt);


    }
    }


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

public class Wedding extends AppCompatActivity {
    ImageView im1,im2,im3,im4,im5,im6,im7,im8;
    Button b;
    ExpandableTextView expandableTextView;
    String lt="Here at AAA Photos we see your wedding day as a unique event to be captured in a style which suits you. We capture the memories of your day so that you can remember it in the very best way. From our own experience we know how quickly the day flies by, and how wonderful it is to have a beautiful, stylish record of your beautiful wedding day. We have a bespoke, relaxed and natural approach to capturing your wedding day memories. Our aim is to provide you stunning, creative images and films whilst recording the laughs, tears and moments as they happen.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding);
        final Handler mHandler = new Handler();
        b=(Button)findViewById(R.id.wedbut1);
        im1=(ImageView)findViewById(R.id.wedim1);
        im2=(ImageView)findViewById(R.id.wedim2);
        im3=(ImageView)findViewById(R.id.wedim3);
        im4=(ImageView)findViewById(R.id.wedim4);
        im5=(ImageView)findViewById(R.id.wedim5);
        im6=(ImageView)findViewById(R.id.wedim6);
        im7=(ImageView)findViewById(R.id.wedim7);
        im8=(ImageView)findViewById(R.id.wedim8);
        TextView tv=(TextView)findViewById(R.id.wedtext1);
        TextView tv1 = (TextView)findViewById(R.id.expandable_text);
        Typeface tf3 =  Typeface.createFromAsset(Wedding.this.getAssets(),"ghotel.otf");
        tv1.setTypeface(tf3);
        Typeface tf2 =  Typeface.createFromAsset(Wedding.this.getAssets(),"zagga.otf");
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



            }
        });
        expandableTextView=(ExpandableTextView)findViewById(R.id.expand_text_view);
        expandableTextView.setText(lt);


    }
}

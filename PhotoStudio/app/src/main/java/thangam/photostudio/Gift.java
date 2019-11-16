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

public class Gift extends AppCompatActivity {
    ImageView im1,im2,im3,im4,im5,im6,im7,im8;
    Button b;
    ExpandableTextView expandableTextView;
    String lt="Every photo you take has story tied to it. The best gifts are the same way, they tell a story. No matter the occasion, family and friends will always cherish personalized photo gifts from us because they hold dear the memories your photos share. Choose from our professional quality for unique & personalized photo gifts designed with your favourite pictures. We can now print photos from your mobile phone onto different products, making great photo gifts.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        final Handler mHandler = new Handler();
        b=(Button)findViewById(R.id.gifbut1);
        im1=(ImageView)findViewById(R.id.gifim1);
        im2=(ImageView)findViewById(R.id.gifim2);
        im3=(ImageView)findViewById(R.id.gifim3);
        im4=(ImageView)findViewById(R.id.gifim4);
        im5=(ImageView)findViewById(R.id.gifim5);
        im6=(ImageView)findViewById(R.id.gifim6);
        im7=(ImageView)findViewById(R.id.gifim7);
        im8=(ImageView)findViewById(R.id.gifim8);
        TextView tv=(TextView)findViewById(R.id.giftext1);
        TextView tv1 = (TextView)findViewById(R.id.expandable_text);
        Typeface tf3 =  Typeface.createFromAsset(Gift.this.getAssets(),"ghotel.otf");
        tv1.setTypeface(tf3);
        Typeface tf2 =  Typeface.createFromAsset(Gift.this.getAssets(),"zagga.otf");
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

package thangam.photostudio;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Timer;
import java.util.TimerTask;

public class Album extends AppCompatActivity {
    ImageView im1,im2,im3,im4,im5,im6,im7,im8;
    Button b;
    ExpandableTextView expandableTextView;
    String lt="AAA Photo's Photo Books and Albums are beautifully designed and produced to stand the test of time, to give you a book to treasure and hand down through the generations. Our aim has been to bring the worlds of print and digital technology together to give you the best photo book making experience possible. You will love the smell, the feel, the look, and the touch of them, and now we want to help you to create your book – the most original book of your moments and memories to last for ever.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        final Handler mHandler = new Handler();
        b=(Button)findViewById(R.id.albut1);
        im1=(ImageView)findViewById(R.id.alim1);
        im2=(ImageView)findViewById(R.id.alim2);
        im3=(ImageView)findViewById(R.id.alim3);
        im4=(ImageView)findViewById(R.id.alim4);
        im5=(ImageView)findViewById(R.id.alim5);
        im6=(ImageView)findViewById(R.id.alim6);
        im7=(ImageView)findViewById(R.id.alim7);
        im8=(ImageView)findViewById(R.id.alim8);
        TextView tv=(TextView)findViewById(R.id.altext1);
        TextView tv1 = (TextView)findViewById(R.id.expandable_text);
        Typeface tf3 =  Typeface.createFromAsset(Album.this.getAssets(),"ghotel.otf");
        tv1.setTypeface(tf3);
        Typeface tf2 =  Typeface.createFromAsset(Album.this.getAssets(),"zagga.otf");
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

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

public class Old extends AppCompatActivity {
    ImageView im1,im2,im3,im4,im5;
    Button b;
    ExpandableTextView expandableTextView;
    String lt="Our photo editing & retouching artists provides best profession Photo Restoration Service to clients. We help you to recover precious memories and preserve them for long lasting. Our team is skilled for photo restoration and enlargement, restoration of old and damaged photo, converting b/w photo to color, adding or removing people/object. We also specialize in Recreating / repairing missing parts of photo.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old);
        final Handler mHandler = new Handler();
        b=(Button)findViewById(R.id.oldbut1);
        im1=(ImageView)findViewById(R.id.oldim1);
        im2=(ImageView)findViewById(R.id.oldim2);
        im3=(ImageView)findViewById(R.id.oldim3);
        im4=(ImageView)findViewById(R.id.oldim4);
        im5=(ImageView)findViewById(R.id.oldim5);
        TextView tv=(TextView)findViewById(R.id.oldtext1);
        TextView tv1 = (TextView)findViewById(R.id.expandable_text);
        Typeface tf3 =  Typeface.createFromAsset(Old.this.getAssets(),"ghotel.otf");
        tv1.setTypeface(tf3);
        Typeface tf2 =  Typeface.createFromAsset(Old.this.getAssets(),"zagga.otf");
        tv.setTypeface(tf2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                im1.setVisibility(View.VISIBLE);
                im2.setVisibility(View.VISIBLE);
                im3.setVisibility(View.VISIBLE);
                im4.setVisibility(View.VISIBLE);
                im5.setVisibility(View.VISIBLE);



            }
        });
        expandableTextView=(ExpandableTextView)findViewById(R.id.expand_text_view);
        expandableTextView.setText(lt);


    }
}

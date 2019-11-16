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

public class Passport extends AppCompatActivity {

    public int currentimageindex=0;
    Timer timer;
    TimerTask task;
    ImageView slidingimage,slidingimage1,slidingimage2;
    Button b;
    ExpandableTextView expandableTextView;
    String longText="Our professional order processing team has gained unparalleled passport photos compliance experience - being the largest passport photos shop in Coimbatore. We have a purpose built professional passport studio, with professional lighting, camera equipment and state of the art digital photographic printers. Within minutes of walking in, you will be holding a set of high quality Visa photos in your hand that conform to all relevant Visa regulations. Unlike traditional photo shops, our system currently supports the exact government requirements of over 60 countries.\n" +
            "\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport);
        final Handler mHandler = new Handler();
        b=(Button)findViewById(R.id.passbut1);
        TextView tv=(TextView)findViewById(R.id.passtext1);
        TextView tv1 = (TextView)findViewById(R.id.expandable_text);
        Typeface tf3 =  Typeface.createFromAsset(Passport.this.getAssets(),"ghotel.otf");
        tv1.setTypeface(tf3);
        slidingimage=(ImageView)findViewById(R.id.passim1);
        slidingimage1=(ImageView)findViewById(R.id.passim2);
        slidingimage2=(ImageView)findViewById(R.id.passim3);
        Typeface tf2 =  Typeface.createFromAsset(Passport.this.getAssets(),"zagga.otf");
        tv.setTypeface(tf2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingimage.setVisibility(View.VISIBLE);
                slidingimage1.setVisibility(View.VISIBLE);
                slidingimage2.setVisibility(View.VISIBLE);
            }
        });
        expandableTextView=(ExpandableTextView)findViewById(R.id.expand_text_view);
        expandableTextView.setText(longText);

    }
}

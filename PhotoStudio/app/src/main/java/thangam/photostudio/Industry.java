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

public class Industry extends AppCompatActivity {
    ImageView im1,im2,im3,im4,im5,im6,im7,im8;
    Button b;
    ExpandableTextView expandableTextView;
    String lt="When you work in an industry, nothing tells the story better of what you do than quality photos of your plant, product or equipment. At AAA Photos we’ve got extensive experience working behind the camera in industrial environments. We aren’t afraid of red dust, heights or a little bit of dirt under our fingernails, and will go to great— but safe— lengths to get the perfect shot. The photographs we take for your business are perfect for all your marketing needs. Whether that’s advertising, brochures, catalogues, websites, or business marketing collateral. All images are of the highest quality and completely versatile, so you get maximum return on investment.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry);
        final Handler mHandler = new Handler();
        b=(Button)findViewById(R.id.indbut1);
        im1=(ImageView)findViewById(R.id.indim1);
        im2=(ImageView)findViewById(R.id.indim2);
        im3=(ImageView)findViewById(R.id.indim3);
        im4=(ImageView)findViewById(R.id.indim4);
        im5=(ImageView)findViewById(R.id.indim5);
        im6=(ImageView)findViewById(R.id.indim6);
        im7=(ImageView)findViewById(R.id.indim7);
        im8=(ImageView)findViewById(R.id.indim8);
        TextView tv=(TextView)findViewById(R.id.indtext1);
        TextView tv1 = (TextView)findViewById(R.id.expandable_text);
        Typeface tf3 =  Typeface.createFromAsset(Industry.this.getAssets(),"ghotel.otf");
        tv1.setTypeface(tf3);
        Typeface tf2 =  Typeface.createFromAsset(Industry.this.getAssets(),"zagga.otf");
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

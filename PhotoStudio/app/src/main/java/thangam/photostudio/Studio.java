package thangam.photostudio;

import android.content.Intent;
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

public class Studio extends AppCompatActivity {
    ImageView im1,im2,im3,im4;
    TextView t,t1,t2,t3,t4;
    Button b,b1,b2,b3,b4;
    ExpandableTextView expandableTextView;

    String lt="Choosing between getting your portraits taken inside a studio and outdoors can be a difficult decision. This is were we come in, we specialize in studio or natural lighting photography. We are available for infants, children, families, seniors, teens, pets, and sports portraits. We can even come out to your home and capture the moments at your location. With the advancement of modern technology and our years of experience, we have been able to revolutionize the photography industry, bringing a fresh, creative edge to your portraits.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio);
        final Handler mHandler = new Handler();
        b=(Button)findViewById(R.id.stbut1);
        b1=(Button)findViewById(R.id.stbut2);
        b2=(Button)findViewById(R.id.stbut3);
        b3=(Button)findViewById(R.id.stbut4);
        t=(TextView)findViewById(R.id.sttext1);
        t1=(TextView)findViewById(R.id.family);
        t2=(TextView)findViewById(R.id.kids);
        t3=(TextView)findViewById(R.id.wedding);
        t4=(TextView)findViewById(R.id.fashion);
        TextView tv = (TextView)findViewById(R.id.expandable_text);
        Typeface tf3 =  Typeface.createFromAsset(Studio.this.getAssets(),"ghotel.otf");
        Typeface tf2 =  Typeface.createFromAsset(Studio.this.getAssets(),"zagga.otf");
        Typeface tf1 =  Typeface.createFromAsset(Studio.this.getAssets(),"fff.ttf");
        t.setTypeface(tf2);
        t1.setTypeface(tf1);
        t2.setTypeface(tf1);
        t3.setTypeface(tf1);
        t4.setTypeface(tf1);

        tv.setTypeface(tf3);
   b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Bundle bun=new Bundle();
        bun.putInt("img1",R.drawable.bun1);
        bun.putInt("img2",R.drawable.bun2);
        bun.putInt("img3",R.drawable.bun3);
        bun.putInt("img4",R.drawable.bun4);
        bun.putInt("img5",R.drawable.bun5);
        Intent i=new Intent(Studio.this,Bun.class);
        i.putExtras(bun);
        startActivity(i);

    }
});

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bun=new Bundle();
                bun.putInt("img1",R.drawable.bun6);
                bun.putInt("img2",R.drawable.bun7);
                bun.putInt("img3",R.drawable.bun8);
                bun.putInt("img4",R.drawable.bun9);
                bun.putInt("img5",R.drawable.bun10);
                Intent i=new Intent(Studio.this,Bun.class);
                i.putExtras(bun);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bun=new Bundle();
                bun.putInt("img1",R.drawable.bun11);
                bun.putInt("img2",R.drawable.bun12);
                bun.putInt("img3",R.drawable.bun13);
                bun.putInt("img4",R.drawable.bun14);
                bun.putInt("img5",R.drawable.bun15);
                Intent i=new Intent(Studio.this,Bun.class);
                i.putExtras(bun);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bun=new Bundle();
                bun.putInt("img1",R.drawable.bun16);
                bun.putInt("img2",R.drawable.bun17);
                bun.putInt("img3",R.drawable.bun18);
                bun.putInt("img4",R.drawable.bun19);
                bun.putInt("img5",R.drawable.st4);
                Intent i=new Intent(Studio.this,Bun.class);
                i.putExtras(bun);
                startActivity(i);
            }
        });
        expandableTextView=(ExpandableTextView)findViewById(R.id.expand_text_view);
        expandableTextView.setText(lt);

    }
    }


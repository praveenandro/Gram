package thangam.photostudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Bun extends AppCompatActivity {
    ImageView im1,im2,im3,im4,im5;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bun);
        im1=(ImageView)findViewById(R.id.bim2);
        im2=(ImageView)findViewById(R.id.bim3);
        im3=(ImageView)findViewById(R.id.bim4);
        im4=(ImageView)findViewById(R.id.bim5);
        im5=(ImageView)findViewById(R.id.bim6);
 b=(Button)findViewById(R.id.bbut1);



        Intent i=getIntent();
        Bundle bun=i.getExtras();
        int pic=bun.getInt("img1");
        int pic1=bun.getInt("img2");
        int pic2=bun.getInt("img3");
        int pic3=bun.getInt("img4");
        int pic4=bun.getInt("img5");
        im1.setImageResource(pic);
        im2.setImageResource(pic1);
        im3.setImageResource(pic2);
        im4.setImageResource(pic3);
        im5.setImageResource(pic4);

  b.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent i=new Intent(Bun.this,Studio.class);
          startActivity(i);
      }
  });

    }
}

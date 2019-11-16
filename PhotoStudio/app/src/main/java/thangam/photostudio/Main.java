package thangam.photostudio;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    Button b,b1;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.log);
        b1=(Button)findViewById(R.id.join);
        t=(TextView)findViewById(R.id.matext1);
        Typeface tf3 =  Typeface.createFromAsset(Main.this.getAssets(),"CaviarDreams_BoldItalic.ttf");

        t.setTypeface(tf3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main.this,Login.class);
                  startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main.this,SignUp.class);
                startActivity(i);
            }
        });
    }
}

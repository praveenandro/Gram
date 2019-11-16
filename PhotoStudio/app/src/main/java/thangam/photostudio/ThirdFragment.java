package thangam.photostudio;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Admin on 22-01-2018.
 */

public class ThirdFragment extends Fragment{
View myView;
    TextView tv,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.third,container,false);
        tv=(TextView)myView.findViewById(R.id.textthird1);
      //  tv.setPaintFlags(tv.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv1=(TextView)myView.findViewById(R.id.textthird2);
      //  tv1.setPaintFlags(tv1.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv2=(TextView)myView.findViewById(R.id.textthird3);
      //  tv2.setPaintFlags(tv2.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv3=(TextView)myView.findViewById(R.id.textthird4);
      //  tv3.setPaintFlags(tv3.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv4=(TextView)myView.findViewById(R.id.textthird5);
       // tv4.setPaintFlags(tv4.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv5=(TextView)myView.findViewById(R.id.textthird6);
       // tv5.setPaintFlags(tv5.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv6=(TextView)myView.findViewById(R.id.textthird7);
       // tv6.setPaintFlags(tv6.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv7=(TextView)myView.findViewById(R.id.textthird8);
       // tv7.setPaintFlags(tv7.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tv8=(TextView)myView.findViewById(R.id.textthird9);
       // tv8.setPaintFlags(tv8.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Typeface tf2 =  Typeface.createFromAsset(getActivity().getAssets(),"zagga.otf");
        tv.setTypeface(tf2);
        tv1.setTypeface(tf2);
        tv2.setTypeface(tf2);
        tv3.setTypeface(tf2);
        tv4.setTypeface(tf2);
        tv5.setTypeface(tf2);
        tv6.setTypeface(tf2);
        tv7.setTypeface(tf2);
        tv8.setTypeface(tf2);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThirdFragment.this.getActivity(), Studio.class);
                startActivity(i);



            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ThirdFragment.this.getActivity(), Passport.class);
                startActivity(in);



            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThirdFragment.this.getActivity(), Media.class);
                startActivity(i);



            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThirdFragment.this.getActivity(), Lamination.class);
                startActivity(i);



            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThirdFragment.this.getActivity(), Old.class);
                startActivity(i);



            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThirdFragment.this.getActivity(), Wedding.class);
                startActivity(i);



            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThirdFragment.this.getActivity(), Industry.class);
                startActivity(i);



            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThirdFragment.this.getActivity(), Gift.class);
                startActivity(i);



            }
        });

        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThirdFragment.this.getActivity(), Album.class);
                startActivity(i);



            }
        });
        return myView;
    }
}

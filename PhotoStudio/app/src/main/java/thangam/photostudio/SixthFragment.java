package thangam.photostudio;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Admin on 22-01-2018.
 */

public class SixthFragment extends Fragment {
    View myView;
    ImageView i;
    TextView t;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.sixth, container, false);
        i = (ImageView) myView.findViewById(R.id.coim2);
        TextView tv = (TextView)myView.findViewById(R.id.cotext2);
        TextView tv1 = (TextView)myView.findViewById(R.id.cotext1);
        TextView tv2 = (TextView)myView.findViewById(R.id.cotext3);
        TextView tv3 = (TextView)myView.findViewById(R.id.cotext4);
        TextView tv4 = (TextView)myView.findViewById(R.id.cotext5);
        TextView tv5 = (TextView)myView.findViewById(R.id.cotext6);
        TextView tv6 = (TextView)myView.findViewById(R.id.cotext7);
        TextView tv7 = (TextView)myView.findViewById(R.id.cotext8);
        Typeface tf =  Typeface.createFromAsset(getActivity().getAssets(),"proxanys.otf");
        Typeface tf1 =  Typeface.createFromAsset(getActivity().getAssets(),"ghotel.otf");
        Typeface tf2 =  Typeface.createFromAsset(getActivity().getAssets(),"zagga.otf");
        tv.setTypeface(tf);
        tv2.setTypeface(tf1);
        tv1.setTypeface(tf2);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        tv6.setTypeface(tf);
        tv5.setTypeface(tf1);
        tv7.setTypeface(tf1);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MapsActivity.class);
                startActivity(i);
            }
        });


        return myView;


    }

              }



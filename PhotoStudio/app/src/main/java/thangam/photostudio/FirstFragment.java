package thangam.photostudio;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 22-01-2018.
 */

public class FirstFragment  extends Fragment{
View myView;
    public int currentimageindex=0;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;




    Timer timer;
    TimerTask task;
    ImageView slidingimage;
    //ExpandableTextView expandableTextView;
   // String longText="AAA today is a staple name in most of the households and Industrial houses of TamilNadu.If one is looking for, state of the art technology, creativity, love for photography, unparalleled commitment to quality  and world class professionalism.AAA will be a one stop shop for all this and more.. "+
           // "Well, it’s our keen eye for perfection, an outstanding creative team to capture the best in you  " +
           // "and the thirst for providing our customers a memorable and fun experience when in AAA and to take back " +
          //  "home a souvenir that would speak volumes for generations to come.";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first, container, false);
        // Create runnable for posting
        final Handler mHandler = new Handler();

       //expandableTextView = (ExpandableTextView) myView.findViewById(R.id.expand_text_view);
      // expandableTextView.setText(longText);
        viewPager = (ViewPager) myView.findViewById(R.id.viewPager);
        TextView tv1 = (TextView)myView.findViewById(R.id.textView2);
        TextView tv2 = (TextView)myView.findViewById(R.id.text);
        Typeface tf =  Typeface.createFromAsset(getActivity().getAssets(),"zagga.otf");
        Typeface tf1 =  Typeface.createFromAsset(getActivity().getAssets(),"ghotel.otf");
        tv1.setTypeface(tf);
        tv2.setTypeface(tf1);
        sliderDotspanel = (LinearLayout)myView.findViewById(R.id.SliderDots);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);


        }


        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

        return myView;
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    } else if (viewPager.getCurrentItem() == 3) {
                        viewPager.setCurrentItem(4);
                    } else if (viewPager.getCurrentItem() == 4) {
                        viewPager.setCurrentItem(5);
                    } else if (viewPager.getCurrentItem() == 5) {
                        viewPager.setCurrentItem(6);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }
}












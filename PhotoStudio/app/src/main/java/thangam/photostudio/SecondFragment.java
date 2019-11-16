package thangam.photostudio;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * Created by Admin on 22-01-2018.
 */

public class SecondFragment extends Fragment{
    private ActionBar toolbar;
View myView;

    ExpandableTextView expandableTextView;
    String LongText=" AAA today is a staple name in most of the households and Industrial houses of TamilNadu. For the legacy in world class photography commenced as early as 1940s under the leadership of our founder Sri. Raguram. \n" +
            "\n" +
            "If one is looking for, state of the art technology, creativity, love for photography, unparalleled commitment to quality and world class professionalism, AAA will be a one stop shop for all this and more. \n" +
            "\n" +
            "The saga of creating not just photographs but lasting impressions commenced 8 decades ago and it’s both humbling and a matter of pride for team AAA to be known as the best in photography and the market leader in TamilNadu branches spanning across the city. \n" +
            "\n" +
            "Quality and customer satisfaction has been our work mantra and our commitment to this profession. Each of our branch has an ambience that’s tastefully crafted to suit your comfort and portrait studios that are equipped with the best of equipments to match any world class portrait outlet. Our team of trained experts and skilled technicians, help transform a regular picture to a “WOW” end product ! \n" +
            "\n" +
            "What makes AAA different? Well, it’s our keen eye for perfection, an outstanding creative team to capture the best in you and the thirst for providing our customers a memorable and fun experience when in AAA and to take back home a souvenir that would speak volumes for generations to come. \n" +
            "\n" +
            "A portrait from AAA is undoubtedly a piece of Art. Rare, precious and one of a kind. Something for you to cherish and others to envy. A home without an AAA portrait is incomplete... so wait no longer, we at AAA assure you that, YOU will go back home smiling !!\n" +
            "\n" +
            "Management :\n" +
            " \n" +
            "Founder :\n" +
            "AAA was founded in the year 1940 by Sri. Raguram, a photographic legend and an ace businessman.\n" +
            " \n" +
            "Managing Director :\n" +
            "The son of our founder, Sri. R. Jayakumar is the current Managing director. He took up the reins of AAA, in the year 1970 and has had a dream run. It is under the leadership of this iconic business man, that AAA spread it’s wings across the city, spanning from a single branch in the year 2000 to 8 branches now in 2015. His strength and business acumen in the field of Photography is well renowned and is the key person monitoring and guiding team AAA in it’s commitment to world class quality in the field of Photography.\n" +
            " \n" +
            "Executive Directors:\n" +
            "Mr. Ajay kumar, and Mr. Vijay kumar, are the executive directors of AAA who with their keen interest for photography have helped evolve the business and take it to newer heights.";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.second,container,false);
       /* toolbar = getActivity().getActionBar();
        toolbar.setTitle("About Us");*/
        TextView tv = (TextView)myView.findViewById(R.id.expandable_text);
        TextView tv1 = (TextView)myView.findViewById(R.id.sectext);
        expandableTextView=(ExpandableTextView)myView.findViewById(R.id.expand_text_view);
        expandableTextView.setText(LongText);
        Typeface tf =  Typeface.createFromAsset(getActivity().getAssets(),"ghotel.otf");
        tv.setTypeface(tf);
        Typeface tf1 =  Typeface.createFromAsset(getActivity().getAssets(),"zagga.otf");
        tv1.setTypeface(tf1);
        return myView;
    }



}

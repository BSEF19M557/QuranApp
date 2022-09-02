package com.example.contest;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuranWithTranslationAdopter extends ArrayAdapter<ArabicWithTranslation> {
    String type;

    // invoke the suitable constructor of the ArrayAdapter class
    public QuranWithTranslationAdopter(@NonNull Context context, ArrayList<ArabicWithTranslation> arrayList,String type) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);

        this.type=type;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.arabic_with_eng_urdu, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        ArabicWithTranslation currentPosition = getItem(position);


        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.arabicText);
        textView1.setText(currentPosition.getArabic());

        textView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
        textView1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"noorehuda.ttf"));
        textView1.setTypeface(textView1.getTypeface(),Typeface.BOLD);



        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textView2 = currentItemView.findViewById(R.id.urduOrEng);
        textView2.setText(currentPosition.getTranslation());

        textView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
        if(type.equals("E")) {
            textView2.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "tnr.ttf"));
            textView2.setTypeface(textView1.getTypeface(),Typeface.ITALIC);
        }
        else {
            textView2.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "JameelNooriNastaleeq.ttf"));
            textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }


        // then return the recyclable view
        return currentItemView;
    }

}

package com.example.contest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class SurahActivity extends AppCompatActivity {

    ListView listViewAyats;
    TextView suratNameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_surah);

        listViewAyats = findViewById(R.id.listViewAyats);
        suratNameView=findViewById(R.id.suratName);


        Intent intent = getIntent();
        String suratName = intent.getStringExtra("suratName");
        int suratPosition = 1+intent.getIntExtra("suratPosition", -1);

        suratNameView.setText(suratName);
        suratNameView.setTypeface(Typeface.createFromAsset(getAssets(),"JameelNooriNastaleeq.ttf"));



        SqlHandler sqlHandler=new SqlHandler(this);
        ListAdapter listViewAyatsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                sqlHandler.getSurah(suratPosition)){
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
                tv.setTextAlignment(View.TEXT_DIRECTION_ANY_RTL);
                tv.setTypeface(Typeface.createFromAsset(getAssets(),"noorehuda.ttf"));

                ViewGroup.LayoutParams params = tv.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                tv.setLayoutParams(params);

                // Return the view
                return view;
            }
        };



        listViewAyats.setAdapter(listViewAyatsAdapter);
    }
}
package com.example.contest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class paraActivity extends AppCompatActivity {

    ListView listViewPara;
    TextView paraNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para);

        listViewPara=findViewById(R.id.listViewPara);
        paraNameView=findViewById(R.id.paraNumber);

        Intent intent = getIntent();
        String  paraNumber = intent.getStringExtra("paraNumber");

        paraNameView.setText(paraNumber);
        paraNameView.setTypeface(Typeface.createFromAsset(getAssets(),"noorehuda.ttf"));



        SqlHandler sqlHandler=new SqlHandler(this);
        ListAdapter listViewAyatsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                sqlHandler.getCompletePara(Integer.parseInt(paraNumber))){
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



        listViewPara.setAdapter(listViewAyatsAdapter);
    }
}
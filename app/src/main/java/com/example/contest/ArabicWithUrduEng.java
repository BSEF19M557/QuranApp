package com.example.contest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ArabicWithUrduEng extends AppCompatActivity {

    ListView listViewQuranEngUrdu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arabic_with_urdu_eng);

        listViewQuranEngUrdu=findViewById(R.id.QuranEngUrduList);
        Intent intent=getIntent();
        String type=intent.getStringExtra("type");

        SqlHandler sqlHandler=new SqlHandler(this);

        QuranWithTranslationAdopter listViewQuranAdapter = new QuranWithTranslationAdopter(this,sqlHandler.getArabicUrduOrEng(type),type);

        listViewQuranEngUrdu.setAdapter(listViewQuranAdapter);

    }
}
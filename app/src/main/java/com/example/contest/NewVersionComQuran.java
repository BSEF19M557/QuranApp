package com.example.contest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class NewVersionComQuran extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_version_com_quran);

        recyclerView=findViewById(R.id.recyclerview2);
        textView=findViewById(R.id.titleName);

        NewVersionRecyclerAdopter2 adopter=null;
        SqlHandler sqlHandler=new SqlHandler(this);

        Intent intent=getIntent();
        String view=intent.getStringExtra("view");
        String type=intent.getStringExtra("type");
        int position=1+intent.getIntExtra("position",-1);

        textView.setText(view);
        if(view.equals("Quran")){

            adopter=new NewVersionRecyclerAdopter2(this,sqlHandler.getQuran("A"),sqlHandler.getQuran("E"),sqlHandler.getQuran("U"));

        }
        else{
            textView.setTypeface(Typeface.createFromAsset(getAssets(),"noorehuda.ttf"));
            if(type.equals("para")){
                 adopter=new NewVersionRecyclerAdopter2(this,sqlHandler.getCompletePara(Integer.parseInt(view),"A"),sqlHandler.getCompletePara(Integer.parseInt(view),"E"),sqlHandler.getCompletePara(Integer.parseInt(view),"U"));
            }else{
                adopter=new NewVersionRecyclerAdopter2(this,sqlHandler.getSurah(position,"A"),sqlHandler.getSurah(position,"E"),sqlHandler.getSurah(position,"U"));
            }

        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adopter);

    }
}
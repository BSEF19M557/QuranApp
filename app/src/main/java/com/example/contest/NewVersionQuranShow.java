package com.example.contest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class NewVersionQuranShow extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_version_quran_show);

        recyclerView=findViewById(R.id.recyclerview);
        SqlHandler sqlHandler=new SqlHandler(this);

        Intent intent=getIntent();
        String buttonName=intent.getStringExtra("button");

        NewVersionRecycleAdopter adopter=null;

        if(buttonName.equals("surah")){
            adopter=new NewVersionRecycleAdopter(sqlHandler.getAllSurahName().Urdu,this,buttonName);
        }
        else if(buttonName.equals("para")){
            adopter=new NewVersionRecycleAdopter(sqlHandler.getAllParaName(),this,buttonName);
        }
        else{
            intent = new Intent(this, NewVersionComQuran.class);
            intent.putExtra("view","Quran");
            startActivity(intent);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adopter);


    }
}
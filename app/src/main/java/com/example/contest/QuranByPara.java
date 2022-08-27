package com.example.contest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ListAdapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuranByPara extends AppCompatActivity {

    ListView listViewPara;
    SearchView ParaSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_by_para);

        listViewPara=findViewById(R.id.listViewPara);
        ParaSearchView=findViewById(R.id.paraSearchView);


        listViewPara.setOnItemClickListener((adapterView, view, i, l) -> {
            String paraNumber = adapterView.getItemAtPosition(i).toString();
            Intent intent = new Intent(QuranByPara.this, paraActivity.class);
            intent.putExtra("paraNumber", paraNumber);
            QuranByPara.this.startActivity(intent);

        });

        SqlHandler sqlHandler=new SqlHandler(this);
        ArrayList allParaName=sqlHandler.getAllParaName();

        ArrayAdapter listViewParaAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, allParaName){
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);

                tv.setTypeface(Typeface.createFromAsset(getAssets(),"noorehuda.ttf"));

                // Return the view
                return view;
            }
        };
        listViewPara.setAdapter(listViewParaAdapter);


        ParaSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(allParaName.contains(query)){
                    listViewParaAdapter.getFilter().filter(query);
                }else{
                    Toast.makeText(QuranByPara.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listViewParaAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }
}
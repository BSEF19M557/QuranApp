package com.example.contest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView listViewSurahs;
    SearchView searchView;
    ArrayList<String> allSurahName;
    ArrayAdapter<String> listViewSurahsAdapter;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listViewSurahs = findViewById(R.id.listViewSurahs);
        searchView = findViewById(R.id.searchView);

        SqlHandler sqlHandler=new SqlHandler(this);
        EngUrduNames engUrduNames= sqlHandler.getAllSurahName();


        listViewSurahs.setOnItemClickListener((adapterView, view, position, id) -> {
            String suratName = adapterView.getItemAtPosition(position).toString();
            Intent intent = new Intent(MainActivity.this, SurahActivity.class);

            //surahnumber
            int poz=engUrduNames.Eng.indexOf(suratName);
            //urdu name of surah
            suratName=engUrduNames.Urdu.get(poz).toString();

            intent.putExtra("suratName", suratName);
            intent.putExtra("suratPosition", poz);
            MainActivity.this.startActivity(intent);
        }
        );


        allSurahName= engUrduNames.Eng;

        listViewSurahsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, allSurahName){
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);

                tv.setTypeface(Typeface.createFromAsset(getAssets(),"tnr.ttf"));

                // Return the view
                return view;
            }
        };



        listViewSurahs.setAdapter(listViewSurahsAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(allSurahName.contains(query)){
                    listViewSurahsAdapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    listViewSurahsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.arabicQuran:
                    Intent intent = new Intent(MainActivity.this, QuranInArabic.class);
                    intent.putExtra("type","A");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.engQuran:
                    intent = new Intent(MainActivity.this, QuranInArabic.class);
                    intent.putExtra("type","E");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.urduQuran:
                    intent = new Intent(MainActivity.this, QuranInArabic.class);
                    intent.putExtra("type","U");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.quranWithUrdu:
                    intent = new Intent(MainActivity.this, ArabicWithUrduEng.class);
                    intent.putExtra("type","U");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.quranWithEng:
                    intent = new Intent(MainActivity.this, ArabicWithUrduEng.class);
                    intent.putExtra("type","E");
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.paraQuran:
                    intent = new Intent(MainActivity.this, QuranByPara.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.new_version:
                    intent = new Intent(MainActivity.this, NewVersionMainActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.nav_return:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }

            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
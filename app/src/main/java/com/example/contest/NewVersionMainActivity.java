package com.example.contest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class NewVersionMainActivity extends AppCompatActivity implements View.OnClickListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    Button Sb,Pb,Qb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_version_main);

        Sb=findViewById(R.id.surahButton);
        Sb.setOnClickListener(this);

        Pb=findViewById(R.id.paraButton);
        Pb.setOnClickListener(this);

        Qb=findViewById(R.id.quranButton);
        Qb.setOnClickListener(this);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout2);
        navigationView=findViewById(R.id.nav_view2);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(item -> {
            FontAndTranslationOnOff set=new FontAndTranslationOnOff();

            switch (item.getItemId()) {
                case R.id.nav_sEng:
                    if(set.isEng()){
                        set.setEng(false);
                        item.setTitle("Turn on English");
                    }
                    else {
                        set.setEng(true);
                        item.setTitle("Turn off English");
                    }
                    break;
                case R.id.nav_sUrdu:
                    if(set.isUrdu()){
                        set.setUrdu(false);
                        item.setTitle("Turn on Urdu");
                    }
                    else {
                        set.setUrdu(true);
                        item.setTitle("Turn off Urdu");
                    }
                    break;
                case R.id.nva_font:
                    if(set.getFontSize()==30){
                        set.setFontSize("large");
                        item.setTitle("Font size Large");
                    }
                    else if(set.getFontSize()==40) {
                        set.setFontSize("small");
                        item.setTitle("Font size small");
                    }
                    else {
                        set.setFontSize("normal");
                        item.setTitle("Font size Normal");
                    }
                    break;

                case R.id.nav_return:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }

            return true;
        });
    }
//navigation toggle code
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.surahButton:
                Intent intent = new Intent(this, NewVersionQuranShow.class);
                intent.putExtra("button","surah");
                startActivity(intent);
                break;
            case R.id.paraButton:
                intent = new Intent(this, NewVersionQuranShow.class);
                intent.putExtra("button","para");
                startActivity(intent);
                break;
            case R.id.quranButton:
                intent = new Intent(this, NewVersionQuranShow.class);
                intent.putExtra("button","Quran");
                startActivity(intent);
                break;
        }
    }
}
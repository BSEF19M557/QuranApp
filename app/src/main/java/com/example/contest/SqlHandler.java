package com.example.contest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class SqlHandler extends SQLiteAssetHelper {

    public static final String databaseName="data.sqlite";
    public static final int databaseVersion=1;

    public SqlHandler(Context context){
        super(context,databaseName,null,databaseVersion);
    }

    public EngUrduNames getAllSurahName() {

        EngUrduNames engUrduNames;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT SurahNameE,SurahNameU FROM tsurah ", null);

        ArrayList<String> SurahArrayListE = new ArrayList<>();
        ArrayList<String> SurahArrayListU = new ArrayList<>();
        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                String name=cursorCourses.getString(0);
                int startIndex = name.indexOf("(");
                int endIndex = name.indexOf(")");
                String toBeReplaced = name.substring(startIndex, endIndex+1);
                name=name.replace(toBeReplaced,"");

                SurahArrayListE.add(name);
                SurahArrayListU.add(cursorCourses.getString(1));
            } while (cursorCourses.moveToNext());

        }
        engUrduNames=new EngUrduNames(SurahArrayListE,SurahArrayListU);
        cursorCourses.close();
        return engUrduNames;
    }

    public ArrayList<String> getAllParaName() {

        ArrayList<String> paraNumber = new ArrayList<>();
        for(int i=0;i<=30;i++){
            paraNumber.add(String.valueOf(i));
        }

        return paraNumber;
    }



    public ArrayList<String> getSurah(int surahIndex) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT [Arabic Text] FROM tayah WHERE SuraID='"+surahIndex+"' ORDER BY AyaID", null);

        ArrayList<String> SurahArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {

                SurahArrayList.add(cursorCourses.getString(0));
            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return SurahArrayList;
    }

    public ArrayList<String> getCompletePara(int paraIndex) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT [Arabic Text] FROM tayah WHERE ParaID='"+paraIndex+"' ORDER BY AyaID", null);

        ArrayList<String> SurahArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {

                SurahArrayList.add(cursorCourses.getString(0));
            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return SurahArrayList;
    }

    public ArrayList<String> getQuran(String type) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses=null;
        if(type.equals("A"))
             cursorCourses = db.rawQuery("SELECT [Arabic Text] FROM tayah ORDER BY AyaID", null);
        else if(type.equals("E"))
            cursorCourses = db.rawQuery("SELECT [Mufti Taqi Usmani] FROM tayah ORDER BY AyaID", null);
        else
            cursorCourses = db.rawQuery("SELECT [Mehmood ul Hassan] FROM tayah ORDER BY AyaID", null);




        ArrayList<String> SurahArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {

                SurahArrayList.add(cursorCourses.getString(0));
            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return SurahArrayList;
    }

    public ArrayList<ArabicWithTranslation> getArabicUrduOrEng(String type){

        ArrayList Arabic=getQuran("A");
        ArrayList rTranslation=getQuran(type);

        ArrayList<ArabicWithTranslation> arabicEngUrdu=new ArrayList<>();

        int index=0;
        while (Arabic.size()>index ||rTranslation.size()>index){
            ArabicWithTranslation e=new ArabicWithTranslation(Arabic.get(index).toString(),rTranslation.get(index).toString());
            arabicEngUrdu.add(e);
            index++;
        }


        return arabicEngUrdu;
    }

}

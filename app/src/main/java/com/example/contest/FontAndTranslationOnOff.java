package com.example.contest;

public class FontAndTranslationOnOff {
    private static boolean urdu=true,eng=true;
    private  static int fontSize=30;


    public  boolean isUrdu() {
        return urdu;
    }

    public  void setUrdu(boolean urdu) {
        FontAndTranslationOnOff.urdu = urdu;
    }

    public  boolean isEng() {
        return eng;
    }

    public void setEng(boolean eng) {
        FontAndTranslationOnOff.eng = eng;
    }

    public  int getFontSize() {
        return fontSize;
    }

    public  void setFontSize(String size) {
        if(size.equals("small"))
            FontAndTranslationOnOff.fontSize = 20;
        else if(size.equals("normal"))
            FontAndTranslationOnOff.fontSize = 30;
        else
            FontAndTranslationOnOff.fontSize = 40;
    }
}

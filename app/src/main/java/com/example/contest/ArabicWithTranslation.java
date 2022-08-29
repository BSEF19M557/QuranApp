package com.example.contest;

public class ArabicWithTranslation {
    String arabic;
    String translation;

    public ArabicWithTranslation(String arabic, String translation) {
        this.arabic = arabic;
        this.translation = translation;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}

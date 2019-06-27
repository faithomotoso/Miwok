package com.example.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mMediaResourceID;


    // constructor
    // @param miwokTranslation is the word in Miwok language
    // @param defaultTranslation is the word in the users default language
    // @param imageResourceId is the drawable resource ID for the image asset

    public Word(String defaultTranslation, String miwokTranslation, int ImageResourceId, int mediaResourceID){
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResourceId = ImageResourceId;
        this.mMediaResourceID = mediaResourceID;
    }

    public Word(String defaultTranslation, String miwokTranslation, int mediaResourceID){
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mMediaResourceID = mediaResourceID;
    }

    // get default word
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    // get miwok word
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() { return mImageResourceId;}

    public boolean hasImage() { return  mImageResourceId != NO_IMAGE_PROVIDED;}

    public int getMediaResourceID() { return mMediaResourceID; }
}

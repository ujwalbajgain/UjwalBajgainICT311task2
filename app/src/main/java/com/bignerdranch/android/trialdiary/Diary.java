package com.bignerdranch.android.trialdiary;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ujwalbajgain on 12/9/17.
 */

public class Diary
{
    private UUID mId;

    public UUID getId() {
        return mId;
    }
    private String mType;
    public String getType() {
        return mType;
    }
    public void setType(String type) { mType = type; }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }
    public String getPlace(){
        return mPlace;
    }
    public void setPlace(String place){
        mPlace = place;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
    public String getComments() {
        return mComments;
     }

    public void setComments(String comments){
        mComments= comments;

    }

    private String mTitle;
    private Date mDate;
    private String mComments;
    private String mPlace;


    public Diary(){
        this(UUID.randomUUID());
    }


    public Diary(UUID id){
        mId = id;
        mDate = new Date();


    }
    public String getPhotoFileName(){
        return "IMG_" +getId().toString() + ".jpg";
    }
}

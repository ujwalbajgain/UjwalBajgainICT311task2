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
    public String getComment() {
        return mComment;
     }

    public void setComment(String comment){
        mComment= comment;

    }

    private String mTitle;
    private Date mDate;
    private String mComment;
    private String mPlace;


    public Diary(){
        this(UUID.randomUUID());
    }


    public Diary(UUID id){
        mId = id;
        mDate = new Date();


    }
}

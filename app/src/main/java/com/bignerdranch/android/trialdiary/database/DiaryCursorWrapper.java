package com.bignerdranch.android.trialdiary.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.trialdiary.Diary;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ujwalbajgain on 6/10/17.
 */

public class DiaryCursorWrapper extends CursorWrapper {
    public DiaryCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Diary getDiary() {
        String uuidString = getString(getColumnIndex(DiaryDbSchema.DiaryTable.Cols.UUID));
        String title = getString(getColumnIndex(DiaryDbSchema.DiaryTable.Cols.TITLE));
        long date = getLong(getColumnIndex(DiaryDbSchema.DiaryTable.Cols.DATE));
        //String comment = getString(getColumnIndex(DiaryDbSchema.DiaryTable.Cols.COMMENT));
        String place = getString(getColumnIndex(DiaryDbSchema.DiaryTable.Cols.PLACE));


        Diary diary = new Diary(UUID.fromString(uuidString));
        diary.setTitle(title);
        diary.setDate(new Date(date));
        //diary.setComment(comment);
        diary.setPlace(place);


        return diary;
    }
}


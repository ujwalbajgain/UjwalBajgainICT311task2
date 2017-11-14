package com.bignerdranch.android.trialdiary.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.trialdiary.Diary;
import com.bignerdranch.android.trialdiary.Settings;

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
        String comments = getString(getColumnIndex(DiaryDbSchema.DiaryTable.Cols.COMMENTS));
        String place = getString(getColumnIndex(DiaryDbSchema.DiaryTable.Cols.PLACE));


        Diary diary = new Diary(UUID.fromString(uuidString));
        diary.setTitle(title);
        diary.setDate(new Date(date));
        diary.setComments(comments);
        diary.setPlace(place);


        return diary;
    }

    public Settings getSettings() {
        String id = getString(getColumnIndex(DiaryDbSchema.SettingsTable.Cols.ID));
        String name = getString(getColumnIndex(DiaryDbSchema.SettingsTable.Cols.NAME));
        String email = getString(getColumnIndex(DiaryDbSchema.SettingsTable.Cols.EMAIL));
        String gender = getString(getColumnIndex(DiaryDbSchema.SettingsTable.Cols.GENDER));
        String comment = getString(getColumnIndex(DiaryDbSchema.SettingsTable.Cols.COMMENT));

        Settings settings = new Settings();
        settings.setId(id);
        settings.setName(name);
        settings.setEmail(email);
        settings.setGender(gender);
        settings.setComment(comment);

        return settings;
    }
}


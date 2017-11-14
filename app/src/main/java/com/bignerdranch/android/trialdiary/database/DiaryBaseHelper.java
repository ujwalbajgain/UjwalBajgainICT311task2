package com.bignerdranch.android.trialdiary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.trialdiary.database.DiaryDbSchema.DiaryTable;
import com.bignerdranch.android.trialdiary.database.DiaryDbSchema.SettingsTable;

import static com.bignerdranch.android.trialdiary.database.DiaryDbSchema.SettingsTable.*;

/**
 * Created by ujwalbajgain on 6/10/17.
 */

public class DiaryBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "diaryBase.db";

    public DiaryBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + DiaryTable.NAME + "("
                + " _id integer primary key autoincrement, " +
        DiaryTable.Cols.UUID + ", "+
        DiaryTable.Cols.TITLE + ", " +
        DiaryTable.Cols.DATE + ", " +
        DiaryTable.Cols.PLACE + ", " +
        DiaryTable.Cols.COMMENTS +
                        ")"
        );

        db.execSQL("create table " + NAME + "(" +
                Cols.ID + ", " +
                Cols.NAME + ", " +
                Cols.EMAIL + ", " +
                Cols.GENDER + ", " +
                Cols.COMMENT +
                ")"
        );

        db.execSQL("insert into " + NAME + " values ('22222', 'Thungsuk Wangdu', 'wangdu@umail.com', '2' , 'No comments')");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}

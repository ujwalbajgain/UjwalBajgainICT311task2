package com.bignerdranch.android.trialdiary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.trialdiary.database.DiaryDbSchema.DiaryTable;

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
        DiaryTable.Cols.PLACE + ")"
//        DiaryTable.Cols.COMMENT + ")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}

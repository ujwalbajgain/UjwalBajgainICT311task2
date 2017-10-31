package com.bignerdranch.android.trialdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.trialdiary.database.DiaryBaseHelper;
import com.bignerdranch.android.trialdiary.database.DiaryCursorWrapper;
import com.bignerdranch.android.trialdiary.database.DiaryDbSchema.DiaryTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ujwalbajgain on 13/9/17.
 */

public class DiaryLab {
    private static DiaryLab sDiaryLab;


    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static DiaryLab get (Context context){
        if (sDiaryLab == null){
            sDiaryLab = new DiaryLab(context);
        }
        return sDiaryLab;
    }
    private DiaryLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DiaryBaseHelper(mContext)
                .getWritableDatabase();



    }

    public void addDiary(Diary d){
        ContentValues values = getContentValues(d);
        mDatabase.insert(DiaryTable.NAME, null, values);

    }

    public  List<Diary> getDiaries(){
        List<Diary> diaries = new ArrayList<>();

        DiaryCursorWrapper cursor = queryDiaries(null ,null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                diaries.add(cursor.getDiary());
                cursor.moveToNext();
            }
            } finally {
                cursor.close();
            }
            return diaries;
        }



    public Diary getDiary(UUID id){
        DiaryCursorWrapper cursor = queryDiaries(
                DiaryTable.Cols.UUID + " = ?",
                new String[]{id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getDiary();

        } finally {
            cursor.close();

        }
    }

    public void updateDiary( Diary diary){
        String uuidString = diary.getId().toString();
        ContentValues values = getContentValues(diary);

        mDatabase.update(DiaryTable.NAME, values,
                DiaryTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private DiaryCursorWrapper queryDiaries(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                DiaryTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null

        );
        return new DiaryCursorWrapper(cursor);

    }

    private static ContentValues getContentValues(Diary diary){
        ContentValues values = new ContentValues();
        values.put(DiaryTable.Cols.UUID, diary.getId().toString());
        values.put(DiaryTable.Cols.TITLE, diary.getTitle());
        values.put(DiaryTable.Cols.DATE, diary.getDate().getTime());
        //values.put(DiaryTable.Cols.COMMENT, diary.getComment().toString());
        //values.put(DiaryTable.Cols.PLACE, diary.getPlace().toString());


        return values;

    }


}

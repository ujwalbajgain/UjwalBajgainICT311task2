package com.bignerdranch.android.trialdiary.database;

/**
 * Created by ujwalbajgain on 6/10/17.
 */

public class DiaryDbSchema {
    public static final class DiaryTable{
        public static final String NAME = "diaries";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            //public static final String COMMENT = "comment";
            public static final String PLACE = "place";

        }

    }
}

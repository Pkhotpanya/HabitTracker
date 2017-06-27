package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by pkhotpanya on 6/27/17.
 */

public final class HabitContract {

    public static abstract class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_DESCRIPTION = "description";
        public static final String COLUMN_HABIT_DATETIME = "datetime";

    }

}

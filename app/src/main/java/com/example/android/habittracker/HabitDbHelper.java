package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.HabitContract.HabitEntry;

import static android.R.attr.version;

/**
 * Created by pkhotpanya on 6/27/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tracker.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, " + HabitEntry.COLUMN_HABIT_DESCRIPTION + " TEXT, " + HabitEntry.COLUMN_HABIT_DATETIME + " INTEGER NOT NULL);";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

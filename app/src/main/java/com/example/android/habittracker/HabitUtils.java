package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.habittracker.HabitContract.*;

/**
 * Created by pkhotpanya on 6/27/17.
 */

public class HabitUtils {

    public static void insertHabit(Context context, String name, String description, Integer datetime) {
        HabitDbHelper dbHelper = new HabitDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_DESCRIPTION, description);
        values.put(HabitEntry.COLUMN_HABIT_DATETIME, datetime);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(context, "Error with saving habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Habit saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    public static Cursor getHabitCursor(Context context) {
        HabitDbHelper dbHelper = new HabitDbHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DESCRIPTION,
                HabitEntry.COLUMN_HABIT_DATETIME
        };

        String selection = HabitEntry.COLUMN_HABIT_NAME + " = ?";
        String[] selectionArgs = {"*"};

        String sortOrder = HabitEntry.COLUMN_HABIT_DATETIME + " DESC";

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,                      // Table
                projection,                                       // Returned column
                null,                                       // WHERE clause tables
                null,                                       // WHERE clause values
                null,
                null,
                null                                        // Sort order
        );

        return cursor;
    }

    public static String readHabitCursor(Cursor cursor){
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Number of rows in habit database table: " + cursor.getCount() + "\n");

            sb.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_DESCRIPTION + " - " +
                    HabitEntry.COLUMN_HABIT_DATETIME + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int descriptionColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DESCRIPTION);
            int datetimeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATETIME);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDescription = cursor.getString(descriptionColumnIndex);
                int currentDatetime = cursor.getInt(datetimeColumnIndex);

                sb.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentDescription + " - " +
                        currentDatetime));
            }
            Log.v("HabitUtil.java", sb.toString());
            return sb.toString();
        } catch (Exception e){
            Log.e("HabitUtil.java", "Exception: " + e.toString());
        } finally {
            if(cursor != null){
                cursor.close();
            }
        }
        return null;
    }

}

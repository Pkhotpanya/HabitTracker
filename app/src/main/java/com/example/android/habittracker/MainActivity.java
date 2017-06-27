package com.example.android.habittracker;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtVw = (TextView) findViewById(R.id.textview_debug);

        Cursor crsr = HabitUtils.getHabitCursor(this);
        String strng = HabitUtils.readHabitCursor(crsr);
        txtVw.setText(strng);
    }
}

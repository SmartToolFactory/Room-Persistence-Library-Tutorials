package com.example.tutorial3superclassentities;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorial3superclassentities.data.source.local.MeasurementAnglemeterDao;
import com.example.tutorial3superclassentities.data.source.local.MeasurementBubbleLevelDao;
import com.example.tutorial3superclassentities.data.source.local.MeasurementDatabase;
import com.example.tutorial3superclassentities.data.source.model.MeasurementAnglemeter;
import com.example.tutorial3superclassentities.data.source.model.MeasurementBubbleLevel;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv_measurement);


        MeasurementDatabase measurementDatabase = Room.databaseBuilder(getApplicationContext(), MeasurementDatabase.class, MeasurementDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        MeasurementAnglemeterDao measurementAnglemeterDao = measurementDatabase.measurementAnglemeterDao();
        MeasurementBubbleLevelDao measurementBubbleLevelDao = measurementDatabase.measurementBubbleLevelDao();

        // Add Dummy Measurements
        MeasurementAnglemeter measurementAnglemeter = new MeasurementAnglemeter();
        measurementAnglemeter.setAngleSingle(12);
        measurementAnglemeter.setTitle("Title1");
        measurementAnglemeter.setNote("Note1");
        measurementAnglemeter.setDate(System.currentTimeMillis());
        measurementAnglemeterDao.insert(measurementAnglemeter);
        measurementAnglemeter.setAngleSingle(22);
        measurementAnglemeter.setTitle("Title2");
        measurementAnglemeter.setNote("Note2");
        measurementAnglemeter.setDate(System.currentTimeMillis());
        measurementAnglemeterDao.insert(measurementAnglemeter);
        measurementAnglemeter.setAngleSingle(32);
        measurementAnglemeter.setTitle("Title3");
        measurementAnglemeter.setNote("Note3");
        measurementAnglemeter.setDate(System.currentTimeMillis());
        measurementAnglemeterDao.insert(measurementAnglemeter);

        MeasurementBubbleLevel measurementBubbleLevel = new MeasurementBubbleLevel();
        measurementBubbleLevel.setTitle("Bubble Example");
        measurementBubbleLevel.setNote("Bubble note");
        measurementBubbleLevel.setAngleHorizontal(4);
        measurementBubbleLevel.setAngleVertical(30);
        measurementBubbleLevel.setDate(System.currentTimeMillis());
        measurementBubbleLevelDao.insert(measurementBubbleLevel);



        List<MeasurementBubbleLevel> measurementListAnglemeter = measurementBubbleLevelDao.getAll();
        StringBuilder stringBuilder = new StringBuilder();
        if (measurementListAnglemeter != null && measurementListAnglemeter.size() > 0) {
            for (MeasurementBubbleLevel measurement : measurementListAnglemeter) {
                stringBuilder.append("Title: " + measurement.getTitle() + ", angle: " + measurement.getAngleHorizontal() + ", note: " + measurement.getNote() + ", date: " + measurement.getFormattedDate() + "\n");
            }
            textView.setText(stringBuilder.toString());
        }
    }



}

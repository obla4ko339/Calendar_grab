package com.delta.calendarevent;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class Viewcalendar extends AppCompatActivity {

    CalendarView viewCalendarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcalendar);

        viewCalendarId = (CalendarView) findViewById(R.id.viewCalendarId);
        viewCalendarId.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Intent PagelistCalendarEvent = new Intent(Viewcalendar.this, Listcalendarevent.class);

                PagelistCalendarEvent.putExtra("dayOfMonth", dayOfMonth);
                PagelistCalendarEvent.putExtra("month", month);
                PagelistCalendarEvent.putExtra("year", year);

                startActivity(PagelistCalendarEvent);

            }
        });

    }
}

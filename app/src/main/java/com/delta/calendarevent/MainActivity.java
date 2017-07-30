package com.delta.calendarevent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Button BTCalendatEvent;
    Button BTPortalkulturaTo;

    String hrefPortal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // переход на портал культуры kultura-to.ru
        BTPortalkulturaTo = (Button) findViewById(R.id.BTPortalkulturaTo);
        BTPortalkulturaTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrefPortal = "http://kultura-to.ru";
                Uri uri = Uri.parse(hrefPortal);
                Intent portalLinkIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(portalLinkIntent);

            }
        });

        BTCalendatEvent = (Button) findViewById(R.id.BTCalendatEvent);
        BTCalendatEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCalendar = new Intent(MainActivity.this, Viewcalendar.class);
                startActivity(intentCalendar);
            }
        });

    }
}

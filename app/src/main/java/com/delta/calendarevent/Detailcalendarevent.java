package com.delta.calendarevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Detailcalendarevent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailcalendarevent);

        String title = getIntent().getStringExtra("text");

        Toast.makeText(Detailcalendarevent.this, "title "+ title, Toast.LENGTH_LONG).show();

    }
}

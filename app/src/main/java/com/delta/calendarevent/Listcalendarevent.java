package com.delta.calendarevent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Listcalendarevent extends AppCompatActivity {

    TextView testText;

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    JSONObject jsonObjectnew = null;
    JSONObject frd;

    List<DataCalendarEvent> dataCalendarEventsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        ParseTask parseTask = new ParseTask();
        parseTask.execute();

        try {
            jsonObjectnew = new JSONObject(parseTask.get());
            JSONArray jsonArraynew = jsonObjectnew.getJSONArray("friends");

            for(int i =0; i<jsonArraynew.length(); i++){
                frd = jsonArraynew.getJSONObject(i);
                DataCalendarEvent dataCalendarEvent = new DataCalendarEvent(frd.getString("name"),frd.getString("city"),frd.getString("city"),frd.getString("id") );
                dataCalendarEventsList.add(dataCalendarEvent);
            }







        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        int dayOfMonth = getIntent().getIntExtra("dayOfMonth",0);
        int month = getIntent().getIntExtra("month",0);
        int year = getIntent().getIntExtra("year",0);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        String[] data = new String[]{"Gthdsq","Dnjhjq"};
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(dataCalendarEventsList, Listcalendarevent.this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }

    private class ParseTask extends AsyncTask<Void, Void, String>{

        HttpURLConnection urlConnection;
        BufferedReader reader;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL("http://androiddocs.ru/api/friends.json");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = reader.readLine()) != null){
                    stringBuffer.append(line);
                }

                resultJson = stringBuffer.toString();



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return resultJson;
        }

        @Override
        protected void onPostExecute(String resultJson) {
            super.onPostExecute(resultJson);

            JSONObject jsonObject = null;
            String name = "";

            try {
                jsonObject = new JSONObject(resultJson);

                JSONArray frinds = jsonObject.getJSONArray("friends");

                for(int i = 0; i<frinds.length(); i++){
                    JSONObject frind = frinds.getJSONObject(i);
                    Log.d("MYLOG", frind .getString("name"));
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}

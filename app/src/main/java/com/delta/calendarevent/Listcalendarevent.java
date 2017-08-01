package com.delta.calendarevent;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
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

    int dayOfMonth;
    int month;
    int year;
    String dayOfMonthString;

    List<DataCalendarEvent> dataCalendarEventsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        dayOfMonth = getIntent().getIntExtra("dayOfMonth",0);
        month = getIntent().getIntExtra("month",0)+1;
        year = getIntent().getIntExtra("year",0);

        if(dayOfMonth >= 1 && dayOfMonth<=9 ){
            dayOfMonthString = "0"+dayOfMonth;
        }else{
            dayOfMonthString = dayOfMonth+"";
        }


        ParseTask parseTask = new ParseTask();
        parseTask.execute();

        try {
            jsonObjectnew = new JSONObject(parseTask.get());
            JSONArray jsonArraynew = jsonObjectnew.getJSONArray("event");

            for(int i =0; i<jsonArraynew.length(); i++){
                frd = jsonArraynew.getJSONObject(i);
                DataCalendarEvent dataCalendarEvent = new DataCalendarEvent(frd.getString("title"),frd.getString("shortdesc"),frd.getString("type"),frd.getString("startdate") );
                dataCalendarEventsList.add(dataCalendarEvent);
            }







        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




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
        URL url;

        @Override
        protected String doInBackground(Void... params) {
            Log.d("DAY_MONTH_YEAR", "ura "+dayOfMonth+" "+month+" "+year);

            Log.d("URL", "http://kultura-to.ru/mjson.php?datepost="+year+"-"+month+"-"+dayOfMonthString+"");
            try {
                    url = new URL("http://kultura-to.ru/mjson.php?datepost="+year+"-"+month+"-"+dayOfMonthString+"");


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

            Log.d("MYJSON", resultJson);

            JSONObject jsonObject = null;
            String name = "";

            try {
                jsonObject = new JSONObject(resultJson);

                JSONArray event = jsonObject.getJSONArray("event");
                event.getJSONObject(1).getString("title");

                for(int i = 0; i<event.length(); i++){
                    JSONObject frind = event.getJSONObject(i);
                    Log.d("MYLOG", frind.getString("title"));
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}

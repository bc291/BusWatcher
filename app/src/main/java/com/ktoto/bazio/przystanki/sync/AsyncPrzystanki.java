package com.ktoto.bazio.przystanki.sync;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ktoto.bazio.przystanki.DisplayList;
import com.ktoto.bazio.przystanki.MainActivity;
import com.ktoto.bazio.przystanki.R;
import com.ktoto.bazio.przystanki.dane.Delay_main;
import com.ktoto.bazio.przystanki.dane.Display_main;
import com.ktoto.bazio.przystanki.dane.Przystanki;
import com.ktoto.bazio.przystanki.dane.Przystanki_main;
import com.ktoto.bazio.przystanki.dane.namesAndValues;
import com.ktoto.bazio.przystanki.database.db;
import com.ktoto.bazio.przystanki.details.DetailsDisplayList;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.nio.charset.Charset;


public class AsyncPrzystanki extends AsyncTask<URL, Integer, Przystanki_main> {

    Activity wywolujaceActivity;
    Context ctx;
    ProgressBar bar;
    WeakReference<Activity> mWeakActivity;

    public void setProgressBar(ProgressBar bar) {
        this.bar = bar;
    }

    public AsyncPrzystanki(Activity wywolujaceActivity) {

        this.wywolujaceActivity = wywolujaceActivity;
        ctx = wywolujaceActivity.getApplicationContext();
        mWeakActivity = new WeakReference<Activity>(wywolujaceActivity);
    }



    @Override
    protected void onPreExecute() {

    }
    //URL... urls
    @Override
    protected Przystanki_main doInBackground(URL... urls) {
        Przystanki_main dto = null;
        InputStream raw = wywolujaceActivity.getResources().openRawResource(R.raw.baza2);
        Reader rd = new BufferedReader(new InputStreamReader(raw));

        dto = new Gson().fromJson(rd, Przystanki_main.class);

        db database = new db(ctx);
        SQLiteDatabase dbb = database.getWritableDatabase();

        db.dropTable(dbb);

        String countQuery = "SELECT  * FROM " + namesAndValues.dane.STOPS_TABLE_NAME;
        Cursor cursor22 = dbb.rawQuery(countQuery, null);
        int cnt = cursor22.getCount();
        cursor22.close();

        if (cnt == 0) {

            inserting(dto, database, dbb);
        }


        return null;
    }






    public void inserting(Przystanki_main dto, db database, SQLiteDatabase dbb)
    {
        int count = 0;

        while (count < dto.getStops().size())
        {
            if(dto.getStops().get(count).getZoneName() != null && !dto.getStops().get(count).getZoneName().isEmpty()){
             if (dto.getStops().get(count).getZoneName().equals("Gdańsk")) {

                database.putInformationPrzystanki(dbb, dto.getStops().get(count).getStopDesc(),
                        dto.getStops().get(count).getStopId(),
                        dto.getStops().get(count).getStopLat(),
                        dto.getStops().get(count).getStopLon(),
                        dto.getStops().get(count).getZoneName());
            }
        }
            count++;
            publishProgress(count*100/dto.getStops().size() );

        }

       if(!database.isTableExists("tmp_stops", false, dbb)) {
            database.test(dbb);
        }
        database.close();
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        Activity activity = mWeakActivity.get();
        if (this.bar != null) {
            bar.setProgress(values[0]);
        }

        if (activity != null) {
            ProgressBar progressBar = activity.findViewById(R.id.progressBar);
            TextView textView = activity.findViewById(R.id.textView12);
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onPostExecute(Przystanki_main dto) {

    }
}

package com.ktoto.bazio.przystanki.sync;

/**
 * Created by bazio on 02.11.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.ktoto.bazio.przystanki.dane.Display;
import com.ktoto.bazio.przystanki.dane.Display_main;
import com.ktoto.bazio.przystanki.dane.namesAndValues;
import com.ktoto.bazio.przystanki.database.db;

import java.io.IOException;
import java.io.InputStreamReader;


import java.net.URL;

public class AsyncStops extends AsyncTask<URL, Void, Display_main> {


    Activity wywolujaceActivity;
    Context ctx;



    public AsyncStops(Activity wywolujaceActivity) {

        this.wywolujaceActivity = wywolujaceActivity;
       ctx = wywolujaceActivity.getApplicationContext();

    }



    @Override
    protected void onPreExecute() {

    }
//URL... urls
    @Override
    protected Display_main doInBackground(URL... urls) {

        Display_main dto = null;
        URL url = null;
        InputStreamReader reader2 = null;
        try {
            url = new URL("http://91.244.248.19/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/25e0de48-1acd-4ee5-9bc5-45cb5a55b32f/download/displays.json");
            reader2 = new InputStreamReader(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dto = new Gson().fromJson(reader2, Display_main.class);



        db database = new db(ctx);
        SQLiteDatabase dbb = database.getWritableDatabase();


        String countQuery = "SELECT  * FROM " + namesAndValues.dane.STOPS_TABLE_NAME;
        Cursor cursor22 = dbb.rawQuery(countQuery, null);
        int cnt = cursor22.getCount();
        cursor22.close();

        if(cnt==0) inserting(dto, database, dbb);


        return null;




    }

    @Override
    protected void onPostExecute(Display_main dto) {

    }

    public void inserting(Display_main dto, db database, SQLiteDatabase dbb)
    {





        int nowe_id = 1;

        database.putInformation(dbb, dto.getDisplays().get(0).getName(),
                dto.getDisplays().get(0).getIdStop1(),
                dto.getDisplays().get(0).getIdStop2(),
                dto.getDisplays().get(0).getIdStop3(),
                dto.getDisplays().get(0).getIdStop4(), nowe_id);


        nowe_id++;
        int count = 1;

        while (count < dto.getDisplays().size())
        // for(int count =1; count<=118;count++)
        {


            //  if(dto.getDisplays().get(count).getName().equals(dto.getDisplays().get(count-1).getName())
            //          && dto.getDisplays().get(count).getIdStop1().equals(dto.getDisplays().get(count-1).getIdStop1())){
            //      Log.d("keresrew", "Podwojny to:"+dto.getDisplays().get(count).getName());
//}
//else{
            String test = "22222";
            if (dto.getDisplays().get(count).getName().equals(dto.getDisplays().get(count - 1).getName())) {

                database.putInformation(dbb, dto.getDisplays().get(count).getName() + test,
                        dto.getDisplays().get(count).getIdStop1(),
                        dto.getDisplays().get(count).getIdStop2(),
                        dto.getDisplays().get(count).getIdStop3(),
                        dto.getDisplays().get(count).getIdStop4(), nowe_id);
                count++;
                nowe_id++;

            } else {
                database.putInformation(dbb, dto.getDisplays().get(count).getName(),
                        dto.getDisplays().get(count).getIdStop1(),
                        dto.getDisplays().get(count).getIdStop2(),
                        dto.getDisplays().get(count).getIdStop3(),
                        dto.getDisplays().get(count).getIdStop4(), nowe_id);
                count++;
                nowe_id++;
            }
        }
        //}

        dbb.execSQL(String.format("DELETE FROM %s WHERE %s = %d",
                namesAndValues.dane.STOPS_TABLE_NAME, namesAndValues.dane.COLUMN_STOPID1, Integer.parseInt("0")));

        // dbb.execSQL("DELETE FROM stops WHERE stopId1 NOT IN (SELECT MIN(stopId1) FROM stops GROUP BY name)");




        if(!database.isTableExists("tmp_stops", false, dbb)) {
            database.test(dbb);
        }
        database.close();


        try {
            // Thread.currentThread();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

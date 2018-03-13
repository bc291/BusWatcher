package com.ktoto.bus.przystanki.sync;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ktoto.bus.przystanki.dane.Delay_main;
import com.ktoto.bus.przystanki.dane.namesAndValues;
import com.ktoto.bus.przystanki.database.db;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;



public class AsyncDelays extends AsyncTask<URL, Void, Delay_main> {



    Activity wywolujaceActivity;
    Context ctx;

int progress;

    public AsyncDelays(Activity wywolujaceActivity) {

        this.wywolujaceActivity = wywolujaceActivity;
        ctx = wywolujaceActivity.getApplicationContext();

    }



    @Override
    protected void onPreExecute() {

    }
    //URL... urls
    @Override
    protected Delay_main doInBackground(URL... urls) {


        Delay_main dto = null;
        URL url = null;
        InputStreamReader reader2 = null;
        try {
           // url = new URL("http://87.98.237.99:88/delays?stopId=2045");
            url = urls[0];
            reader2 = new InputStreamReader(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dto = new Gson().fromJson(reader2, Delay_main.class);


//        Delay_main dto = null;
//        InputStream raw =  wywolujaceActivity.getResources().openRawResource(R.raw.baza);
//        Reader rd = new BufferedReader(new InputStreamReader(raw));
//
//        dto = new Gson().fromJson(rd, Delay_main.class);

        db database = new db(ctx);
        SQLiteDatabase dbb = database.getWritableDatabase();

        db.dropTable(dbb);

        String countQuery = "SELECT  * FROM " + namesAndValues.dane.DELAYS_TABLE_NAME;
        Cursor cursor22 = dbb.rawQuery(countQuery, null);
        int cnt = cursor22.getCount();
        cursor22.close();

        if(cnt==0) {
            inserting(dto, database, dbb);
        }


        return null;




    }

    @Override
    protected void onPostExecute(Delay_main dto) {

    }

    public void inserting(Delay_main dto, db database, SQLiteDatabase dbb)
    {

        int count = 0;

        while (count < dto.getDelay().size())
        {

                database.putInformationDelays(dbb, dto.getDelay().get(count).getRouteId(),
                        dto.getDelay().get(count).getHeadsign(),
                        dto.getDelay().get(count).getEstimatedTime() );
                count++;


        }

        database.close();


    }

}

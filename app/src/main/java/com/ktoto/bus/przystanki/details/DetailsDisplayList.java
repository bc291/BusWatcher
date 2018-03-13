package com.ktoto.bus.przystanki.details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.ktoto.bus.przystanki.R;
import com.ktoto.bus.przystanki.RecyclerItemClickListener;
import com.ktoto.bus.przystanki.dane.Delay_main;
import com.ktoto.bus.przystanki.dane.SettingsActivity;
import com.ktoto.bus.przystanki.dane.getFromDelays;
import com.ktoto.bus.przystanki.database.db;
import com.ktoto.bus.przystanki.sync.AsyncDelays;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DetailsDisplayList extends AppCompatActivity {
    RecyclerView recyclerView2;
    RecyclerView.Adapter adapter2;
    RecyclerView.LayoutManager layoutManager2;
    ArrayList<getFromDelays> arrayList2 = new ArrayList<>();
    TextView textView15;
    TextView textView14;
    ImageView imageView15;
    FloatingActionButton fab;
    URL url = null;
    public boolean syncing=false;
    Boolean showInMinutes;
    Boolean stopSyncingTask = false;
    Handler timerHandler;
    Runnable timerRunnable;
    String syncingIntervals;
    int syncingIntervalsInt;
    int countingTillSync;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_details);
        Bundle bundle = getIntent().getExtras();
        String stronaJson = bundle.getString("test");
        AndroidThreeTen.init(this);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        showInMinutes =  sharedPreferences.getBoolean("show_minutes",false);
        syncingIntervals = sharedPreferences.getString("list_preference_1", getResources().getString(R.string.def_value_syncing_times));
        syncingIntervalsInt = Integer.parseInt(syncingIntervals);

        refreshRecycler();

        try {
            url = new URL(stronaJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {







                animateFab();
                syncing = true;
                adapter2 = new DetailsRecyclerAdapter(arrayList2, syncing);
                recyclerView2.setAdapter(adapter2);



                AsyncDelays task12 = new AsyncDelays(DetailsDisplayList.this) {
                    protected void onPostExecute(Delay_main dto) {

                        arrayList2.clear();
                        db dbb = new db(DetailsDisplayList.this);
                        SQLiteDatabase sqLiteDatabase = dbb.getReadableDatabase();

                        Cursor cursor = dbb.getInformation2(sqLiteDatabase);
                        cursor.moveToFirst();

                        if(showInMinutes) {

                            if (cursor.getCount() != 0) {
                                fab.setVisibility(View.VISIBLE);
                                do {
                                    Date oldDate = new Date();
                                    String test = LocalDate.now().atTime(LocalTime.parse(cursor.getString(2)))
                                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                    Calendar c = Calendar.getInstance();
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String formattedDate = df.format(c.getTime());

                                    //test = "2017-11-10 00:14:00";
                                    //  formattedDate = "2017-11-10 23:50:00";

                                    try {
                                        Date date1 = df.parse(test);
                                        Date date2 = df.parse(formattedDate);
                                        long diff = date1.getTime() - date2.getTime();

                                        long days = diff / (24 * 60 * 60 * 1000);
                                        diff -= days * (24 * 60 * 60 * 1000);

                                        long hours = diff / (60 * 60 * 1000);
                                        diff -= hours * (60 * 60 * 1000);

                                        long minutes = diff / (60 * 1000);
                                        diff -= minutes * (60 * 1000);

                                        long seconds = diff / 1000;
                                        if (minutes < 0) minutes = minutes * -1;
                                        Log.d("data4", Long.toString(minutes));
                                        getFromDelays getFromDelays = new getFromDelays(cursor.getInt(0), cursor.getString(1), Long.toString(minutes)+" min");
                                        arrayList2.add(getFromDelays);

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }


                                }
                                while (cursor.moveToNext());

                            } else {
                                textView15.setVisibility(View.VISIBLE);
                                textView15.setText("Niestety brak odjazdów z tego przystanku");
                                imageView15.setVisibility(View.VISIBLE);
                                fab.setVisibility(View.INVISIBLE);
                            }

                        }

                        else
                        {

                            if (cursor.getCount() != 0) {
                                fab.setVisibility(View.VISIBLE);
                                do {
                                    getFromDelays getFromDelays = new getFromDelays(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                                    arrayList2.add(getFromDelays);
                                }
                                while (cursor.moveToNext());

                            } else {
                                textView15.setVisibility(View.VISIBLE);
                                textView15.setText("Niestety brak odjazdów z tego przystanku");
                                imageView15.setVisibility(View.VISIBLE);
                                fab.setVisibility(View.INVISIBLE);
                            }

                        }

                        dbb.close();
                        cursor.close();
                        syncing = false;
                        adapter2 = new DetailsRecyclerAdapter(arrayList2, syncing);
                        recyclerView2.setAdapter(adapter2);
                       // adapter2.notifyDataSetChanged();
                        Snackbar.make(getCurrentFocus(), "Zsynchronizowano",500).setAction("Action", null).show();
                    }
                };task12.execute(url);



            }
        });


        textView15 = (TextView) findViewById(R.id.textView15);
        textView15.setVisibility(View.INVISIBLE);
        textView14 = (TextView) findViewById(R.id.textView14);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        layoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setHasFixedSize(true);
        imageView15 = (ImageView) findViewById(R.id.imageView5);
        imageView15.setVisibility(View.INVISIBLE);
        db dbb = new db(this);
        SQLiteDatabase sqLiteDatabase = dbb.getReadableDatabase();

        Cursor cursor = dbb.getInformation2(sqLiteDatabase);

        cursor.moveToFirst();
        if(showInMinutes) {

            if (cursor.getCount() != 0) {
                fab.setVisibility(View.VISIBLE);
                do {
                    String test2 = LocalDate.now().atTime(LocalTime.parse(cursor.getString(2)))
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate2 = df.format(c.getTime());

                    //test = "2017-11-10 00:14:00";
                    //  formattedDate = "2017-11-10 23:50:00";

                    try {
                        Date date11 = df.parse(test2);
                        Date date22 = df.parse(formattedDate2);
                        long diff = date11.getTime() - date22.getTime();

                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);

                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);

                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);

                        long seconds = diff / 1000;
                        if (minutes < 0) minutes = minutes * -1;
                        getFromDelays getFromDelays = new getFromDelays(cursor.getInt(0), cursor.getString(1), Long.toString(minutes)+" min");
                        arrayList2.add(getFromDelays);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }
                while (cursor.moveToNext());

            } else {
                textView15.setVisibility(View.VISIBLE);
                textView15.setText("Niestety brak odjazdów z tego przystanku");
                imageView15.setVisibility(View.VISIBLE);
                fab.setVisibility(View.INVISIBLE);
            }

        }

        else {
            if (cursor.getCount() != 0) {
                fab.setVisibility(View.VISIBLE);
                do {
                    getFromDelays getFromDelays = new getFromDelays(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                    arrayList2.add(getFromDelays);
                }
                while (cursor.moveToNext());

            } else {
                textView15.setVisibility(View.VISIBLE);
                textView15.setText("Niestety brak odjazdów z tego przystanku");
                imageView15.setVisibility(View.VISIBLE);
                fab.setVisibility(View.INVISIBLE);
            }
        }
        dbb.close();
        cursor.close();
        adapter2 = new DetailsRecyclerAdapter(arrayList2, syncing);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView2, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


    }





//    Handler handler = new Handler();
//    // Define the code block to be executed
//    private Runnable runnableCode = new Runnable() {
//        @Override
//        public void run() {
//            getFromDelays getFromDelays = new getFromDelays(0,"test","test5");
//            arrayList2.add(getFromDelays);
//            handler.postDelayed(runnableCode, 1000);
//            adapter2.notifyDataSetChanged();
//        }
//    };

    public void animateFab()
    {
        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(findViewById(R.id.fab));
    }

    @Override
    protected void onPause() {
        super.onPause();
timerHandler.removeCallbacks(timerRunnable);

    }




    public void refreshRecycler()
{
    countingTillSync = syncingIntervalsInt;

    timerHandler = new Handler();

    timerRunnable = new Runnable() {


        @Override
        public void run() {

            syncing = true;
            adapter2 = new DetailsRecyclerAdapter(arrayList2, syncing);
            recyclerView2.setAdapter(adapter2);



            AsyncDelays task12 = new AsyncDelays(DetailsDisplayList.this) {
                protected void onPostExecute(Delay_main dto) {

                    arrayList2.clear();
                    db dbb = new db(DetailsDisplayList.this);
                    SQLiteDatabase sqLiteDatabase = dbb.getReadableDatabase();

                    Cursor cursor = dbb.getInformation2(sqLiteDatabase);
                    cursor.moveToFirst();

                    if(showInMinutes) {

                        if (cursor.getCount() != 0) {
                            fab.setVisibility(View.VISIBLE);
                            do {
                                Date oldDate = new Date();
                                String test = LocalDate.now().atTime(LocalTime.parse(cursor.getString(2)))
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String formattedDate = df.format(c.getTime());

                                //test = "2017-11-10 00:14:00";
                                //  formattedDate = "2017-11-10 23:50:00";

                                try {
                                    Date date1 = df.parse(test);
                                    Date date2 = df.parse(formattedDate);
                                    long diff = date1.getTime() - date2.getTime();

                                    long days = diff / (24 * 60 * 60 * 1000);
                                    diff -= days * (24 * 60 * 60 * 1000);

                                    long hours = diff / (60 * 60 * 1000);
                                    diff -= hours * (60 * 60 * 1000);

                                    long minutes = diff / (60 * 1000);
                                    diff -= minutes * (60 * 1000);

                                    long seconds = diff / 1000;
                                    if (minutes < 0) minutes = minutes * -1;
                                    Log.d("data4", Long.toString(minutes));
                                    getFromDelays getFromDelays = new getFromDelays(cursor.getInt(0), cursor.getString(1), Long.toString(minutes)+" min");
                                    arrayList2.add(getFromDelays);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                            }
                            while (cursor.moveToNext());

                        } else {
                            textView15.setVisibility(View.VISIBLE);
                            textView15.setText("Niestety brak odjazdów z tego przystanku");
                            imageView15.setVisibility(View.VISIBLE);
                            fab.setVisibility(View.INVISIBLE);
                        }

                    }

                    else
                    {

                        if (cursor.getCount() != 0) {
                            fab.setVisibility(View.VISIBLE);
                            do {
                                getFromDelays getFromDelays = new getFromDelays(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                                arrayList2.add(getFromDelays);
                            }
                            while (cursor.moveToNext());

                        } else {
                            textView15.setVisibility(View.VISIBLE);
                            textView15.setText("Niestety brak odjazdów z tego przystanku");
                            imageView15.setVisibility(View.VISIBLE);
                            fab.setVisibility(View.INVISIBLE);
                        }

                    }

                    dbb.close();
                    cursor.close();
                    syncing = false;
                    adapter2 = new DetailsRecyclerAdapter(arrayList2, syncing);
                    recyclerView2.setAdapter(adapter2);
                    countingTillSync--;
                    Log.d("odliczanie", Integer.toString(countingTillSync));
                    textView14.setText(String.valueOf(countingTillSync));
                }
            };task12.execute(url);

Log.d("synchro2", "THREAD DZIALA");

            timerHandler.postDelayed(this, syncingIntervalsInt*1000); //run every second
        }
    };

    timerHandler.postDelayed(timerRunnable, syncingIntervalsInt*1000); //Start timer after 1 sec

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.menu_main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        //  if (id == R.id.action_map) {
        //    openPreferredLocationInMap();
        //    return true;
        //  }

        return super.onOptionsItemSelected(item);
    }

}

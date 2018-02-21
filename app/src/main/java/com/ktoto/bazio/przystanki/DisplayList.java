package com.ktoto.bazio.przystanki;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import com.ktoto.bazio.przystanki.dane.Delay_main;

import com.ktoto.bazio.przystanki.dane.SettingsActivity;
import com.ktoto.bazio.przystanki.dane.getFromPrzystanki;
import com.ktoto.bazio.przystanki.database.db;
import com.ktoto.bazio.przystanki.details.DetailsDisplayList;
import com.ktoto.bazio.przystanki.sync.AsyncDelays;




import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DisplayList extends AppCompatActivity{
    RecyclerView recyclerView;


    RecyclerView.Adapter adapter;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<getFromPrzystanki> arrayList = new ArrayList<>();
    ProgressBar progressBar2;
    String stronaJson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        db dbb = new db(this);
        SQLiteDatabase sqLiteDatabase = dbb.getReadableDatabase();

        Cursor cursor = dbb.getInformationPrzystanki(sqLiteDatabase);

        cursor.moveToFirst();
        do
        {
            getFromPrzystanki getFromPrzystanki = new getFromPrzystanki(cursor.getString(0), cursor.getInt(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getString(4));
            arrayList.add(getFromPrzystanki);
        }
        while(cursor.moveToNext());

        dbb.close();

        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar2.setVisibility(View.VISIBLE);

                        db dbb = new db(DisplayList.this);
                        SQLiteDatabase sqLiteDatabase = dbb.getReadableDatabase();
                        dbb.dropTable(sqLiteDatabase);
                        Cursor cursor = dbb.getById(sqLiteDatabase, position);
                        cursor.moveToFirst();
                        String test = DatabaseUtils.dumpCursorToString(cursor);
                        String test2 = cursor.getString(0);

                        stronaJson = "http://87.98.237.99:88/delays?stopId="+test2;

                        URL url = null;
                        try {
                            url = new URL(stronaJson);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }


                        Log.d("testt",test2);


                        // do whatever
                        Log.d("clocke", Integer.toString(position));

                        AsyncDelays task = new AsyncDelays(DisplayList.this) {

                            protected void onPostExecute(Delay_main dto) {
                                progressBar2.setVisibility(View.INVISIBLE);
                                Intent i = new Intent(DisplayList.this, DetailsDisplayList.class);
                                i.putExtra("test",stronaJson);
                                startActivity(i);
                                //  startActivity(new Intent(DisplayList.this, DetailsDisplayList.class));
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);




                            }
                        };

                        task.execute(url);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.menu_main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        return true;
    }

private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Wciśnij wstecz ponownie by wyłączyć",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }



            public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

                if (id == R.id.home) {
                    NavUtils.navigateUpFromSameTask(this);
                }

        return super.onOptionsItemSelected(item);
    }







}

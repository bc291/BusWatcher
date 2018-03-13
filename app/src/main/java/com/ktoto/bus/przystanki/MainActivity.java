package com.ktoto.bus.przystanki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.ktoto.bus.przystanki.dane.Przystanki_main;
import com.ktoto.bus.przystanki.database.db;
import com.ktoto.bus.przystanki.sync.AsyncPrzystanki;

public class MainActivity extends AppCompatActivity {

    final db bazaDanychPrzystanki = new db(this);
    private RecyclerView mRecyclerView;
    private Button button;
    private ProgressBar progressBar;
    private static final String DB_PATH = "data/data/com.ktoto.bus.przystanki/databases/baza";
private ProgressBar progressBar2;
private TextView textview;


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Stetho.initializeWithDefaults(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.textView12);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2) ;
         progressBar2 = (ProgressBar) findViewById(R.id.progressBar);
        progressBar2.setProgress(0);
        progressBar2.setMax(100);
        progressBar.setVisibility(View.VISIBLE);
        progressBar2.setVisibility(View.INVISIBLE);
        textview.setVisibility(View.INVISIBLE);


        doDBCheck();


        URL url = null;
        try {
            url = new URL("http://91.244.248.19/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/25e0de48-1acd-4ee5-9bc5-45cb5a55b32f/download/displays.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

       AsyncPrzystanki task = new AsyncPrzystanki(this) {
            protected void onPostExecute(Przystanki_main dto) {

                progressBar.setVisibility(View.INVISIBLE);

                Intent i = new Intent(MainActivity.this, DisplayList.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);

           }
        };
        task.setProgressBar(progressBar2);
        task.execute();



    }



    public void changeVisibility()

    {
        progressBar2.setVisibility(View.VISIBLE);
        textview.setVisibility(View.VISIBLE);

    }




    private void doDBCheck()
    {
        try{
            File file = new File(DB_PATH);
            file.delete();
        }catch(Exception ex)
        {}
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}

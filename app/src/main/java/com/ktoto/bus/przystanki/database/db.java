package com.ktoto.bus.przystanki.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ktoto.bus.przystanki.dane.namesAndValues.dane;


public class db extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "baza.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DROP_QUERY = "drop table if exists "+dane.STOPS_TABLE_NAME+";";
    public db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE =   "CREATE TABLE IF NOT EXISTS " + dane.STOPS_TABLE_NAME + " (" +

                dane._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                dane.COLUMN_NAZWAPRZYSTANKU       + " TEXT, "                 +

                dane.COLUMN_STOPIDPRZYSTANKU + " INTEGER NOT NULL,"                  +
                dane.COLUMN_DLUGOSCGEO   + " REAL, "                    +

                dane.COLUMN_SZEROKOSCGEO   + " REAL, "                    +

                dane.COLUMN_STREFA   + " TEXT);"+"";


        db.execSQL(SQL_CREATE_TABLE);
     //   db.execSQL("CREATE UNIQUE INDEX user_location ON stops (stopDesc,stopId);");







        final String SQL_CREATE_TABLE_DELAYS =   "CREATE TABLE IF NOT EXISTS " + dane.DELAYS_TABLE_NAME + " (" +

                dane._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                dane.COLUMN_ROUTEID       + " INTEGER NOT NULL, "                 +

                dane.COLUMN_HEADSIGN + " TEXT,"                  +

                dane.COLUMN_ESTIMATEDTIME   + " TEXT);"+"";

                  db.execSQL(SQL_CREATE_TABLE_DELAYS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_QUERY);
     //   onCreate(db);
    }





//    public int getCount() {
//        Cursor kursor = null;
//        try {
//            SQLiteDatabase db3 = getReadableDatabase();
//            String[] kolumny ={dane.COLUMN_NAZWA, dane.COLUMN_STOPID1, dane.COLUMN_STOPID2, dane.COLUMN_STOPID3, dane.COLUMN_STOPID4, dane.COLUMN_NOWEID};
//            String query = "select count(*) from TableName where name = ?";
//            kursor = db3.query(dane.STOPS_TABLE_NAME, kolumny, null,null,null,null,null,null);
//
//            if (kursor.moveToFirst()) {
//                return kursor.getInt(0);
//            }
//            return 0;
//        }
//        finally {
//            if (kursor != null) {
//                kursor.close();
//            }
//
//        }}




public void putInformation(SQLiteDatabase db, String nazwa, int stopId1, int stopId2, int stopId3, int stopId4, int noweId )
{


    ContentValues wartosci = new ContentValues();

    wartosci.put(dane.COLUMN_NAZWA, nazwa);
    wartosci.put(dane.COLUMN_STOPID1, stopId1);
    wartosci.put(dane.COLUMN_STOPID2, stopId2);
    wartosci.put(dane.COLUMN_STOPID3, stopId3);
    wartosci.put(dane.COLUMN_STOPID4, stopId4);
    //wartosci.put(dane.COLUMN_NOWEID, noweId);

    long l = db.insert(dane.STOPS_TABLE_NAME, null, wartosci);
}


    public void putInformationPrzystanki(SQLiteDatabase db, String stopDesc, int stopId, double stopLat, double stopLon, String zoneName )
    {


        ContentValues wartosci = new ContentValues();

        wartosci.put(dane.COLUMN_NAZWAPRZYSTANKU, stopDesc);
        wartosci.put(dane.COLUMN_STOPIDPRZYSTANKU, stopId);
        wartosci.put(dane.COLUMN_DLUGOSCGEO, stopLat);
        wartosci.put(dane.COLUMN_SZEROKOSCGEO, stopLon);
        wartosci.put(dane.COLUMN_STREFA, zoneName);
        //wartosci.put(dane.COLUMN_NOWEID, noweId);

        long l = db.insert(dane.STOPS_TABLE_NAME, null, wartosci);
    }



    public void putInformationDelays(SQLiteDatabase db, int routeId, String headSign, String EstimatedTime)
    {


        ContentValues wartosci = new ContentValues();

        wartosci.put(dane.COLUMN_ROUTEID, routeId);
        wartosci.put(dane.COLUMN_HEADSIGN, headSign);
        wartosci.put(dane.COLUMN_ESTIMATEDTIME, EstimatedTime);

        long l = db.insert(dane.DELAYS_TABLE_NAME, null, wartosci);
    }




//dane.COLUMN_NAZWA+" ASC"
public Cursor getInformation2(SQLiteDatabase db){

        String[] projection = {dane.COLUMN_ROUTEID, dane.COLUMN_HEADSIGN, dane.COLUMN_ESTIMATEDTIME};
    Cursor cursor = db.query(dane.DELAYS_TABLE_NAME, projection, null, null, null, null,null);



return cursor;
}


    public Cursor getInformation(SQLiteDatabase db){

        String[] projection = {dane.COLUMN_NAZWA, dane.COLUMN_STOPID1, dane.COLUMN_STOPID2, dane.COLUMN_STOPID3, dane.COLUMN_STOPID4};
        // Cursor cursor = db.query(dane.STOPS_TABLE_NAME, projection, null, null, null, null, dane.COLUMN_NAZWA+" ASC");
        Cursor cursor = db.query(dane.STOPS_TABLE_NAME, projection, null, null, null, null,null);



        return cursor;
    }

    public Cursor getInformationPrzystanki(SQLiteDatabase db){

        String[] projection = {dane.COLUMN_NAZWAPRZYSTANKU, dane.COLUMN_STOPIDPRZYSTANKU, dane.COLUMN_DLUGOSCGEO, dane.COLUMN_SZEROKOSCGEO, dane.COLUMN_STREFA};
        // Cursor cursor = db.query(dane.STOPS_TABLE_NAME, projection, null, null, null, null, dane.COLUMN_NAZWA+" ASC");
        Cursor cursor = db.query(dane.STOPS_TABLE_NAME, projection, null, null, null, null,null);



        return cursor;
    }


    public Cursor getById(SQLiteDatabase db, int position){
       int pozycja = position + 1;
        Cursor mCursor = db.rawQuery(
                "SELECT stopId  FROM  stops WHERE rowid= '"+pozycja+"'" , null);



        return mCursor;
    }

    public static void dropTable(SQLiteDatabase db)
    {

        db.execSQL("delete from delays");

    }
public void test(SQLiteDatabase db)
{

    db.execSQL("ALTER TABLE stops RENAME TO tmp_stops;");



    db.execSQL("CREATE TABLE stops (stopDesc TEXT, stopId INT, stopLat REAL, stopLon REAL, zoneName TEXT);");

    db.execSQL(" INSERT INTO stops(stopDesc, stopId, stopLat, stopLon, zoneName) SELECT stopDesc, stopId, stopLat, stopLon, zoneName FROM tmp_stops ORDER BY stopDesc  COLLATE LOCALIZED ASC;");

    db.execSQL(" DROP TABLE tmp_stops;");


}


    public boolean isTableExists(String tableName, boolean openDb, SQLiteDatabase dbb) {



        if(openDb) {
            if(dbb == null || !dbb.isOpen()) {
                dbb = getReadableDatabase();
            }

            if(!dbb.isReadOnly()) {
                dbb.close();
                dbb = getReadableDatabase();
            }
        }

        Cursor cursor = dbb.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }



}


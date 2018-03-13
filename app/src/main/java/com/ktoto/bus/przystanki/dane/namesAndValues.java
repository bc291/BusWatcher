package com.ktoto.bus.przystanki.dane;

import android.provider.BaseColumns;



public class namesAndValues {






    public static final class dane implements BaseColumns {

        public static final String STOPS_TABLE_NAME = "stops";
        public static final String DELAYS_TABLE_NAME = "delays";

        public static final String COLUMN_STOPID1 = "stopId1";

        /* Weather ID as returned by API, used to identify the icon to be used */
        public static final String COLUMN_STOPID2 = "stopId2";

        /* Min and max temperatures in °C for the day (stored as floats in the database) */
        public static final String COLUMN_STOPID3 = "stopId3";
        public static final String COLUMN_STOPID4 = "stopId4";

        /* Humidity is stored as a float representing percentage */
        public static final String COLUMN_NAZWA = "name";

        public static final String COLUMN_NOWEID = "noweId";
        public static final String COLUMN_HEADSIGN = "headsign";
        public static final String COLUMN_ESTIMATEDTIME = "estimatedTime";
        public static final String COLUMN_ROUTEID = "routeId";

        public static final String COLUMN_NAZWAPRZYSTANKU = "stopDesc";
        public static final String COLUMN_STOPIDPRZYSTANKU = "stopId";
        public static final String COLUMN_DLUGOSCGEO = "stopLat";
        public static final String COLUMN_SZEROKOSCGEO = "stopLon";
        public static final String COLUMN_STREFA = "zoneName";

    }





}



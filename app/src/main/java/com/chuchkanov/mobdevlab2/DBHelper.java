package com.chuchkanov.mobdevlab2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app2.db"; // название бд
    private static final int SCHEMA = 4; // версия базы данных
    static final String TABLE = "districts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_POPULATION = "Population";
    public static final String COLUMN_AREA = "AREA";
    public static final String COLUMN_CAPITAL = "DistrictCapital";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS districts ( "+COLUMN_ID+" INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAME+" TEXT NOT NULL, "+COLUMN_POPULATION+" INTEGER NOT NULL, "+COLUMN_AREA+" INTEGER NOT NULL, "+COLUMN_CAPITAL+" TEXT NOT NULL)");
        // добавление начальных данных
        db.execSQL("INSERT OR IGNORE INTO districts ("+COLUMN_NAME+","+COLUMN_POPULATION+", "+COLUMN_AREA+","+COLUMN_CAPITAL+") VALUES ('Киіївська',1782000,28131,'Київ'),('Закарпатська',1250100,12777,'Ужгород'),('Тернопільська',1052312,13823,'Тернопіль');");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}

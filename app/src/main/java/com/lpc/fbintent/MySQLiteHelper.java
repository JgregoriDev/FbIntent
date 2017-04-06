package com.lpc.fbintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lpc on 6/04/17.
 */

public class MySQLiteHelper  extends SQLiteOpenHelper {
    private static final String Database_Name="T_NumeroCuaAndClicks";
    private static final int Database_V=1;
    private String SQL="CREATE TABLE T_NumeroCuaAndClicks(NumCua INTEGER,NumCLick INTEGER)";
    public MySQLiteHelper(Context context) {
        super(context, Database_Name, null, Database_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Clients ");
        db.execSQL (SQL); // Creating a new version
    }
}

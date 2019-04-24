package com.app.keyalive.crudjava.DataHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by keyalive on 04/01/2018.
 */

public class DataHelper extends SQLiteOpenHelper{

//    membuat database
    private static final String NameDb ="biodatadiri.db";
    private static final int Database_Ver = 2 ;

    public DataHelper(Context context) {
        super(context, NameDb, null, Database_Ver);
    }

//  membuat Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table biodata (nomor text null,nama text null,tgl_lahir text null,jenkel text null,alamat text null,pendidikan text null);";
        Log.d("Data","onCreate"+sql);
        db.execSQL(sql);
        sql = "insert into biodata (nomor, nama, tgl_lahir, jenkel, alamat, pendidikan) values ();";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}

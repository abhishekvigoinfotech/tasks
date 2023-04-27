package com.example.practice.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.practice.models.SQLiteModel;
import com.example.practice.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context){
        super(context, Params.DB_NAME, null, Params.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_TIMESTAMPS + " TEXT, "
                + Params.KEY_URL + " TEXT, "
                + Params.KEY_RESPONSE + " TEXT" + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addRecord(SQLiteModel sqLiteModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_TIMESTAMPS, sqLiteModel.getTimestamp());
        contentValues.put(Params.KEY_URL, sqLiteModel.getUrl());
        contentValues.put(Params.KEY_RESPONSE, sqLiteModel.getResponse());

        db.insert(Params.TABLE_NAME, null, contentValues);
        db.close();
    }

    public List<SQLiteModel> getAllRecords(){
        List<SQLiteModel> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Params.TABLE_NAME;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                SQLiteModel sqLiteModel = new SQLiteModel("", "", "");
                sqLiteModel.setId(Integer.parseInt(cursor.getString(0)));
                sqLiteModel.setTimestamp(cursor.getString(1));
                sqLiteModel.setUrl(cursor.getString(2));
                sqLiteModel.setResponse(cursor.getString(3));

                dataList.add(sqLiteModel);
            } while (cursor.moveToNext());
        }
        return dataList;
    }
}

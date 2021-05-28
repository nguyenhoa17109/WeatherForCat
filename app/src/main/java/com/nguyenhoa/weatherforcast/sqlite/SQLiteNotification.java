package com.nguyenhoa.weatherforcast.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nguyenhoa.weatherforcast.common.Common;
import com.nguyenhoa.weatherforcast.model.Alert;
import com.nguyenhoa.weatherforcast.model.HourWeatherForecastResult;
import com.nguyenhoa.weatherforcast.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class SQLiteNotification extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "weatherforec.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteNotification(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        Log.d()
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE notification(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "day TEXT," +
                "title TEXT," +
                "description TEXT)";
        String sql2 = "CREATE TABLE weather(" +
                "id INTEGER," +
                "general TEXT," +
                "main TEXT," +
                "win TEXT," +
                "clouds TEXT)";
//        Log.d("Alo", sql_noti);
        db.execSQL(sql1);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS 'notification'");
        db.execSQL("DROP TABLE IF EXISTS 'weather'");
        onCreate(db);
    }

    public void addNotification(Notification result){
        SQLiteDatabase r = getWritableDatabase();
        List<Notification> list = getAll();
        if(list.size()>1){
            if(result.toString().equals(list.get(list.size()-1).toString())){
                return;
            }
            else{}
        }else{
            ContentValues values = new ContentValues();
            values.put("title", result.getTitle());
            values.put("day", result.getDay());
            values.put("description", result.getContent());

            long id = r.insertWithOnConflict("notification", null,
                    values, SQLiteDatabase.CONFLICT_IGNORE);
            ContentValues values1 = new ContentValues();
            values1.put("id", id);
            values1.put("general", result.getGeneral());
            values1.put("main", result.getMain());
            values1.put("win", result.getWin());
            values1.put("clouds", result.getClouds());
            r.insert("weather", null, values1);
        }

    }

    public List<Notification> getAll(){
        List<Notification> list = new ArrayList<>();
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.query("notification", null, null, null,
                null, null, null);
        while(cursor.moveToNext()){
            String title = cursor.getString(1);
            String day = cursor.getString(2);
            String description = cursor.getString(3);
            Cursor cursor1 = r.query("weather", null, null, null,
                    null, null, null);
            String general="", main="", win="", clouds = "";
            while (cursor1.moveToNext()){
                general = cursor1.getString(1);
                main = cursor1.getString(2);
                win = cursor1.getString(3);
                clouds = cursor1.getString(4);
            }
            list.add(new Notification(day, title, description, general, win, main, clouds));
        }
        return list;
    }

    public void delete(int id){
        String clause = "id = ?";
        String[] agrs = {Integer.toString(id)};
        SQLiteDatabase r = getWritableDatabase();
        r.delete("notification", clause, agrs);
        r.delete("weather", clause, agrs);
    }
}

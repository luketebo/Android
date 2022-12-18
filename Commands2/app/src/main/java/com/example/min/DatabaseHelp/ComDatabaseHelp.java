package com.example.min.DatabaseHelp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.min.Model.Commands;

import java.util.ArrayList;

public class ComDatabaseHelp extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public ComDatabaseHelp(Context context){
        super(context,"command.db" , null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS command(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT,"+
                "details TEXT," +
                "grammar TEXT," +
                "param TEXT," +
                "example TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS command");
        onCreate(db);
    }

    public void  add(String name,String details, String grammar,String param,String example){
        db.execSQL("insert into command(name,details,grammar,param,example)values(?,?,?,?,?)",new Object[]{name,details,grammar,param,example});
    }

    public ArrayList<Commands> getAll(){
        ArrayList<Commands> list = new ArrayList<Commands>();
        @SuppressLint("Recycle") Cursor cursor = db.query("command",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            @SuppressLint("Range") String details = cursor.getString(cursor.getColumnIndex("details"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String grammar = cursor.getString(cursor.getColumnIndex("grammar"));
            @SuppressLint("Range") String param = cursor.getString(cursor.getColumnIndex("param"));
            @SuppressLint("Range") String example = cursor.getString(cursor.getColumnIndex("example"));
            list.add(new Commands(name,details,grammar,param,example));
        }
        return list;
    }

}

package com.example.min.DatabaseHelp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.min.Model.Record;

import java.util.ArrayList;

public class RecordDatabaseHelp extends SQLiteOpenHelper {
    private SQLiteDatabase db;//声明一个数据库对象

    public RecordDatabaseHelp(Context context){
        super(context,"record.db" , null, 1);
        db = getWritableDatabase();//以读写方式打开数据库
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS record(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT,"+
                "content INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS record");
        onCreate(db);
    }

    public void  add(String title,String content){
        db.execSQL("insert into record(title,content)values(?,?)",new Object[]{title,content});
    }

    public ArrayList<Record> getAll(){
        //声明动态数组
        ArrayList<Record> list = new ArrayList<Record>();
        //将查询到的数据放入游标中
        @SuppressLint("Recycle") Cursor cursor = db.query("record",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            //读取游标中的数据
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
            //将一行的数据存入list中
            list.add(new Record(title,content));
        }
        return list;
    }
}

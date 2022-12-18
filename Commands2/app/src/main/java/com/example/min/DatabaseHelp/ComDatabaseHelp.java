package com.example.min.DatabaseHelp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.min.Model.Commands;

import java.util.ArrayList;

public class ComDatabaseHelp extends SQLiteOpenHelper {
    private SQLiteDatabase db;//声明一个数据库对象

    public ComDatabaseHelp(Context context){
        super(context,"command.db" , null, 1);
        db = getWritableDatabase();//以读写方式打开数据库
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS command(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT,"+
                "details TEXT," +
                "grammar TEXT," +
                "param TEXT," +
                "example TEXT,"+
                "collect INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS command");
        onCreate(db);
    }

    public void  add(String name,String details, String grammar,String param,String example,int collect){
        db.execSQL("insert into command(name,details,grammar,param,example,collect)values(?,?,?,?,?,?)",new Object[]{name,details,grammar,param,example,collect});
    }

    public ArrayList<Commands> getAll(){
        //声明动态数组
        ArrayList<Commands> list = new ArrayList<Commands>();
        //将查询到的数据放入游标中
        @SuppressLint("Recycle") Cursor cursor = db.query("command",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            //读取游标中的数据
            @SuppressLint("Range") String details = cursor.getString(cursor.getColumnIndex("details"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String grammar = cursor.getString(cursor.getColumnIndex("grammar"));
            @SuppressLint("Range") String param = cursor.getString(cursor.getColumnIndex("param"));
            @SuppressLint("Range") String example = cursor.getString(cursor.getColumnIndex("example"));
            @SuppressLint("Range") int collect = cursor.getInt(cursor.getColumnIndex("collect"));
            //将一行的数据存入list中
            list.add(new Commands(name,details,grammar,param,example,collect));
        }
        return list;
    }

    public void update(String name,int collect){
        ContentValues values = new ContentValues();
        System.out.println(collect);
        values.put("collect",collect);
        db.update("command",values,"name=?",new String []{name});
    }

}

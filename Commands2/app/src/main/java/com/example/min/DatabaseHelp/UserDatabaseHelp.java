package com.example.min.DatabaseHelp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.min.Model.User;

import java.util.ArrayList;


public class UserDatabaseHelp extends SQLiteOpenHelper {
    private SQLiteDatabase db;//声明一个数据库对象
    public UserDatabaseHelp(Context context) {
        super(context,"user.db" , null, 1);
        db = getWritableDatabase();//以读写方式打开数据库
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT,"+
                "name TEXT," +
                "password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void  add(String email,String name, String password){
        db.execSQL("insert into user(email,name,password)values(?,?,?)",new Object[]{email,name,password});
    }

    public ArrayList<User> getAll(){
        //声明动态数组
        ArrayList<User> list = new ArrayList<User>();
        //将查询到的数据放入游标中
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            //读取游标中的数据
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            //将一行的数据存入list中
            list.add(new User(email,name,password));
        }
        return list;
    }
}

package top.luketebo.login_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public DataBase(Context context) {
        super(context, "db_test.db", null, 1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,"+
                "password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
    }

    public void add(String name,String password){
        db.execSQL("insert into user(name,password)values(?,?)",new Object[]{name,password});
    }

    public ArrayList<User> getAllData() {
        ArrayList<User> list = new ArrayList<>();
        Cursor cursor =  db.query("user", null, null, null, null, null,"name DESC");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name, password));
        }
        return list;
    }

    public void delete(String name, String password){
        db.execSQL("delete from user where name = ? and password = ?", new Object[]{name, password});
    }

    public void update(String name, String password){
        db.execSQL("update user set password = ? where name = ?", new Object[]{password, name});
    }

}

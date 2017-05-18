package com.example.gaojunhui.textworld.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by gaojunhui on 2017/5/10.
 * 数据库帮助类
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREAT_BOOK = "create table "
            + DatabaseConfig.TABLE_NAME
            + " ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";
    public static final String CREAT_CATEGORY = "create table "
            + DatabaseConfig.TABLE_NAME_CATEGORY
            + " ("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";
    private Context context;

    public MyDatabaseHelper(Context context, String name,
            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAT_BOOK);
        sqLiteDatabase.execSQL(CREAT_CATEGORY);
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists book");
        sqLiteDatabase.execSQL("drop table if exists category");
        onCreate(sqLiteDatabase);
    }
}

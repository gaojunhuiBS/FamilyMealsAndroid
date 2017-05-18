package com.example.gaojunhui.textworld.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Button;
import com.example.gaojunhui.textworld.BaseActivity;
import com.example.gaojunhui.textworld.R;
import com.jakewharton.rxbinding.view.RxView;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaojunhui on 2017/5/10.
 */

public class MyDatabaseActivity extends BaseActivity {
    private Button btnDatabase;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnQuery;
    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private ContentValues contentValues;
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_database;
    }

    @Override
    protected void initView() {

        btnDatabase= (Button) findViewById(R.id.btn_database);
        btnAdd= (Button) findViewById(R.id.btn_add);
        btnUpdate= (Button) findViewById(R.id.btn_update);
        btnDelete= (Button) findViewById(R.id.btn_delete);
        btnQuery= (Button) findViewById(R.id.btn_query);
    }

    @Override
    protected void initData() {
        super.initData();
        databaseHelper=new MyDatabaseHelper(this,DatabaseConfig.DB_NAME,null,DatabaseConfig.DB_VERSION);
        db=databaseHelper.getWritableDatabase();
        contentValues=new ContentValues();
        createDatabase();
        insert();
        upDate();
        delete();
        query();
    }
    private void query(){
            RxView.clicks(btnQuery)
                    .throttleFirst(1,TimeUnit.SECONDS)
                    .subscribe(aVoid -> {
                        //表名，
                        // 查询哪一列或哪几列（不指定，默认查询所有列）,
                        //查询哪一行或哪几行（不指定，默认查询所有行）
                        //查询哪一行或哪几行（不指定，默认查询所有行）
                        //指定需要去group by的列（不指定则不对查询结果进行分组）
                        //对group by之后的数据进行过滤（不指定不进行过滤）
                        //指定查询结果的排序方式（不指定则使用默认的排序方式）
                        Cursor cursor=db.query(DatabaseConfig.TABLE_NAME,null,null,null,null,null,null);

                        if (cursor.moveToFirst()){
                            do {
                                int id=cursor.getInt(cursor.getColumnIndex("id"));
                                String name=cursor.getString(cursor.getColumnIndex("name"));
                                String author=cursor.getString(cursor.getColumnIndex("author"));
                                int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                                Log.d("----", "id: "+id);
                                Log.d("----", "name: "+name);
                                Log.d("----", "author: "+author);
                                Log.d("----", "pages: "+pages);
                                Log.d("----", "price: "+price);
                            }while (cursor.moveToNext());
                        }
                    });
    }
    private void delete(){
        //删除pages>200的那行
        RxView.clicks(btnDelete)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                   db.delete(DatabaseConfig.TABLE_NAME,"pages > ?",new String[]{"200"});
                });
    }
    private void upDate(){
        //将name=ba dao的prices更改为4
        RxView.clicks(btnUpdate)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    contentValues.put("price",4);
                    db.update(DatabaseConfig.TABLE_NAME,contentValues,"name=?",new String[]{"ba dao"});
                });
    }
    private void insert() {
        RxView.clicks(btnAdd)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    //添加第一条数据
                    contentValues.put("name","ba dao");
                    contentValues.put("author","bei shen");
                    contentValues.put("pages",220);
                    contentValues.put("price",16.48);
                    //第三个参数为：用于未指定添加数据的情况下给某些可为空的列自动赋值为null,一般用不到这个功能，传null
                    db.insert(DatabaseConfig.TABLE_NAME,null,contentValues);
                    contentValues.clear();
                    //添加第二条数据
                    contentValues.put("name","yingfei nidi");
                    contentValues.put("author","xiao guizi");
                    contentValues.put("pages",200);
                    contentValues.put("price",13.48);
                    db.insert(DatabaseConfig.TABLE_NAME,null,contentValues);
                    contentValues.clear();
                    //添加第三条数据
                    contentValues.put("name","aodi q7");
                    contentValues.put("author","de guo");
                    contentValues.put("pages",400);
                    contentValues.put("price",13.18);
                    db.insert(DatabaseConfig.TABLE_NAME,null,contentValues);
                    contentValues.clear();
                });
    }

    private void createDatabase() {
        RxView.clicks(btnDatabase)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    databaseHelper.getWritableDatabase();
                });
    }
}

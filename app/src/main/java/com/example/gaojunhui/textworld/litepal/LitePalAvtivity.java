package com.example.gaojunhui.textworld.litepal;

import android.database.Cursor;
import android.util.Log;
import android.widget.Button;
import com.example.gaojunhui.textworld.BaseActivity;
import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.litepal.bean.Book;
import com.jakewharton.rxbinding.view.RxView;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by gaojunhui on 2017/5/11.
 */

public class LitePalAvtivity extends BaseActivity {
    private Button btnCreate;
    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnQuery;
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_database;
    }

    @Override
    protected void initView() {
        btnCreate = (Button) findViewById(R.id.btn_database);
        btnInsert= (Button) findViewById(R.id.btn_add);
        btnUpdate= (Button) findViewById(R.id.btn_update);
        btnDelete= (Button) findViewById(R.id.btn_delete);
        btnQuery= (Button) findViewById(R.id.btn_query);
    }

    @Override
    protected void initData() {
        super.initData();
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
                    //queryAll();
                    queryFirst();
                    queryLast();
                    querySelect();
                });
    }
    /*使用SQL语句进行查询，注意：返回的是Cursor*/
    private void userSQL(){
        Cursor cursor=DataSupport.findBySQL("select * from Book where pages > ? and price < ?","300","15");
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
    }
    /*查询Book表中第2-11条满足页数大于200的name、author、pages这3列数据，并将数据按升序排列*/
    private void group(){
        List<Book> books=DataSupport.select("name","author","pages")
                .where("pages > ?","200")
                .order("price")
                .limit(10)
                .offset(1)
                .find(Book.class);
    }
    /*查询第一条的后三条*/
    private void limit(){
        List<Book> book=DataSupport.limit(3).offset(1).find(Book.class);
    }
    /*按price进行降序排列（asc或着不写，标示升序）*/
    private void order(){
        List<Book> book=DataSupport.order("price desc").find(Book.class);
    }
    /*where()查询pages>300的数据*/
    private void queryWhere(){
        List<Book> book=DataSupport.where("pages > ?","300").find(Book.class);
    }
    /*查询name和author两列*/
    private void querySelect(){
        List<Book> book=DataSupport.select("name","author").find(Book.class);
    }
    /*查询最后一列数据*/
    private void queryLast(){
        Book book=DataSupport.findLast(Book.class);
        Log.d("----", "id: "+book.getId());
        Log.d("----", "name: "+book.getName());
        Log.d("----", "author: "+book.getAuthor());
        Log.d("----", "pages: "+book.getPages());
        Log.d("----", "press: "+book.getPress());
        Log.d("----", "price: "+book.getPrice());
    }
    /*查询第一条数据*/
    private void queryFirst(){
        Book book=DataSupport.findFirst(Book.class);
        Log.d("----", "id: "+book.getId());
        Log.d("----", "name: "+book.getName());
        Log.d("----", "author: "+book.getAuthor());
        Log.d("----", "pages: "+book.getPages());
        Log.d("----", "press: "+book.getPress());
        Log.d("----", "price: "+book.getPrice());
    }
    private void queryAll() {
        Observable.from(DataSupport.findAll(Book.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Book>() {
                    @Override
                    public void call(Book book) {
                        Log.d("----", "id: "+book.getId());
                        Log.d("----", "name: "+book.getName());
                        Log.d("----", "author: "+book.getAuthor());
                        Log.d("----", "pages: "+book.getPages());
                        Log.d("----", "press: "+book.getPress());
                        Log.d("----", "price: "+book.getPrice());

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("----", "call: "+throwable.getMessage());
                    }
                });
    }

    private void delete(){
        RxView.clicks(btnDelete)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    DataSupport.deleteAll(Book.class,"price < ?","10");
                });
    }
    private void upDate(){
        RxView.clicks(btnUpdate)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                   Book book=new Book();
                    book.setPrice(5.0);
                    book.setPress("beishenchubaishe");
                    book.updateAll("name = ? and author = ?","bingshuang","beishen");
                });
    }
    private void insert(){
        RxView.clicks(btnInsert)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    Book book=new Book("beishen",15.5,500,"bingshuang","unKnow");
                    book.save();
                });
    }
    private void createDatabase() {
        RxView.clicks(btnCreate)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    LitePal.getDatabase();
                });
    }
}

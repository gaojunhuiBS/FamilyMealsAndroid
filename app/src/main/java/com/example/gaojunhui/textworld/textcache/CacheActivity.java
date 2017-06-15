package com.example.gaojunhui.textworld.textcache;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import com.example.gaojunhui.textworld.BaseActivity;
import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.util.UIUtil;
import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;
import com.jakewharton.rxbinding.view.RxView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaojunhui on 2017/6/14.
 */

public class CacheActivity extends BaseActivity {
    public static final String LIST_DATA = "list_data";
    public static final String LIST_SP = "list_sharedPreferences";
    private RecyclerView recyclerView;
    private List<CacheData> list = new ArrayList<>();
    private ArrayList<CacheData> arrayList=new ArrayList<>();
    private List<CacheData> list_default = new ArrayList<>();
    private TypeToken<List<CacheData>> typeToken;
    public String[] images = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg"
    };
    private CacheAdapter adapter;
    private Prefser prefser;
    private Button button;
    private ACache aCache;
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_cache;
    }

    @Override
    protected void initView() {
        button = (Button) findViewById(R.id.readCache);
        recyclerView = (RecyclerView) findViewById(R.id.cache_ry);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new CacheAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        aCache=ACache.get(this);
        prefser = new Prefser(getSharedPreferences(LIST_SP, Context.MODE_PRIVATE));
        typeToken = new TypeToken<List<CacheData>>() {};
        if (UIUtil.isNetworkConnected(this)) {
            for (int i = 0; i < images.length; i++) {
                list.add(new CacheData("This is num:" + i, images[i]));
            }
            arrayList= (ArrayList<CacheData>) list;
            //if (prefser != null && list != null && list.size() > 0) {
            //    prefser.put(LIST_DATA, list);
            //}
            aCache.put(LIST_DATA,arrayList,100000);
        } else {
            //if (prefser != null &&
            //        (prefser.get(LIST_DATA, typeToken, list_default) != null) &&
            //        (prefser.get(LIST_DATA, typeToken, list_default).size() > 0)) {
            //    showToast("正在读取缓存");
            //    list.clear();
            //    list = prefser.get(LIST_DATA, typeToken, list_default);
            //    adapter.notifyD(list);
            //} else {
            //    showToast("无缓存");
            //}
            if (aCache.getAsObject(LIST_DATA)!=null) {
                list.clear();
                list = (List<CacheData>) aCache.getAsObject(LIST_DATA);
                adapter.notifyD(list);
            }else{
                showToast("缓存为null");
            }
        }
        adapter.notifyDataSetChanged();
        RxView.clicks(button)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    //if (prefser != null
                    //        && prefser.get(LIST_DATA, typeToken, list_default) != null) {
                    //    showToast("正在读取缓存");
                    //    list.clear();
                    //    list = prefser.get(LIST_DATA, typeToken, list_default);
                    //    adapter.notifyD(list);
                    //    Log.i("----", "initData: " + list.size());
                    //} else {
                    //    showToast("无缓存");
                    //}
                    if (aCache.getAsObject(LIST_DATA)!=null) {
                        list.clear();
                        list = (List<CacheData>) aCache.getAsObject(LIST_DATA);
                        adapter.notifyD(list);
                    }else{
                        showToast("缓存为null");
                    }
                });
    }
}

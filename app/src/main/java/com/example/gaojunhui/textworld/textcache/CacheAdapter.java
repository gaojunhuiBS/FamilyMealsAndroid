package com.example.gaojunhui.textworld.textcache;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.gaojunhui.textworld.R;
import java.util.List;

/**
 * Created by gaojunhui on 2017/6/14.
 */

public class CacheAdapter extends RecyclerView.Adapter<CacheAdapter.MyWH> {
    private Context context;
    private List<CacheData> list;
    public CacheAdapter(Context context,List<CacheData> list) {
        this.context = context;
        this.list=list;
    }
    public void notifyD(List<CacheData> listCache){
        list.clear();
        list=listCache;
        notifyDataSetChanged();
    }
    @Override
    public MyWH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_cache,parent,false);
        MyWH myWH=new MyWH(view);
        return myWH;
    }

    @Override
    public void onBindViewHolder(MyWH holder, int position) {
        holder.cacheTv.setText(list.get(position).title);
        Glide.with(context).
                load(list.get(position).url).
                skipMemoryCache(true).
                placeholder(R.mipmap.ic_launcher).
                error(R.mipmap.ic_launcher).
                into(holder.cacheIv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyWH extends RecyclerView.ViewHolder{
        private ImageView cacheIv;
        private TextView cacheTv;
        public MyWH(View itemView) {
            super(itemView);
            cacheIv= (ImageView) itemView.findViewById(R.id.cache_iv);
            cacheTv= (TextView) itemView.findViewById(R.id.cache_tv);
        }
    }
}

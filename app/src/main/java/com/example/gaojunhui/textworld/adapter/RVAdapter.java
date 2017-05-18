package com.example.gaojunhui.textworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.rest.data.Data;
import java.util.List;

/**
 * Created by gaojunhui on 2017/4/10.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    private List<Data> mList;
    private Context mContext;
    private View mView;

    public RVAdapter(Context mContext, List<Data> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void initData(List<Data> datas) {
        this.mList = datas;
        this.notifyDataSetChanged();
    }

    public void notifyData(List<Data> datas) {
        this.mList.clear();
        this.mList.addAll(datas);
        this.notifyDataSetChanged();
    }

    public void loadMore(List<Data> datas) {
        this.mList.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, null);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext, mList.get(position).getImageList());
        holder.gridView.setAdapter(gridViewAdapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext, "" + i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        GridView gridView;

        public MyViewHolder(View itemView) {
            super(itemView);
            gridView = (GridView) itemView.findViewById(R.id.item_gridView);
        }
    }
}

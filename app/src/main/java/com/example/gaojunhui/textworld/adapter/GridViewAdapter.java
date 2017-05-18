package com.example.gaojunhui.textworld.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.gaojunhui.textworld.R;
import com.example.gaojunhui.textworld.rest.data.ImageListBean;
import com.example.gaojunhui.textworld.util.UIUtil;
import java.util.List;

/**
 * Created by gaojunhui on 2017/5/9.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<ImageListBean> mList;
    private ViewHolder viewHolder;
    private ViewGroup.LayoutParams layoutParams;
    public GridViewAdapter(Context context,List<ImageListBean> mList) {
        this.context=context;
        this.mList=mList;
        layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height= UIUtil.getScreenWidth(context)/3;
        layoutParams.width=UIUtil.getScreenWidth(context)/3;
    }

    @Override
    public int getCount() {
        return mList!=null?mList.size():0;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view==null){
            imageView=new ImageView(context);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //viewHolder=new ViewHolder();
            //view= LayoutInflater.from(context).inflate(R.layout.item_rv,null);
            //viewHolder.imageView= (ImageView) view.findViewById(R.id.imageView);
            //view.setTag(imageView);
        }else {
            imageView= (ImageView) view;
        }
        Glide.with(context).load(mList.get(i).getFileUrl()).error(R.drawable.customactivityoncrash_error_image).into(imageView);
        return imageView;
    }
    static class ViewHolder{
        ImageView imageView;
    }
}

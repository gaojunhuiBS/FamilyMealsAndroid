package com.example.gaojunhui.textworld.headerview;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.gaojunhui.textworld.R;

/**
 * Created by gaojunhui on 2017/3/1.
 * 轮播图的加载器
 */

public class NetworkImageHolderView implements Holder<Integer> {
    private View mView;
    private ImageView mImageView;
    private TextView mTextView;
    @Override
    public View createView(Context context) {
        mView= LayoutInflater.from(context).inflate(R.layout.item_banner,null);
        mImageView= (ImageView) mView.findViewById(R.id.item_banner_iv);
        mTextView= (TextView) mView.findViewById(R.id.circleTv);
        TextPaint tp=mTextView.getPaint();
        tp.setFakeBoldText(true);//字体加粗
        return mView;
    }

    @Override
    public void UpdateUI(Context context,int position, Integer data) {
        mImageView.setImageResource(data);
        //GlideHelper.getInstance().loadImageByHeightAndWidth(context,
        //        mImageView,
        //        data.getLogo(),
        //        rolls_default,
        //        rolls_default,
        //        UIUtil.getScreenWidth(context),
        //        UIUtil.dip2px(context, 110));
    }
}

package com.nsg.zgbx.ui.adapter.circle;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.nsg.zgbx.R;
import com.nsg.zgbx.rest.entity.circle.RollDataEntity;
import com.nsg.zgbx.utils.UIUtil;
import com.nsg.zgbx.utils.glide.GlideHelper;
import static com.nsg.zgbx.R.drawable.rolls_default;

/**
 * Created by gaojunhui on 2017/3/1.
 * 轮播图的加载器
 */

public class NetworkImageHolderView implements Holder<RollDataEntity> {
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
    public void UpdateUI(Context context,int position, RollDataEntity data) {
        mTextView.setText(data.getTitle()!=null? data.getTitle():"");
        GlideHelper.getInstance().loadImageByHeightAndWidth(context,
                mImageView,
                data.getLogo(),
                rolls_default,
                rolls_default,
                UIUtil.getScreenWidth(context),
                UIUtil.dip2px(context, 110));
    }
}

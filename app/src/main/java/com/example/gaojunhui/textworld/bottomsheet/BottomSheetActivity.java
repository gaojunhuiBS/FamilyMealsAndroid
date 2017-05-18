package com.example.gaojunhui.textworld.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.gaojunhui.textworld.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gaojunhui on 2017/4/24.
 */

public class BottomSheetActivity extends AppCompatActivity {
    @Bind(R.id.expand_bt)
    Button expandBt;
    @Bind(R.id.collapse)
    Button collapse;
    @Bind(R.id.hide)
    Button hide;
    @Bind(R.id.show)
    Button show;
    @Bind(R.id.tv_bs)
    TextView tvBs;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.bottomSheetLayout)
    RelativeLayout bottomSheetLayout;

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bs);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState==BottomSheetBehavior.STATE_EXPANDED){
                    tvBs.setText("Collapse me");
                }else{
                    tvBs.setText("Expand me");
                }
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("-----", "onStateChanged: "+"collapsed" );
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("-----", "onStateChanged: "+"expanded" );
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("_____", "onStateChanged: "+"dragging" );
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("______", "onStateChanged: "+"hide" );
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("_______", "onStateChanged: "+"setting" );
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @OnClick({R.id.expand_bt, R.id.collapse, R.id.hide, R.id.show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.expand_bt:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.collapse:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.hide:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.show:
                new BottomSheetDiloag().show(getSupportFragmentManager(),"Dialog");
                break;
        }
    }
}

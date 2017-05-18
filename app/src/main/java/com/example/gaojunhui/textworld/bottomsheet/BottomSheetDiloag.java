package com.example.gaojunhui.textworld.bottomsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaojunhui.textworld.R;

/**
 * Created by gaojunhui on 2017/4/24.
 */

public class BottomSheetDiloag extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bs_fragment,container,false);
        return view;
    }
}

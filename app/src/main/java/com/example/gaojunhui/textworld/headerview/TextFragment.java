package com.example.gaojunhui.textworld.headerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.gaojunhui.textworld.R;

/**
 * Created by gaojunhui on 2017/5/31.
 */

public class TextFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_text,container,false);
        return view;
    }
}

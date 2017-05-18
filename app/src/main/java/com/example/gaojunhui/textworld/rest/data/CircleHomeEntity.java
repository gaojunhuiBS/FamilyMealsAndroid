package com.example.gaojunhui.textworld.rest.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojunhui on 2017/4/10.
 */

public class CircleHomeEntity implements Serializable{
    public int oper_code;
    public String message;
    public String desc;
    public Data data = new Data();
    public class Data implements Serializable {
        public List<com.example.gaojunhui.textworld.rest.data.Data> data = new ArrayList<>();
        public Page page = new Page();
    }

    public class Page implements Serializable {
        public int page_num;
        public int total_row;
        public int page_row;
        @SerializedName("page-hasnext")
        public boolean page_hasnext;
        public int total_page;
    }
}

package com.example.gaojunhui.textworld.textcache;

import java.io.Serializable;

/**
 * Created by gaojunhui on 2017/6/14.
 */

public class CacheData implements Serializable {
    public CacheData(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String title;
    public String url;
}

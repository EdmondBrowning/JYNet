package com.edmond.jynet.network;

import com.edmond.jynet.network.base.BaseJsonObjectListener;
import com.edmond.jynet.network.base.BaseJsonObjectNetWork;

import java.util.Map;

/**
 * Created by edmond on 16-12-16.
 */

public class JsonObjectNetWork extends BaseJsonObjectNetWork {
    public JsonObjectNetWork(int what, String url, BaseJsonObjectListener listener, Map<String, String> map) {
        super(what,url, listener, map);
    }
}

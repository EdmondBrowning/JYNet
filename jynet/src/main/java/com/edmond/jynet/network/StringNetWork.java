package com.edmond.jynet.network;

import com.edmond.jynet.network.base.BaseStringNetWork;

import java.util.Map;

/**
 * Created by edmond on 16-9-27.
 */

public class StringNetWork extends BaseStringNetWork {
    public StringNetWork (int what, String url, StringListener stringNet, Map<String, String> map) {
        super(what,url,stringNet, map);
    }
}

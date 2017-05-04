package com.edmond.jynet.network.base;

import com.edmond.jynet.Net;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by edmond on 16-12-16.
 */

public abstract class BaseJsonObjectNetWork {
    Request<JSONObject> request = null;
    JsonObjectListener jsonObjectListener = null;
    Map<String,String> params = null;
    int what;

    public BaseJsonObjectNetWork(int what,String url, JsonObjectListener listener,Map<String,String> map){
        this.what = what;
        this.params = map;
        this.jsonObjectListener = listener;

        this.request = NoHttp.createJsonObjectRequest(url, RequestMethod.POST);
        request.add(params);
    }

    public void doIt(){
        Net.getRequestQueue().add(0, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {
                if(jsonObjectListener!=null)
                    jsonObjectListener.onStart(what);
            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                if(jsonObjectListener!=null)
                    jsonObjectListener.format(what,response.get());
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if(jsonObjectListener!=null)
                    jsonObjectListener.onFailed(what,url,tag,exception,responseCode,networkMillis);
            }

            @Override
            public void onFinish(int what) {
                if(jsonObjectListener!=null)
                    jsonObjectListener.onFinish(what);
            }
        });
    }

    public interface JsonObjectListener<T> {
        void format(int what, JSONObject response);
        void onStart(int what);
        void onSucceed(int what, T response);
        void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis);
        void onFinish(int what);
    }
}

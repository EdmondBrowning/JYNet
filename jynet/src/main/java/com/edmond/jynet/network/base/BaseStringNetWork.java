package com.edmond.jynet.network.base;

import com.edmond.jynet.Net;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.Map;

/**
 * Created by edmond on 16-9-27.
 */

public abstract class BaseStringNetWork {
    Request<String> request = null;
    StringListener stringListener;
    Map<String,String> params;
    int what;

    public BaseStringNetWork(int what,String url, StringListener listener,Map<String,String> map) {
        this.what = what;
        this.params = map;
        this.stringListener = listener;

        request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add(params);
    }

    public void doIt(){
        Net.getRequestQueue().add(what, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                if(stringListener!=null)
                    stringListener.onStart(what);
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if(stringListener!=null)
                    stringListener.format(what,response.get());
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                if(stringListener!=null)
                    stringListener.onFailed(what,url,tag,exception,responseCode,networkMillis);
            }

            @Override
            public void onFinish(int what) {
                if(stringListener!=null)
                    stringListener.onFinish(what);
            }
        });
    }

    public interface StringListener<T> {
        void format(int what, String response);
        void onStart(int what);
        void onSucceed(int what, T response);
        void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis);
        void onFinish(int what);
    }
}

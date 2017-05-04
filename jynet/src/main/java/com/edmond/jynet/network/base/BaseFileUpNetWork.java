package com.edmond.jynet.network.base;

import com.edmond.jynet.Net;
import com.yolanda.nohttp.BasicBinary;
import com.yolanda.nohttp.BitmapBinary;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.OnUploadListener;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.StringRequest;

import java.util.List;

/**
 * Created by edmond on 17-3-19.
 */

public abstract class BaseFileUpNetWork {
    Request<String> request = null;
    StringListener stringListener;
    int what;

    public BaseFileUpNetWork(String url, String key, FileInfo... fileInfos){
        request = new StringRequest(url, RequestMethod.POST);
        for(int loop=0;loop<fileInfos.length;loop++){
            final FileInfo fileInfo = fileInfos[loop];
            request.add(key+"[]", fileInfo.getBinary());
            fileInfo.getBinary().setUploadListener(0, new OnUploadListener() {
                @Override
                public void onStart(int what) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onStart(what);
                    }
                }

                @Override
                public void onCancel(int what) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onCancel(what);
                    }
                }

                @Override
                public void onProgress(int what, int progress) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onProgress(what,progress);
                    }
                }

                @Override
                public void onFinish(int what) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onFinish(what);
                    }
                }

                @Override
                public void onError(int what, Exception exception) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onError(what,exception);
                    }
                }
            });
        }
    }

    public BaseFileUpNetWork(String url, FileInfo... fileInfos){
        request = new StringRequest(url, RequestMethod.POST);
        for(int loop=0;loop<fileInfos.length;loop++){
            final FileInfo fileInfo = fileInfos[loop];
            request.add(fileInfo.getKey(), fileInfo.getBinary());
            fileInfo.getBinary().setUploadListener(0, new OnUploadListener() {
                @Override
                public void onStart(int what) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onStart(what);
                    }
                }

                @Override
                public void onCancel(int what) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onCancel(what);
                    }
                }

                @Override
                public void onProgress(int what, int progress) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onProgress(what,progress);
                    }
                }

                @Override
                public void onFinish(int what) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onFinish(what);
                    }
                }

                @Override
                public void onError(int what, Exception exception) {
                    if(fileInfo.getListener()!=null){
                        fileInfo.getListener().onError(what,exception);
                    }
                }
            });
        }
    }

    public BaseFileUpNetWork(String url, String key,List<FileBinary> binaries,List<OnUploadListener> listeners){
        request = new StringRequest(url, RequestMethod.POST);
        for(int loop=0;loop<binaries.size();loop++){
            request.add(key, binaries.get(loop));
            binaries.get(loop).setUploadListener(0,listeners.get(loop));
        }
    }

    public BaseFileUpNetWork(String url, List<String> keys, List<FileBinary> binaries, List<OnUploadListener> listeners){
        request = new StringRequest(url, RequestMethod.POST);
        for(int loop=0;loop<keys.size();loop++){
            request.add(keys.get(loop), binaries.get(loop));
            binaries.get(loop).setUploadListener(0,listeners.get(loop));
        }
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
                exception.printStackTrace();
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

    public static class FileInfo{
        String key = "";
        FileBinary binary = null;
        Listener listener = null;
        public interface Listener{
            void onStart(int what);
            void onCancel(int what);
            void onProgress(int what, int progress);
            void onFinish(int what);
            void onError(int what, Exception exception);
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setBinary(FileBinary binary) {
            this.binary = binary;
        }

        public void setListener(Listener listener) {
            this.listener = listener;
        }

        String getKey() {
            return key;
        }

        FileBinary getBinary() {
            return binary;
        }

        Listener getListener() {
            return listener;
        }
    }

    public interface StringListener<T> {
        void format(int what, String response);
        void onStart(int what);
        void onSucceed(int what, T response);
        void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis);
        void onFinish(int what);
    }
}

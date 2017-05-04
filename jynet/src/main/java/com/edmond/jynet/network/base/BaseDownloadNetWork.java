package com.edmond.jynet.network.base;

import android.os.Environment;

import com.edmond.jynet.Net;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadRequest;

import java.io.File;
import java.util.Map;

/**
 * Created by edmond on 16-12-16.
 */

public abstract class BaseDownloadNetWork {
    DownloadRequest request = null;
    DownloadListener downLoadListener = null;
    Map<String,String> params = null;
    String fileFolder = null;
    String fileName = null;
    int what;

    public BaseDownloadNetWork(int what,String url, String fileFolder, String fileName ,DownloadListener listener,Map<String,String> map){
        this.what = what;
        this.params = map;
        this.downLoadListener = listener;
        this.fileFolder = fileFolder;
        this.fileName = fileName;

        request = NoHttp.createDownloadRequest(url, fileFolder,fileName,true,true);
        request.add(params);
    }

    public void start(){
        Net.getDownloadQueue().add(what, request, new com.yolanda.nohttp.download.DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                if(downLoadListener!=null)
                    downLoadListener.onDownloadError(what,exception);
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                if(downLoadListener!=null)
                    downLoadListener.onStart(what,isResume,rangeSize,responseHeaders,allCount);
            }

            @Override
            public void onProgress(int what, int progress, long fileCount) {
                if(downLoadListener!=null)
                    downLoadListener.onProgress(what,progress,fileCount);
            }

            @Override
            public void onFinish(int what, String filePath) {
                if(downLoadListener!=null)
                    downLoadListener.onFinish(what,filePath);
            }

            @Override
            public void onCancel(int what) {
                if(downLoadListener!=null)
                    downLoadListener.onCancel(what);
            }
        });
    }

    public void pause(){
        request.cancel();
    }

    public void resume(){
        Net.getDownloadQueue().add(what, request, new com.yolanda.nohttp.download.DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                if(downLoadListener!=null)
                    downLoadListener.onDownloadError(what,exception);
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                if(downLoadListener!=null)
                    downLoadListener.onStart(what,isResume,rangeSize,responseHeaders,allCount);
            }

            @Override
            public void onProgress(int what, int progress, long fileCount) {
                if(downLoadListener!=null)
                    downLoadListener.onProgress(what,progress,fileCount);
            }

            @Override
            public void onFinish(int what, String filePath) {
                if(downLoadListener!=null)
                    downLoadListener.onFinish(what,filePath);
            }

            @Override
            public void onCancel(int what) {
                if(downLoadListener!=null)
                    downLoadListener.onCancel(what);
            }
        });
    }

    public void cancel(){
        request.cancel();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),fileName+".nohttp");
        if (file.isFile() && file.exists()) {
            file.delete();
        }else if((file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),fileName)).isFile()&&file.exists()){
            file.delete();
        }
    }

    public interface DownloadListener {
        void onDownloadError(int what, Exception exception);
        void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount);
        void onProgress(int what, int progress, long fileCount);
        void onFinish(int what, String filePath);
        void onCancel(int what);
    }
}

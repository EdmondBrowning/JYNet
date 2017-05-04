package com.edmond.jynet;

import android.os.Environment;
import android.os.Bundle;
import android.util.Log;

import com.edmond.jynet.network.DownloadNetWork;
import com.edmond.jynet.network.FileUpNetWork;
import com.edmond.jynet.network.base.BaseDownloadNetWork;
import com.edmond.jynet.network.base.BaseFileUpNetWork;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.Headers;

import java.io.File;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File sdCardDir=Environment.getExternalStorageDirectory();
        File file1 = new File(sdCardDir.getPath(),"Download/1.jpg");
        File file2 = new File(sdCardDir.getPath(),"Download/2.jpg");
        File file3 = new File(sdCardDir.getPath(),"Download/3.jpg");

        BaseFileUpNetWork.FileInfo fileInfo1 = new BaseFileUpNetWork.FileInfo();
        fileInfo1.setBinary(new FileBinary(file1,"1.jpg"));
        fileInfo1.setListener(new BaseFileUpNetWork.FileInfo.Listener() {
            @Override
            public void onStart(int what) {
                Log.d("TAG","onStart");
            }

            @Override
            public void onCancel(int what) {
                Log.d("TAG","onCancel");
            }

            @Override
            public void onProgress(int what, int progress) {
                Log.d("TAG","onProgress");
            }

            @Override
            public void onFinish(int what) {
                Log.d("TAG","onFinish");
            }

            @Override
            public void onError(int what, Exception exception) {
                Log.d("TAG","onError");
            }
        });

        BaseFileUpNetWork.FileInfo fileInfo2 = new BaseFileUpNetWork.FileInfo();
        fileInfo2.setBinary(new FileBinary(file2,"2.jpg"));
        fileInfo2.setListener(new BaseFileUpNetWork.FileInfo.Listener() {
            @Override
            public void onStart(int what) {
                Log.d("TAG","onStart");
            }

            @Override
            public void onCancel(int what) {
                Log.d("TAG","onCancel");
            }

            @Override
            public void onProgress(int what, int progress) {
                Log.d("TAG","onProgress");
            }

            @Override
            public void onFinish(int what) {
                Log.d("TAG","onFinish");
            }

            @Override
            public void onError(int what, Exception exception) {
                Log.d("TAG","onError");
            }
        });

        FileUpNetWork fileUpNetWork = new FileUpNetWork("http://10.248.185.77/files_upload.php", "file",fileInfo1,fileInfo2);
        fileUpNetWork.doIt();

        DownloadNetWork downloadNetWork = new DownloadNetWork(0, "http://192.168.0.103/gradle-3.3-all.zip", sdCardDir.getAbsolutePath(), "test.txt", new BaseDownloadNetWork.DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                exception.printStackTrace();
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

            }

            @Override
            public void onProgress(int what, int progress, long fileCount) {
                Log.d("TAg", String.valueOf(progress));
            }

            @Override
            public void onFinish(int what, String filePath) {
                Log.d("TAg", "onfinish");
            }

            @Override
            public void onCancel(int what) {

            }
        },null);
        downloadNetWork.start();
    }
}

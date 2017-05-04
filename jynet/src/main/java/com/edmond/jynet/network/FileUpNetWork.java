package com.edmond.jynet.network;

import com.edmond.jynet.network.base.BaseFileUpNetWork;
import com.yolanda.nohttp.BasicBinary;
import com.yolanda.nohttp.BitmapBinary;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.OnUploadListener;

import java.util.List;

/**
 * Created by edmond on 17-3-19.
 */

public class FileUpNetWork extends BaseFileUpNetWork {
    public FileUpNetWork(String url, String key, FileInfo... fileInfos) {
        super(url, key, fileInfos);
    }

    public FileUpNetWork(String url, FileInfo... fileInfos) {
        super(url, fileInfos);
    }
}

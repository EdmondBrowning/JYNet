package com.edmond.jynet;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by edmond on 16-8-15.
 * @author edmond
 */
public abstract class BaseActivity extends AppCompatActivity {
    private String TAG = "BaseActivity";
    private PackageManager pm = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pm = getPackageManager();
        try {
            PackageInfo pack = pm.getPackageInfo(getPackageName(),PackageManager.GET_PERMISSIONS);
            String[] permissionStrings = pack.requestedPermissions;
            if(permissionStrings!=null){
                for(String permissionString:permissionStrings){
                    int permission = ActivityCompat.checkSelfPermission(this, permissionString);
                    int REQUEST_PERMISSION = 1;

                    if(permission!=PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(this,permissionStrings,REQUEST_PERMISSION);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PackageManager getPm() {
        return pm;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(TAG,"FREE");
    }
}

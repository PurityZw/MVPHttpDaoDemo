package com.bestvike.mvphttpdaodemo.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.bestvike.mvphttpdaodemo.base.AppApplication;


/**
 * Created by Administrator on 2017/5/31.
 * hqx
 * 权限管理
 */
public class CheckPermission {

    /**
     * 判断权限有没有授权 同时请求权限
     *
     * @param context
     * @param permissionName 权限名称
     * @param requestCode    请求码
     * @return true 有此权限 false 没有此权限
     */
    public static boolean checkPermission(Activity context, String permissionName, int requestCode) {
        PackageManager pkgManager = AppApplication.CONTEXT.getPackageManager();
        boolean hasPermission = pkgManager.checkPermission(permissionName, AppApplication.CONTEXT.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        if(Build.VERSION.SDK_INT >= 23 && !hasPermission){
            ActivityCompat.requestPermissions(context, new String[]{permissionName}, requestCode);
            return false;
        }

        //API23以下时，只要在Manifest中声明了权限一律返回true
        return hasPermission;
    }

    /**
     * 判断权限有没有授权 同时请求权限，只能在Fragment中使用
     *
     * @param fragment 需要有回调的fragment
     * @param permissionName 权限名称
     * @param requestCode    请求码
     * @return true 有此权限 false 没有此权限
     */
    public static boolean checkPermission(Fragment fragment, String permissionName, int requestCode) {
        PackageManager pkgManager = AppApplication.CONTEXT.getPackageManager();
        boolean hasPermission = pkgManager.checkPermission(permissionName, AppApplication.CONTEXT.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        if(Build.VERSION.SDK_INT >= 23 && !hasPermission){
            fragment.requestPermissions(new String[]{permissionName}, requestCode);
            return false;
        }

        //API23以下时，只要在Manifest中声明了权限一律返回true
        return hasPermission;
    }

    /**
     * 判断权限有没有授权，无权限，不请求授权
     *
     * @param permissionName 权限名称
     * @return true 有此权限 false 没有此权限
     */
    public static boolean checkPermission(String permissionName) {
        PackageManager pkgManager = AppApplication.CONTEXT.getPackageManager();
        return pkgManager.checkPermission(permissionName, AppApplication.CONTEXT.getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }
}

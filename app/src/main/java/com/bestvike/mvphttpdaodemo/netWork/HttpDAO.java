package com.bestvike.mvphttpdaodemo.netWork;

import android.content.pm.PackageManager;


import com.bestvike.mvphttpdaodemo.base.AppApplication;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.OkHttpUtils;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.builder.GetBuilder;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.builder.PostStringBuilder;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.builder.PutStringBuilder;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.callback.DaoStringCallBack;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.utils.TokenHelper;
import com.bestvike.mvphttpdaodemo.utils.CheckUtil;
import com.bestvike.mvphttpdaodemo.utils.JsonUtils;
import com.bestvike.mvphttpdaodemo.utils.Logger;
import com.bestvike.mvphttpdaodemo.utils.SpHelper;
import com.bestvike.mvphttpdaodemo.utils.UiUtil;
import com.bestvike.mvphttpdaodemo.utils.interfaceUtils.SpKey;

import java.util.Map;

/**
 * 项目名称：HFAndroid
 * 项目作者：hqx
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------------------------------------------------------
 */
public class HttpDAO {

    //不需要token接口添加到此处
    public static final String NO_TOKEN =
            BaseUrl.URL_GETDICT;//字典项查询

    //不需要重发的接口添加到此处
    public static final String NO_RETRY = "";

    private DaoStringCallBack callback;
    private Object tag;

    private String appVersion;
    private String token;
    private String channelNo;

    public HttpDAO(NetResultCallBack netResultCallBack) {
        this.tag = netResultCallBack;
        token = SpHelper.getInstance().readMsgFromSp(SpKey.LOGIN, SpKey.LOGIN_ACCESSTOKEN);
        channelNo = SpHelper.getInstance().readMsgFromSp(SpKey.LOGIN, SpKey.LOGIN_CHANNELNO);
        callback = new DaoStringCallBack(netResultCallBack);
        if (CheckUtil.isEmpty(token)) {
            Logger.e("-----", "token:" + token);
        }
        try {
            appVersion = AppApplication.CONTEXT.getPackageManager().getPackageInfo(AppApplication.CONTEXT.getPackageName(), PackageManager.GET_CONFIGURATIONS).versionName;
        } catch (Exception e) {
            appVersion = "";
        }
    }

    /**
     * 可调用方法，requestBean是请求参数的封装类
     */
    public <E> void put(String url, E requestParam, String flag) {
        PutStringBuilder builder = OkHttpUtils.putString()
                .tag(tag)
                .url(BaseUrl.baseUrl + url)
                .content(JsonUtils.class2Json(requestParam))
                .addHeader("Connection", "close")
                .addHeader("APPVersion", "AND-P-" + appVersion)
                .addHeader("DeviceModel", "AND-P-" + android.os.Build.MODEL)
                .addHeader("DeviceResolution", "AND-P-" + UiUtil.getDeviceWidth() + "," + UiUtil.getDeviceHeight())
                .addHeader("SysVersion", "AND-P-" + android.os.Build.VERSION.RELEASE)
                .addHeader("channel", "14")
                .addHeader("channel_no", channelNo);

        if (!NO_TOKEN.contains(url)) {
            builder.addHeader("Authorization", "Bearer" + token)
                    .addHeader("access_token", token);
        }
        builder.build().execute(callback, flag);

        TokenHelper.getInstance().saveRequestParams(url, requestParam, flag, "PUT", callback);
    }


    public void get(String url, Map<String, String> requestParam, String flag) {
        GetBuilder builder = OkHttpUtils
                .get()
                .tag(tag)
                .url(BaseUrl.baseUrl + url)
                .params(requestParam)
                .addHeader("Connection", "close")
                .addHeader("APPVersion", "AND-P-" + appVersion)
                .addHeader("DeviceModel", "AND-P-" + android.os.Build.MODEL)
                .addHeader("DeviceResolution", "AND-P-" + UiUtil.getDeviceWidth() + "," + UiUtil.getDeviceHeight())
                .addHeader("SysVersion", "AND-P-" + android.os.Build.VERSION.RELEASE)
                .addHeader("channel", "14")
                .addHeader("channel_no", channelNo);

        if (!NO_TOKEN.contains(url)) {
            builder.addHeader("Authorization", "Bearer" + token)
                    .addHeader("access_token", token);
        }
        builder.build().execute(callback, flag);

        TokenHelper.getInstance().saveRequestParams(url, requestParam, flag, "GET", callback);
    }

    public <E> void post(String url, E requestParam, String flag) {
        PostStringBuilder builder = OkHttpUtils
                .postString()
                .tag(tag)
                .url(BaseUrl.baseUrl + url)
                .content(JsonUtils.class2Json(requestParam))
                .addHeader("Connection", "close")
                .addHeader("APPVersion", "AND-P-" + appVersion)
                .addHeader("DeviceModel", "AND-P-" + android.os.Build.MODEL)
                .addHeader("DeviceResolution", "AND-P-" + UiUtil.getDeviceWidth() + "," + UiUtil.getDeviceHeight())
                .addHeader("SysVersion", "AND-P-" + android.os.Build.VERSION.RELEASE)
                .addHeader("channel", "14")
                .addHeader("channel_no", channelNo);
        if (!NO_TOKEN.contains(url)) {
            builder.addHeader("Authorization", "Bearer" + token)
                    .addHeader("access_token", token);
        }
        if (NO_RETRY.contains(url)) {
            builder.build(false).execute(callback, flag);
        } else {
            builder.build().execute(callback, flag);
        }

        TokenHelper.getInstance().saveRequestParams(url, requestParam, flag, "POST", callback);
    }
}

package com.bestvike.mvphttpdaodemo.netWork.okHttp.callback;

import com.bestvike.mvphttpdaodemo.R;
import com.bestvike.mvphttpdaodemo.base.AppApplication;
import com.bestvike.mvphttpdaodemo.bean.RequestParamsForToken;
import com.bestvike.mvphttpdaodemo.bean.ResultBean;
import com.bestvike.mvphttpdaodemo.netWork.NetResultCallBack;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.utils.TokenHelper;
import com.bestvike.mvphttpdaodemo.utils.CheckUtil;
import com.bestvike.mvphttpdaodemo.utils.JsonUtils;
import com.bestvike.mvphttpdaodemo.utils.Logger;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * 项目名称：HFAndroid
 * 项目作者：hqx
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------------------------------------------------------
 */
public class DaoStringCallBack extends StringCallback {
    public static final String SUCCESS = "00000";
    public static final String RETMSG = "retMsg";
    public static final String BODY = "json";
    public NetResultCallBack callBack;

    public DaoStringCallBack(NetResultCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onError(Call call, Exception e, String flag) {
        RequestParamsForToken rpft = TokenHelper.getInstance().requestMap.remove(flag);
        Logger.e("网络错误", e.toString());
        try {
            if (e.toString().contains("Canceled") || e.toString().contains("Socket closed")) {
                if (rpft != null)
                    rpft.release();
                return;
            } else if (TokenHelper.getInstance().isTokenOutOfDate
                    (e.getMessage()) && !flag.startsWith(TokenHelper.TOKEN_FLAG)) {
                TokenHelper.getInstance().requestMap.put(flag, rpft);
                TokenHelper.getInstance().getToken(flag, null);
                Logger.e("重新请求token", "<----------------------------Token重新请求------------------------------>");
                return;
            }
            if (callBack != null)
                callBack.onErr(AppApplication.CONTEXT.getResources().getString(R.string.noConnectionCode), AppApplication.CONTEXT.getResources().getString(R.string.noConnection), flag);
        } catch (Exception ex) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(String response, String flag) {
        Logger.e(flag, "服务器返回：" + response);
        RequestParamsForToken rpft = TokenHelper.getInstance().requestMap.remove(flag);
        if (rpft != null)
            rpft.release();
        if (response == null) {
            if (callBack != null)
                callBack.onErr(SUCCESS, AppApplication.CONTEXT.getResources().getString(R.string.noResponse), flag);
            return;
        }

        ResultBean bean = null;
        try {
            bean = JsonUtils.json2Class(response, ResultBean.class);
        } catch (Exception e) {
            if (callBack != null)
                callBack.onErr(e.getMessage(), AppApplication.CONTEXT.getResources().getString(R.string.data_exception), flag);
            return;
        }

        if (bean == null) {
            if (callBack != null)
                callBack.onErr(SUCCESS, AppApplication.CONTEXT.getResources().getString(R.string.noResponse), flag);
            return;
        }

        if (bean.head == null) {
            if (flag != null && flag.startsWith(TokenHelper.TOKEN_FLAG)) {
                //token重新请求
                if (callBack != null) {
                    callBack.onSuccess(response, flag);
                }
            } else {
                if (callBack != null)
                    callBack.onErr(SUCCESS, AppApplication.CONTEXT.getResources().getString(R.string.data_exception), flag);
            }
        } else {
            if (SUCCESS.equals(bean.head.retFlag)) {
                Object obj = bean.body;
                if (obj == null) {
                    if(response.contains("body")){
                        if (callBack != null)
                            callBack.onErr(SUCCESS, AppApplication.CONTEXT.getResources().getString(R.string.data_exception), flag);
                    }else{
                        if (callBack != null)
                            callBack.onSuccess(response, flag);
                    }
                } else {
                    String json;
                    try {
                        Gson gson = new Gson();
                        json = gson.toJson(obj);
                    } catch (Exception e) {
                        if (callBack != null)
                            callBack.onErr(e.getMessage(),
                                    AppApplication.CONTEXT.getResources()
                                            .getString(R.string.data_exception), flag);
                        return;
                    }
                    if (!CheckUtil.isEmpty(json)) {
                        if (callBack != null)
                            callBack.onSuccess(json, flag);
                    } else {
                        if (callBack != null)
                            callBack.onErr(SUCCESS,
                                    AppApplication.CONTEXT.getResources()
                                            .getString(R.string.data_exception), flag);
                    }
                }
            } else {
                if (callBack != null) {
                    bean.head.retMsg = bean.head.retMsg == null ? "" : bean.head.retMsg;
                    if (bean.body != null) {
                        Gson gson = new Gson();
                        String json = gson.toJson(bean.body);
                        if (!CheckUtil.isEmpty(json)) {
                            Map<String, String> map = new HashMap<>();
                            map.put(BODY, json);
                            map.put(RETMSG, bean.head.retMsg);
                            //目前仅有存在返回体的情况下返回map
                            callBack.onErr(bean.head.retFlag, map, flag);
                        } else {
                            callBack.onErr(bean.head.retFlag, bean.head.retMsg, flag);
                        }
                    } else {
                        callBack.onErr(bean.head.retFlag, bean.head.retMsg, flag);
                    }
                }
            }
        }
    }
}

package com.bestvike.mvphttpdaodemo.bean;


import com.bestvike.mvphttpdaodemo.netWork.okHttp.callback.DaoStringCallBack;

/**
 * 项目名称：ysApprove
 * 项目作者：hqx
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：用于储存原请求的参数的类
 * ----------------------------------------------------------------------------------------------------
 */
public class RequestParamsForToken {
    public String url;
    public String flag;
    public Object params;
    public String method;//GET POST PUT DELETE
    public Object tag;
    public DaoStringCallBack callBack;

    public void release(){
        url = null;
        flag = null;
        params = null;
        method = null;
        callBack = null;
        tag = null;
        System.gc();
    }
}

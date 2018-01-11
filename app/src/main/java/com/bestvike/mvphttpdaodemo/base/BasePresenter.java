package com.bestvike.mvphttpdaodemo.base;


import com.bestvike.mvphttpdaodemo.netWork.NetResultCallBack;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.OkHttpUtils;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.callback.DaoStringCallBack;

import java.lang.ref.WeakReference;
import java.util.Map;


/**
 * 项目名称：
 * 项目作者：hqx
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------------------------------------------------------
 */
public class BasePresenter<T extends IView> implements NetResultCallBack {

    private WeakReference<T> reference;
    protected T view;
    public BasePresenter(T view) {
        onAttach(view);
    }

    public void onAttach(T view){
        if(!isAttached()){
            reference = new WeakReference<>(view);
            this.view = reference.get();
        }
    }
    public boolean isAttached(){
        return reference != null && reference.get() != null;
    }

    public void onDettached(){
        OkHttpUtils.getInstance().cancelTag(this);
        if(reference != null){
            reference.clear();
        }
        reference = null;
        view = null;
    }

    @Override
    public void onSuccess(Object response, String flag) {}

    @Override
    public void onErr(String retFlag, Object response, String flag) {
        String msg;
        if(response instanceof Map){
            msg = ((Map<String, String>) response).get(DaoStringCallBack.RETMSG);
            msg = msg==null?"":msg;
        }else{
            msg = response.toString();
        }
        view.onErr(retFlag, msg, flag);
    }
}

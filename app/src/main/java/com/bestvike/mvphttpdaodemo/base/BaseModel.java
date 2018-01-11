package com.bestvike.mvphttpdaodemo.base;


import com.bestvike.mvphttpdaodemo.netWork.NetResultCallBack;

import java.lang.ref.WeakReference;


/**
 * 项目名称：
 * 项目作者：hqx
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------------------------------------------------------
 */
public abstract class BaseModel {

    private final WeakReference<NetResultCallBack> reference;
    public BaseModel(NetResultCallBack callBack) {
        reference = new WeakReference(callBack);
    }
    
    protected NetResultCallBack callBack(){
        if(reference != null){
            return reference.get();
        }
        return null;
    }

    public abstract Object parseResponse(String response);
}

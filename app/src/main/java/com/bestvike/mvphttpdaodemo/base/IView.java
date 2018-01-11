package com.bestvike.mvphttpdaodemo.base;


import com.bestvike.mvphttpdaodemo.interfaces.DialogListener;
import com.bestvike.mvphttpdaodemo.netWork.NetResultCallBack;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface IView extends NetResultCallBack {

    void showProgress(boolean flag);
    void showProgress(boolean flag, String msg);

    void showDialog(String msg);
    void showDialog(String msg, DialogListener listener);
}

package com.bestvike.mvphttpdaodemo.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;


import com.bestvike.mvphttpdaodemo.interfaces.DialogListener;
import com.bestvike.mvphttpdaodemo.netWork.NetResultCallBack;
import com.bestvike.mvphttpdaodemo.view.LoadingProgress;


/**
 * 项目名称：修改baseFragment
 * 项目作者：hqx
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：
 * activity可以被所有继承者直接访问到
 * ----------------------------------------------------------------------------------------------------
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener,NetResultCallBack {

    protected BaseActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }

    public void showDialog(String msg) {
        if(activity != null)
            activity.showDialog(msg);
    }

    public void showDialog(String msg, DialogListener listener) {
        if(activity != null)
            activity.showDialog(msg, listener);
    }

    /*展示网络请求菊花*/
    public LoadingProgress progress;
    public void showProgress(boolean flag) {
        if(activity != null)
            showProgress(flag, null);
    }
    public void showProgress(boolean flag, String msg) {
        try {
            if (flag && activity != null) {
                if (progress == null) {
                    progress = LoadingProgress.show(activity, msg, true, new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            dialog.dismiss();
                            activity.onBackPressed();
                        }
                    });
                }
                if (progress != null && !progress.isShowing()) {
                    progress.setMsg(msg);
                    progress.show();
                }
            } else {
                if (progress != null && progress.isShowing()) {
                    progress.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try{
            if(progress != null){
                progress.dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        progress = null;
    }

    @Override
    public void onDestroy() {
        destroyPresenter();
        super.onDestroy();
        this.activity = null;
    }

    @Override
    public void onClick(View v) {}

    @Override
    public void onSuccess(Object response, String flag) {}

    @Override
    public void onErr(String retFlag, Object response, String flag) {
        showProgress(false);
        showDialog(response.toString());
    }

    /** 用于销毁Presenter */
    protected abstract void destroyPresenter();

    /**
     * fragment name
     */
    public  String getFragmentName(){
        return this.getClass().getSimpleName();
    }
}

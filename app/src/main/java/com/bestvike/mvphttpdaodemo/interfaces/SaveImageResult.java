package com.bestvike.mvphttpdaodemo.interfaces;

import java.io.File;

/**
 * 项目名称：HFAndroid
 * 项目作者：hqx
 * 创建日期：2017/6/8 18:34.
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------------------------------------------------------
 */

public interface SaveImageResult {

    void onSaveFailed(String retFlag, String retMsg);
    void onSaveSuccess(File file, String fileName);

}

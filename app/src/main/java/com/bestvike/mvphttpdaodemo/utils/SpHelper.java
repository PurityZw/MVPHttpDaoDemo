package com.bestvike.mvphttpdaodemo.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.bestvike.mvphttpdaodemo.base.AppApplication;
import com.bestvike.mvphttpdaodemo.utils.interfaceUtils.SpKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjie on 2017/6/5.
 * SP存值帮助类，统一管理sp存值
 * 这个类中只有存取String数据的一些公共操作方法，需要存取其他类型的可以通过方法获取实例，然后进行存取
 * 存取时为保证存值与取值key一致，相对应的key值存在接口文件 SpKey中
 * 需要新增值类型存进sp中时，key值也要写到 SpKey中，方便管理与查阅
 */

public class SpHelper {
    /**
     * 获取sp实例公共方法，需要传入sp的key值
     */
    public SharedPreferences getSpInstance(String spKey){
        return AppApplication.CONTEXT.getSharedPreferences(spKey, Context.MODE_PRIVATE);
    }

    /**
     * 获取editor实例的公共方法，需要传入sp的key值
     */
    public SharedPreferences.Editor getSpEditorInstance(String spKey){
        return getSpInstance(spKey).edit();
    }

    /**
     * 保存sp信息的公共方法
     * spKey：sp的key值
     * valueKey：键值对的key值
     */
    public void saveMsgToSp(String spKey , String valueKey , String value){
        SharedPreferences.Editor editor = getSpEditorInstance(spKey);
        editor.putString(valueKey,value).commit();
    }

    /**
     * 读取sp信息的公共方法
     * spKey：sp的key值
     * valueKey：键值对的key值
     */
    public String readMsgFromSp(String spKey , String valueKey){
        return getSpInstance(spKey).getString(valueKey,"");
    }

    /**
     * 删除某个sp文件下的所有值的公共方法
     * spKey：sp的key值
     */
    public void deleteAllMsgFromSp(String spKey){
        SharedPreferences.Editor editor = getSpEditorInstance(spKey);
        editor.clear().commit();
    }

    /**
     * 删除sp中某一条信息的公共方法
     * spKey：sp的key值
     * valueKey：键值对的key值
     */
    public void deleteMsgFromSp(String spKey , String valueKey){
        SharedPreferences.Editor editor = getSpEditorInstance(spKey);
        editor.remove(valueKey).commit();
    }

    /**
     * 需要删除多个sp中的数据时使用
     * 将初始化sp的key值封装成list传进来即可
     * @param list
     */
    public void deleteAllMsgFromSpList(List<String> list){
        for(String str : list){
            deleteAllMsgFromSp(str);
        }
    }

    /**
     * 清空所有sp信息
     * 用户输入的登录名不清空
     * 调用情况：
     * 1、点击 退出登录 时调用，清空缓存
     * 2、登录页面点击 登录 时调用，防止用户切换账号后取到老数据
     * 其他情况要谨慎调用，需要的话要一起磋商，防止产生丢值问题
     */
    public void clearSp(){
        String inputId = readMsgFromSp(SpKey.LOGIN,SpKey.LOGIN_INPUTID);
        List<String> list = new ArrayList<>();
        list.add(SpKey.LOGIN);
        list.add(SpKey.USER);
        list.add(SpKey.LOCATION);
        list.add(SpKey.STATE);
        list.add(SpKey.FLAG);
        list.add(SpKey.CONFIGURE);
        list.add(SpKey.OTHER);
        deleteAllMsgFromSpList(list);
        saveMsgToSp(SpKey.LOGIN,SpKey.LOGIN_INPUTID,inputId);
    }

    //单例
    private static final class SingleTonHolder{
        private static final SpHelper instance = new SpHelper();
    }
    public static SpHelper getInstance(){
        return SingleTonHolder.instance;
    }
    private SpHelper(){}
}

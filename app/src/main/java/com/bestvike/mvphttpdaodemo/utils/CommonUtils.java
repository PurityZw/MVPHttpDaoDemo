package com.bestvike.mvphttpdaodemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;


import com.bestvike.mvphttpdaodemo.activity.MainActivity;
import com.bestvike.mvphttpdaodemo.base.AppApplication;
import com.bestvike.mvphttpdaodemo.utils.interfaceUtils.SpKey;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共方法
 * Created by zhangxiu on 2017/6/5.
 */

public class CommonUtils {
    private static final String TAG = "CommonUtils";

    /**
     * 获取版本号
     *
     * @param context
     * @return 版本号
     */
    public static String getVersion(Context context) {
        PackageInfo info = null;
        String verson = "";
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            verson = info.versionName;
            Logger.e(TAG, "版本号：" + verson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verson;
    }

    /**
     * 数字串安全转化为Integer——valueOf
     *
     * @param string 数字串
     * @return Integer数字
     */
    public static Integer mIntegerValueOf(String string) {
        if (CheckUtil.isEmpty(string)) {
            Logger.e("CommonUtils", "IntegerValueOf接收到了空值");
            return 0;
        } else {
            return Integer.valueOf(string);
        }
    }

    /**
     * 数字串安全转化为Integer——parseInt
     *
     * @param string 数字串
     * @return Integer数字
     */
    public static Integer mIntegerParseInt(String string) {
        if (CheckUtil.isEmpty(string)) {
            Logger.e("CommonUtils", "IntegerParseInt接收到了空值");
            return 0;
        } else {
            return Integer.parseInt(string);
        }
    }

    /**
     * 数字串安全转化为Float——valueOf
     *
     * @param string 数字串
     * @return Float数字
     */
    public static float mFloatValueOf(String string) {
        if (CheckUtil.isEmpty(string)) {
            Logger.e("CommonUtils", "FloatValueOf接收到了空值");
            return 0;
        } else {
            return Float.valueOf(string);
        }
    }

    /**
     * 数字串安全转化为Float————parseFloat
     *
     * @param string 数字串
     * @return Float数字
     */
    public static float mFloatParseFloat(String string) {
        if (CheckUtil.isEmpty(string)) {
            Logger.e("CommonUtils", "FloatParseFloat接收到了空值");
            return 0;
        } else {
            return Float.parseFloat(string);
        }
    }

    /**
     * 数字串安全转化为Double————parseDouble
     *
     * @param string 数字串
     * @return Double数字
     */
    public static double mDoubleParseDouble(String string) {
        if (CheckUtil.isEmpty(string)) {
            Logger.e("CommonUtils", "DoubleParseDouble接收到了空值");
            return 0;
        } else {
            return Double.parseDouble(string);
        }
    }

    /**
     * 数字串安全转化为Double————valueOf
     *
     * @param string 数字串
     * @return Double数字
     */
    public static double mDoubleValueOf(String string) {
        if (CheckUtil.isEmpty(string)) {
            Logger.e("CommonUtils", "DoubleValueOf接收到了空值");
            return 0;
        } else {
            return Double.valueOf(string);
        }
    }

    /**
     * 是否登录
     *
     * @return true 登录 false 未登录
     */
    public static boolean isLogIn() {
        String logState = SpHelper.getInstance().readMsgFromSp(SpKey.STATE, SpKey.STATE_LOGINSTATE);
        if ("Y".equals(logState)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通知Main页面刷新方法
     *
     * @param isRefreshHomeBottom 以下情况参数值传true
     *                            1. 登录
     *                            2. 实名认证
     *                            <p>
     *                            以下情况参数值传false
     *                            1. 额度申请、被退回、被拒绝等状态
     *                            2. 各种录单
     *                            3. 还款
     *                            4. 积分
     */
    public static void notifyMainDataSetChanged(boolean isRefreshHomeBottom) {
        for (Activity activity : AppApplication.activities) {
            if (activity instanceof MainActivity) {
//                ((MainActivity) activity).setDataChanged(isRefreshHomeBottom);
                break;
            }
        }
    }

    /**
     * 关闭所有app
     */
    public static void finishAllActivitys() {
        for (int i = 0; i < AppApplication.activities.size(); i++) {
            AppApplication.activities.get(i).finish();
        }
    }

    /**
     * 关闭所有Activity除了mainactivity
     */
    public static void finishAllActivityExceptMain() {
        for (int i = 0; i < AppApplication.activities.size(); i++) {
            if (!(AppApplication.activities.get(i) instanceof MainActivity)) {
                AppApplication.activities.get(i).finish();
            }

        }
    }

    /**
     * 是否做过实名认证
     *
     * @return true 实名认证通过 false 没有通过
     */
    public static String isAuthentication() {
        SharedPreferences spUser = SpHelper.getInstance().getSpInstance(SpKey.STATE);
        return spUser.getString(SpKey.STATE_ISAUTHENTICATION, "E");
    }

    /**
     * 获取用户id
     *
     * @return 用户id
     */
    public static String getUserId() {
        String userid = SpHelper.getInstance().readMsgFromSp(SpKey.LOGIN, SpKey.LOGIN_USERID);
        return userid;
    }

    /**
     * 获取custNo
     *
     * @return custNo
     */
    public static String getCustNo() {
        String custNo = SpHelper.getInstance().readMsgFromSp(SpKey.USER, SpKey.USER_CUSTNO);
        return custNo;
    }

    /**
     * 获取用户身份证号
     *
     * @return 用户身份证号
     */
    public static String getIdNo() {
        String idNo = SpHelper.getInstance().readMsgFromSp(SpKey.USER, SpKey.USER_CERTNO);
        return idNo;
    }

    /**
     * 步骤很多的流程 流程结束 关闭中间的activity
     */
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivitys(Activity activity) {
        activityList.add(activity);
    }

    public static void finishActivitys() {
        if (null != activityList) {
            for (int i = 0; i < activityList.size(); i++) {
                activityList.get(i).finish();
            }
        }
    }

    /**
     * 改变同一个TextView中的字体大小和颜色
     *
     * @param textview 要改变的布局
     * @param style    style文件
     * @param start    从第几个开始改变
     * @param end      结束改变
     */
    public static void setTextColorSize(TextView textview, int style, int start, int end) {
        //更改字体大小和颜色
        SpannableStringBuilder builder =
                new SpannableStringBuilder(textview.getText().toString());
        TextAppearanceSpan textAppearanceSpan1 = new
                TextAppearanceSpan(AppApplication.CONTEXT, style);
        builder.setSpan(textAppearanceSpan1, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textview.setText(builder);
    }

    /**
     * 跳转activity
     *
     * @param bundle
     * @param activity
     */
    public static void startActivityByBundle(Context context, Bundle bundle, Class activity) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(context, activity);
        context.startActivity(intent);
    }
}

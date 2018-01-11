package com.bestvike.mvphttpdaodemo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


import com.bestvike.mvphttpdaodemo.base.AppApplication;
import com.bestvike.mvphttpdaodemo.interfaces.SaveImageResult;
import com.bestvike.mvphttpdaodemo.utils.interfaceUtils.SpKey;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.io.File;
import java.io.FileOutputStream;


/**
 * 项目名称：UI
 * 项目作者：hqx
 * 创建日期：2016/3/11 17:48.
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：
 * showToast 自定义toast样式
 *
 * 隐藏、显示菜单(伴随动画效果)
 * hide_top_menu
 * show_top_menu
 *
 * 底部隐藏、弹出菜单(伴随动画效果)
 * hide_menu
 * show_menu
 *
 * dip2px dp转为px
 * getStatusBarHeight 获取状态栏高度
 * ----------------------------------------------------------------------------------------------------
 */
public class UiUtil {

    /**
     * 单例Toast
     *
     * toast禁止用于网络请求返回时的提示，因为用户可能看不到
     */
    public static Toast mToast;
    public static void toast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(AppApplication.CONTEXT, "", Toast.LENGTH_LONG);
        }
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * dip 转换成 px
     * @param dip
     * @return
     */
    public static float dip2px(float dip) {
        DisplayMetrics displayMetrics = AppApplication.CONTEXT.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }
    /**
     * @param dip
     * @param complexUnit {@link TypedValue#COMPLEX_UNIT_DIP} {@link TypedValue#COMPLEX_UNIT_SP}}
     * @return
     */
    public static float toDimension(float dip, int complexUnit) {
        DisplayMetrics displayMetrics = AppApplication.CONTEXT.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(complexUnit, dip, displayMetrics);
    }

    /**
     * 获取状态栏高度
     * @param v
     * @return
     */
    public static int getStatusBarHeight(View v) {
        if (v == null) {
            return 0;
        }
        Rect frame = new Rect();
        v.getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    public static void hideKeyBord(Activity activity){
        if(activity != null && activity.getCurrentFocus() != null){
            ((InputMethodManager)AppApplication.CONTEXT.getSystemService(Activity.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /** 获取设备标识码 */
    public static String getDeviceID(){
        String deviceId = "";
        try{
            TelephonyManager mTelephonyMgr = (TelephonyManager) AppApplication.CONTEXT.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = mTelephonyMgr.getDeviceId();
            SpHelper.getInstance().saveMsgToSp(SpKey.LOGIN, SpKey.LOGIN_DEVICEID, deviceId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return deviceId;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getDeviceWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowMgr = (WindowManager) AppApplication.CONTEXT.getSystemService(Context.WINDOW_SERVICE);
        windowMgr.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
    /**
     * 获取屏幕高度
     * @return
     */
    public static int getDeviceHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowMgr = (WindowManager) AppApplication.CONTEXT.getSystemService(Context.WINDOW_SERVICE);
        windowMgr.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    //防止多次点击记录上次点击时间
    private static long lastClickTime;
    /**
     * 是不是快速点击275ms
     * @return ture 是 false 不是
     */
    public static boolean isFastDoubleClicks() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 275) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 为数字增加逗号
     * @param str 没有逗号的数字，不能带小数点
     * @return 有逗号的数字
     */
    public static String addComma(String str) {
        String reverseStr = new StringBuilder(str).reverse().toString();
        String strTemp = "";
        for (int i = 0; i < reverseStr.length(); i++) {
            if (i * 3 + 3 > reverseStr.length()) {
                strTemp += reverseStr.substring(i * 3, reverseStr.length());
                break;
            }
            strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
        }
        if (strTemp.endsWith(",")) {
            strTemp = strTemp.substring(0, strTemp.length() - 1);
        }
        String resultStr = new StringBuilder(strTemp).reverse().toString();
        return resultStr;
    }



    /**
     * 隐藏银行卡号字段，只显示最后四位 格式 **** **** **** 1234
     */
    public static String hideBankNumber(String string) {
        String cardNo;
        if (null == string || "".equals(string)) {
            cardNo = "卡号格式错误！";
            return cardNo;
        } else {
            StringBuilder sb = new StringBuilder(string);
            sb.replace(0, sb.length() - 4, "**** **** **** ");
            cardNo = sb.toString();
            return cardNo;
        }
    }

    /**
     * 隐藏银行卡号 只显示后四位 格式 ****1234
     *
     * @param string 卡号
     * @return 隐藏后的卡号
     */
    public static String hideBankNumbers(String string) {
        String cardNo;
        if (null == string || "".equals(string)) {
            cardNo = "卡号格式错误！";
            return cardNo;
        } else {
            StringBuilder sb = new StringBuilder(string);
            sb.replace(0, sb.length() - 4, "**** ");
            cardNo = sb.toString();
            return cardNo;
        }
    }

    /*隐藏手机号中间4位*/
    public static String hidePhoneNumber(String string) {
        String phoneNo;
        if (null == string || "".equals(string)) {
            phoneNo = "手机号格式错误！";
            return phoneNo;
        } else {
            StringBuilder sb = new StringBuilder(string);
            for (int i = sb.length() - 8; i < sb.length() - 4; i++) {
                sb.replace(i, i + 1, "*");
            }
            phoneNo = sb.toString();
            return phoneNo;
        }
    }

    /*隐藏身份证号中间位*/
    public static String hideCertNumber(String string) {
        String phoneNo;
        if (null == string || "".equals(string)) {
            phoneNo = "身份证号格式错误！";
            return phoneNo;
        } else {
            StringBuilder sb = new StringBuilder(string);
            for (int i = sb.length() - 15; i < sb.length() - 4; i++) {
                sb.replace(i, i + 1, "*");
            }
            phoneNo = sb.toString();
            return phoneNo;
        }
    }

    /**
     * 获取手机imsi
     */
    public static String getDeviceIMSI() {
        try {
            TelephonyManager mTelephonyMgr = (TelephonyManager) AppApplication.CONTEXT.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            String str = mTelephonyMgr.getSubscriberId();
            return str;
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取设备型号model、设备品牌brand
     * @param type：model返回model
     * @return
     */
    public static String getModelOrBrand(String type){
        Build build = new Build();
        if("model".equals(type)){
            return build.MODEL;
        } else {
            return build.BRAND;
        }
    }

    /**
     * 获取位置区域码lac
     */
    public static String getLac(){
        try {
            TelephonyManager mTelNet = (TelephonyManager) AppApplication.CONTEXT.getSystemService(Context.TELEPHONY_SERVICE);
            GsmCellLocation location = (GsmCellLocation) mTelNet.getCellLocation();
            if(null != location){
                int lac = location.getLac();
                return String.valueOf(lac);
            } else {
                return "";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取基站编号cid
     */
    public static String getCid(){
        try {
            TelephonyManager mTelNet = (TelephonyManager) AppApplication.CONTEXT.getSystemService(Context.TELEPHONY_SERVICE);
            GsmCellLocation location = (GsmCellLocation) mTelNet.getCellLocation();
            if(null != location){
                int cid = location.getCid();
                return String.valueOf(cid);
            } else {
                return "";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 手机是否连接的WiFi
     * @return
     */
    public static boolean isWifi() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) AppApplication.CONTEXT
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetInfo != null
                    && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取BSSID（所连接的WIFI设备的MAC地址）/获取ssid是否隐藏/获取IP地址/mac地址/联网速度/WiFi名称
     * type: bssid-获取bssid；ssidHint-获取ssid是否隐藏；ip-IP地址；mac-mac地址；speed-联网速度；name-WiFi名称
     */
    public static String getUnderWifiMessage(String type){
        try {
            WifiManager wifiManager = (WifiManager) AppApplication.CONTEXT.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if("bssid".equals(type)){
                String bssid = wifiInfo.getBSSID();
                return bssid;
            } else if("ssidHint".equals(type)){
                boolean ssid = wifiInfo.getHiddenSSID();
                if(ssid){
                    return "true";
                } else {
                    return "false";
                }
            } else if("ip".equals(type)){
                int ip = wifiInfo.getIpAddress();
                return String.valueOf(ip);
            } else if("mac".equals(type)){
                return wifiInfo.getMacAddress();
            } else if("speed".equals(type)){
                int speed = wifiInfo.getLinkSpeed();
                return String.valueOf(speed);
            } else {
                return wifiInfo.getSSID();
            }
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 用于Glide加载图片，将url附加header
     * @param url 下载图片的url地址
     * @return 返回带有header的url
     */
    public static GlideUrl addHeader(String url){
        String token = SpHelper.getInstance().readMsgFromSp(SpKey.LOGIN,SpKey.LOGIN_ACCESSTOKEN);
        String channelNo = SpHelper.getInstance().readMsgFromSp(SpKey.LOGIN,SpKey.LOGIN_CHANNELNO);

        Logger.e("channelNo",":"+channelNo);
        String appVersion;
        try {
            appVersion = AppApplication.CONTEXT.getPackageManager().getPackageInfo(AppApplication.CONTEXT.getPackageName(), PackageManager.GET_CONFIGURATIONS).versionName;
        } catch (Exception e) {
            appVersion = "";
        }

        LazyHeaders headers = new LazyHeaders.Builder()
                .addHeader("APPVersion", "AND-P-" + appVersion)
                .addHeader("DeviceModel", "AND-P-" + Build.MODEL)
                .addHeader("DeviceResolution", "AND-P-" + UiUtil.getDeviceWidth() + "," + UiUtil.getDeviceHeight())
                .addHeader("SysVersion", "AND-P-" + Build.VERSION.RELEASE)
                .addHeader("channel", "14")
                .addHeader("channel_no", channelNo)
                .addHeader("access_token", token)
                .addHeader("Authorization", "Bearer" + token)
                .build();

        return new GlideUrl(url, headers);
    }

    /**
     * 保存一个图像文件到本地
     *
     * 注意：
     * 1. 文件存储路径不能乱存，如何存储请看AppApplication
     * 2. 调用前请检查SD卡写入权限
     *
     * @param bitmap 图像
     * @param filePath 保存路径，可以不以“/”结尾
     * @param fileName 文件名，可以不以“.jpg”结尾
     * @param result 存储回调，可以为null
     */
    public void saveImageFile(Bitmap bitmap, String filePath, String fileName, SaveImageResult result){
        if(bitmap == null){
            if(result != null){
                result.onSaveFailed("HUNO_NULL", "没有获取到图像信息，保存失败");
            }
            return;
        }
        boolean sdCardWritePermission =
                AppApplication.CONTEXT.getPackageManager().checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, AppApplication.CONTEXT.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        if(!sdCardWritePermission){
            if(result != null){
                result.onSaveFailed("HUNO_PERMISSION", "没有sd卡读写权限");
            }
            return;
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File imageFile = new File(file.getPath() + "/" +fileName);
            FileOutputStream outStream = null;
            try {
                outStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, outStream);
                if(result != null){
                    result.onSaveSuccess(imageFile, fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
                if(result != null){
                    result.onSaveFailed("HUNO_ERROR", e.getMessage());
                }
            }finally {
                if(outStream != null){
                    try{
                        outStream.flush();
                        outStream.close();
                    }catch (Exception e){}
                }
            }
        }else{
            if(result != null){
                result.onSaveFailed("HUNO_SDCARD_INVALIBLE", "sd卡不可用");
            }
        }
    }

    /**
     * 过滤所有空格
     */
    public static String allWhite(String s) {
        if (s != null && s.contains(" ")) {
            return s.replaceAll(" ", "");
        } else {
            return s;
        }
    }

    /**获取app版本号，当前仅打印Log用，其他地方需要使用再修改*/
    public static void getAppVersion(){
        PackageInfo pi = null;
        String appVersion;
        try{
            PackageManager pm = AppApplication.CONTEXT.getPackageManager();
            pi = pm.getPackageInfo(AppApplication.CONTEXT.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            appVersion = pi.versionName;
        } catch (Exception e){
            e.printStackTrace();
            appVersion = "";
        }
        Logger.e("获取手机设备信息>>>>>>>>", "版本号：" + appVersion);
    }

    /**获取手机版本号，当前仅打印Log用，其他地方需要使用再修改*/
    public static void getSysVersion() {
        String sysVersion;
        try {
            sysVersion = Build.VERSION.RELEASE;
        } catch (Exception e) {
            e.printStackTrace();
            sysVersion = "";
        }
        Logger.e("获取手机设备信息>>>>>>>>", "手机系统版本号：" + sysVersion);
    }

    /**获取设备分辨率，当前仅打印Log用，其他地方需要使用再修改*/
    public static void getDeviceResolution() {
        int width,height;
        try {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager windowMgr = (WindowManager) AppApplication.CONTEXT.getSystemService(Context.WINDOW_SERVICE);
            windowMgr.getDefaultDisplay().getMetrics(dm);
            width = dm.widthPixels;
            height = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            width = 0;
            height = 0;
        }
        Logger.e("获取手机设备信息>>>>>>>>", "手机分辨率：" + width + "," + height);
    }

    /**获取手机型号，当前仅打印Log用，其他地方需要使用再修改*/
    public static void getDeviceModel() {
        String deviceModel;
        try {
            deviceModel = Build.MODEL;
        } catch (Exception e) {
            e.printStackTrace();
            deviceModel = "";
        }
        Logger.e("获取手机设备信息>>>>>>>>", "手机型号：" + deviceModel);
    }

    /** 获取手机分辨率的倍数 */
    public static String getSizeType(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density;
        if (1.5 == density) {
            return "AND480";
        } else if (2.0 == density) {
            return "AND720";
        } else if (3.0 == density) {
            return "AND1080";
        } else {
            return "AND1080";
        }
    }

    /**监听输入金额控件，不能以0开头，只能输入小输掉后两位*/
    private static EditText editText;
    public static void getTextWatcher(EditText editTexts){
        editText = editTexts;
        editText.addTextChangedListener(textMoney);
    }

    /**借款金额输入监听*/
    private static TextWatcher textMoney = new TextWatcher()
    {
        @Override
        public void afterTextChanged(Editable s)
        {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count)
        {
            String str = editText.getText().toString();
            if (str.length() == 1)
            {
                if ("0".equals(s.toString()))
                {
                    toast("借款金额不能以0开头");
                    editText.setText("");
                }
            }
            if (str.length() == 1)
            {
                if (".".equals(s.toString()))
                {
                    editText.setText("");
                    return;
                }
            }
            try
            {
                CheckUtil.counter = 0;
                if (CheckUtil.countStr(str, ".") > 1)
                {
                    editText.setText(str.substring(0, str.length() - 1));
                    editText.setSelection(editText.getText().length());//光标放在文本最后面
                }
                //限制只能输入小数点后2位
                if (str.contains("."))
                {
                    String str1 = str.substring(str.lastIndexOf(".") + 1);
                    if (str1.length() > 2)
                    {
                        editText.setText(str.substring(0, str.lastIndexOf(".") + 3));
                    }
                    editText.setSelection(editText.getText().length());//光标放在文本最后面
                }
            } catch (Exception e)
            {
                Log.e("--", e.toString());
            }
        }
    };
}

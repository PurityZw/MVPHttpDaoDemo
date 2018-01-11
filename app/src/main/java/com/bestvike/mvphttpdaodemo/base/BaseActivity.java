package com.bestvike.mvphttpdaodemo.base;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.bestvike.mvphttpdaodemo.R;
import com.bestvike.mvphttpdaodemo.interfaces.DialogListener;
import com.bestvike.mvphttpdaodemo.netWork.NetResultCallBack;
import com.bestvike.mvphttpdaodemo.utils.CommonUtils;
import com.bestvike.mvphttpdaodemo.utils.SpHelper;
import com.bestvike.mvphttpdaodemo.utils.interfaceUtils.SpKey;
import com.bestvike.mvphttpdaodemo.view.LoadingProgress;

import java.lang.ref.WeakReference;



public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener, NetResultCallBack
{
    private final Handler mHandler = new MyHandler(this);
    private static final int SHOW_ANOTHER_ACTIVITY = 0;

    //子类重写该方法以不锁定横竖屏
    protected boolean isPortrait()
    {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.e("进入Activity", this.getClass().getSimpleName());
        AppApplication.activities.add(this);//将activity加入统一管理类
        if (isPortrait())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏写死
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//键盘弹出时自适应屏幕
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//透明导航栏
        }

    }

    @Override
    protected void onDestroy()
    {
        if (adToast != null)
        {
            adToast.dismiss();
        }
        if (adSelect != null)
        {
            adSelect.dismiss();
        }
        AppApplication.activities.remove(this);
        try
        {
            destroyPresenter();
        } catch (Exception e)
        {
        }
        adToast = null;
        adToastView = null;
        tvToast = null;
        tvUnderstand = null;
        adSelect = null;
        adSelectView = null;
        tvCancel = null;
        tvConfirm = null;
        tvContainer = null;
        super.onDestroy();
        System.gc();
    }

    private AlertDialog adToast;
    private View adToastView;
    private TextView tvToast;
    private TextView tvUnderstand;

    /**
     * 展示一个Dialog提示，每个页面使用一个AlertDialog，防止多层覆盖
     * <p/>
     * 该dialog为提示dialog
     *
     * @param msg 提示内容
     */
    public void showDialog(String msg)
    {
        try
        {
            if (adToast == null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                adToast = builder.create();
            }
            if (adToastView == null)
            {
                adToastView = View.inflate(this, R.layout.dialog_toast, null);
            }
            if (tvToast == null)
            {
                tvToast = (TextView) adToastView.findViewById(R.id.tv_container);
            }
            if (tvUnderstand == null)
            {
                tvUnderstand = (TextView) adToastView.findViewById(R.id.tv_confirm);
                tvUnderstand.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        adToast.dismiss();
                    }
                });
            }

            tvToast.setText(msg);
            adToast.show();

            Window window = adToast.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            Display display = getWindowManager().getDefaultDisplay();
            params.width = (int) (display.getWidth() * 0.7);
            window.setAttributes(params);

            adToast.setContentView(adToastView);
            adToast.setCanceledOnTouchOutside(false);
        } catch (Exception e)
        {
            if (adToast != null)
            {
                adToast.dismiss();
                adToast = null;
            }
            e.printStackTrace();
        }
    }

    private AlertDialog adSelect;
    private View adSelectView;
    private TextView tvContainer;
    private TextView tvConfirm;
    private TextView tvCancel;

    /**
     * 展示一个带有两个选择按钮的提示Dialog
     *
     * @param msg      内容
     * @param listener 回调，不能为null
     */
    public void showDialog(String msg, DialogListener listener)
    {
        try
        {
            if (adSelect == null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                adSelect = builder.create();
            }
            if (adSelectView == null)
            {
                adSelectView = View.inflate(this, R.layout.dialog_warn, null);
            }
            if (tvContainer == null)
                tvContainer = (TextView) adSelectView.findViewById(R.id.tv_container);
            if (tvConfirm == null)
                tvConfirm = (TextView) adSelectView.findViewById(R.id.tv_confirm);
            if (tvCancel == null)
                tvCancel = (TextView) adSelectView.findViewById(R.id.tv_cancel);

            tvContainer.setText(msg);
            tvConfirm.setVisibility(View.VISIBLE);
            tvCancel.setVisibility(View.VISIBLE);
            listener.dialogCallBack(adSelect, tvConfirm, tvCancel);
            adSelect.show();

            Window window = adSelect.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            Display display = getWindowManager().getDefaultDisplay();
            params.width = (int) (display.getWidth() * 0.7);
            window.setAttributes(params);

            adSelect.setContentView(adSelectView);
            adSelect.setCanceledOnTouchOutside(false);
        } catch (Exception e)
        {
            if (adSelect != null)
            {
                adSelect.dismiss();
                adSelect = null;
            }
            e.printStackTrace();
        }
    }

    /*展示网络请求等待的菊花*/
    public LoadingProgress progress;

    public void showProgress(boolean flag)
    {
        showProgress(flag, null);
    }

    public void showProgress(boolean flag, String msg)
    {
        try
        {
            if (flag)
            {
                if (progress == null)
                {
                    progress = LoadingProgress.show(this, msg, true, new DialogInterface.OnCancelListener()
                    {
                        @Override
                        public void onCancel(DialogInterface dialog)
                        {
                            dialog.dismiss();
                            onBackPressed();
                        }
                    });
                }
                if (progress != null)
                {
                    progress.setMsg(msg);
                    if (!progress.isShowing())
                        progress.show();
                }
            } else
            {
                if (progress != null && progress.isShowing())
                {
                    progress.dismiss();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public TitleFragment getHeaderView()
    {
        return (TitleFragment) getSupportFragmentManager().findFragmentById(R.id.header);
    }

    public void setTitle(String title)
    {
        if (getSupportFragmentManager().findFragmentById(R.id.header) != null &&
                getSupportFragmentManager().findFragmentById(R.id.header) instanceof TitleFragment)
            ((TitleFragment) getSupportFragmentManager().findFragmentById(R.id.header)).setTitle(title);
    }

    @Override
    protected void finalize() throws Throwable
    {
        Log.e("内存回收", this.getClass().getSimpleName() + "被回收了");
        super.finalize();
    }

    @Override
    public void onClick(View v)
    {
    }

    @Override
    public void onSuccess(Object response, String flag)
    {
    }

    @Override
    public void onErr(String retFlag, Object response, String flag)
    {
        showProgress(false);
        showDialog(response.toString());
    }

    /**
     * 用于销毁presenter
     */
    protected abstract void destroyPresenter();

    @Override
    public void finish()
    {
        showProgress(false);
        if (adSelect != null && adSelect.isShowing())
        {
            adSelect.dismiss();
        }
        if (adToast != null && adToast.isShowing())
        {
            adToast.dismiss();
        }
        super.finish();
    }

    /**
     * 锁屏计时
     */
    private static class MyHandler extends Handler
    {
        private final WeakReference<BaseActivity> mActivity;

        public MyHandler(BaseActivity activity)
        {
            mActivity = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg)
        {
            try
            {
                System.out.println(msg);
                if (mActivity.get() == null)
                {
                    return;
                }
                super.handleMessage(msg);
                if (msg.what == SHOW_ANOTHER_ACTIVITY)
                {
                    Log.e("--------------->", "计时锁屏！");

                    mActivity.get().sendMessageToDo();
                }
            } catch (Exception e)
            {
                Log.e("handleMessage", e.toString());
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (CommonUtils.isLogIn())
                {
                    resetTime();
                }
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void lockTime()
    {
        SharedPreferences lockSp = SpHelper.getInstance().getSpInstance(SpKey.LOCK);
        final long launchTime = lockSp.getLong(SpKey.LOCK_CURRENT_TIME, 0);
        final long currentTime = System.currentTimeMillis();

        if (launchTime == 0)
        {
            lockSp.edit().putLong(SpKey.LOCK_CURRENT_TIME, currentTime).commit();
        } else
        {
            long diff = currentTime - launchTime;
            if (1000 * 60 * 5 <= diff)
            {
                if (CommonUtils.isLogIn()
                        && "Y".equals(SpHelper.getInstance()
                        .readMsgFromSp(SpKey.STATE, SpKey.STATE_HASGESTURESPAS)))
                {
//                    ValidateGestureActivity.actionStartActivity(this, new Bundle(), BaseActivity.class);
                }
            }
        }
    }

    private void resetTime()
    {
        Log.e("----------------->", "开始倒计时");
        if (mHandler != null)
        {
            mHandler.removeMessages(SHOW_ANOTHER_ACTIVITY);
            Message msg = mHandler.obtainMessage(SHOW_ANOTHER_ACTIVITY);
            mHandler.sendMessageDelayed(msg, 1000 * 60 * 5);
        }
    }

    public void sendMessageToDo()
    {
        if (CommonUtils.isLogIn()
                && "Y".equals(SpHelper.getInstance()
                .readMsgFromSp(SpKey.STATE, SpKey.STATE_HASGESTURESPAS)))
        {
//            ValidateGestureActivity.actionStartActivity(this, new Bundle(), BaseActivity.class);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (CommonUtils.isLogIn())
        {
            resetTime();
            lockTime();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (mHandler != null)
        {
            mHandler.removeMessages(SHOW_ANOTHER_ACTIVITY);
            mHandler.removeCallbacksAndMessages(null);
        }
        final long currentTime = System.currentTimeMillis();
        SharedPreferences.Editor lockEditor = SpHelper.getInstance().getSpEditorInstance(SpKey.LOCK);
        lockEditor.putLong(SpKey.LOCK_CURRENT_TIME, currentTime).commit();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if (mHandler != null)
        {
            mHandler.removeMessages(SHOW_ANOTHER_ACTIVITY);
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}

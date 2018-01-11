package com.bestvike.mvphttpdaodemo.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by use on 2016/4/19.
 * hqx
 * 下拉刷新
 */
public class ScrollRefreshLayout extends SwipeRefreshLayout {

    private boolean isRefresh;

    public ScrollRefreshLayout(Context context) {
        super(context);
        isRefresh = false;
    }
    public ScrollRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        isRefresh = false;
    }

    public void setIsRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if(!isRefresh){
            //直接截断时间传播
            return false;
        }
        return super.onTouchEvent(arg0);
    }


}

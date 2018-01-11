package com.bestvike.mvphttpdaodemo.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bestvike.mvphttpdaodemo.R;


/**
 * Created by Administrator on 2017/6/1.
 * hqx
 * 下拉刷新，上拉加载
 */

public class LoadMoreFooter extends RelativeLayout {

    protected State mState = State.Normal;
    private View mLoadingView;
    private View mNetworkErrorView;
    private View mTheEndView;
    private ProgressBar progressBar;
    private TextView mLoadingTextView;
    private TextView mNoMoreTextView;
    private TextView mNoNetWorkTextView;
    private String loadingHint;
    private String noMoreHint;
    private String noNetWorkHint;

    public LoadMoreFooter(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadMoreFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.load_more_view_sub, this);
        setOnClickListener(null);

        //初始为隐藏状态
        onReset();
    }

    public void setLoadingHint(String hint) {
        this.loadingHint = hint;
    }

    public void setNoMoreHint(String hint) {
        this.noMoreHint = hint;
    }

    public void setNoNetWorkHint(String hint) {
        this.noNetWorkHint = hint;
    }

    public void setViewBackgroundColor(int color) {
        this.setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }

    public State getState() {
        return mState;
    }

    public void setState(State status) {
        setState(status, true);
    }

    public void onReset() {
        onComplete();
    }

    public void onLoading() {
        setState(State.Loading);
    }

    public void onComplete() {
        setState(State.Normal);
    }

    public void onNoMore() {
        setState(State.NoMore);
    }

    public View getFootView() {
        return this;
    }

    public View getFooterChildView() {
        return this.getChildAt(0);
    }

    /**
     * 设置状态
     *
     * @param status
     * @param showView 是否展示当前View
     */
    public void setState(State status, boolean showView) {
        if (mState == status) {
            return;
        }
        mState = status;

        switch (status) {

            case Normal:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                break;
            case Loading:
                setOnClickListener(null);
                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                if (mLoadingView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.loading);
                    mLoadingView = viewStub.inflate();

                    progressBar = (ProgressBar) mLoadingView.findViewById(R.id.progressBar);
                    mLoadingTextView = (TextView) mLoadingView.findViewById(R.id.tv_loading);
                }

                mLoadingView.setVisibility(showView ? VISIBLE : GONE);
                progressBar.setVisibility(VISIBLE);
                mLoadingTextView.setText(TextUtils.isEmpty(loadingHint) ? "正在加载更多数据" : loadingHint);
                break;
            case NoMore:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                if (mTheEndView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.end);
                    mTheEndView = viewStub.inflate();

                    mNoMoreTextView = (TextView) mTheEndView.findViewById(R.id.tv_end);
                } else {
                    mTheEndView.setVisibility(VISIBLE);
                }

                mTheEndView.setVisibility(showView ? VISIBLE : GONE);
                mNoMoreTextView.setText(TextUtils.isEmpty(noMoreHint) ? "已全部加载完毕" : noMoreHint);
                break;
            case NetWorkError:
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.network_error);
                    mNetworkErrorView = viewStub.inflate();
                    mNoNetWorkTextView = (TextView) mNetworkErrorView.findViewById(R.id.tv_error);
                } else {
                    mNetworkErrorView.setVisibility(VISIBLE);
                }

                mNetworkErrorView.setVisibility(showView ? VISIBLE : GONE);
                mNoNetWorkTextView.setText(TextUtils.isEmpty(noNetWorkHint) ? "网络环境异常" : noNetWorkHint);
                break;
            default:
                break;
        }
    }


    public enum State {
        Normal/**正常*/
        , NoMore/**加载到最底了*/
        , Loading/**加载中..*/
        , NetWorkError/**网络异常*/
    }


}

package com.bestvike.mvphttpdaodemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bestvike.mvphttpdaodemo.R;
import com.bestvike.mvphttpdaodemo.utils.CheckUtil;
import com.bestvike.mvphttpdaodemo.view.LoadMoreFooter;
import com.bestvike.mvphttpdaodemo.view.ScrollRefreshLayout;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * 项目名称：
 * 项目作者：hqx
 * 创建日期：2017/4/7 9:08.
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：
 * <p>
 * 1. Adaptor必须在onCreate()方法中初始化
 * <p>
 * 2. getAdaptor()方法子类实现时只能写一行代码，return adaptor;该adaptor在onCreate()中初始化
 * ----------------------------------------------------------------------------------------------------
 */
public abstract class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView recyclerView;
    protected ScrollRefreshLayout swipeRefreshLayout;

    private int lastVisiblePosition;
    private LinearLayout layoutNoData;
    private TextView tvNoData;
    private ImageView ivNodata;

    private boolean isRefreshing = false;
    private boolean isLoadMore = false;
    private boolean hasMore = true;
    private LoadMoreFooter loadMoreFooter;

    /**
     * 请传入RecyclerView的适配器
     */
    protected abstract BaseRecyclerAdapter getAdapter();

    /**
     * 上拉加载更多代码
     */
    protected abstract void loadMore();

    /**
     * 下拉刷新代码
     */
    protected abstract void refresh();

    /**
     * 是否正在刷新,false可停止刷新,每次下拉刷新完毕后需要调用
     */
    public void setRefreshing(boolean flag) {
        isRefreshing = flag;
        setHasMore(true);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(flag);
        }
    }

    /**
     * 上拉加载更多,false可停止加载,每次上啦加载完毕后需要调用
     */
    public void setLoadMore(boolean flag) {
        isLoadMore = flag;
        if (isLoadMore) {
            ifShowFooter(true);
            loadMoreFooter.onLoading();
        } else {
            loadMoreFooter.onComplete();
        }
    }

    /**
     * 是否还有更多数据
     */
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
        isLoadMore = false;
        if (hasMore) {
            loadMoreFooter.onComplete();
        } else {
            loadMoreFooter.onNoMore();
        }
        ifShowFooter(true);
    }

    /**
     * 是否启用下拉刷新
     */
    protected void setRefreshEnable(boolean flag) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(flag);
            swipeRefreshLayout.setIsRefresh(flag);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_loadmore_list, container, false);

        swipeRefreshLayout = (ScrollRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        configRefreshView();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        configRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutNoData = (LinearLayout) view.findViewById(R.id.layout_noData);
        tvNoData = (TextView) view.findViewById(R.id.tv_noData);
        ivNodata = (ImageView) view.findViewById(R.id.iv_noData);
    }

    /**
     * 初始化下拉刷新View
     */
    protected void configRefreshView() {
        swipeRefreshLayout.setOnRefreshListener(this);
        //设置进度动画的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light); //没转一圈换一个颜色
//        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);//设置进度圈的大小，只有两个值：DEFAULT、LARGE
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white); //圈的背景色

        // 设置位置，设置后swipeRefreshLayout.setRefreshing(true);才会显示
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources()  //applyDimension 该处意思是获取24dip
                        .getDisplayMetrics()));

        setRefreshEnable(true);
    }

    /**
     * 初始化RecyclerView
     */
    protected void configRecyclerView() {
        recyclerView.setHasFixedSize(true);
        if (getColumnCount() == 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(activity, getColumnCount()));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        loadMoreFooter = new LoadMoreFooter(activity);
        getAdapter().addFootView(loadMoreFooter);
        recyclerView.setAdapter(getAdapter());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && getAdapter() != null
                        && lastVisiblePosition + 1 == getAdapter().getItemCount()) {
                    if (!isRefreshing && hasMore && !isLoadMore) {
                        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        int visibleItemCount = layoutManager.getChildCount();
                        int totalItemCount = layoutManager.getItemCount();

                        if (visibleItemCount > 0
                                && lastVisiblePosition >= totalItemCount - 1
                                && totalItemCount > visibleItemCount) {
                            setLoadMore(true);
                            loadMore();
                        }

                    } else {
                        getAdapter().notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    lastVisiblePosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof GridLayoutManager) {
                    lastVisiblePosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                }
            }
        });
    }

    public void ifShowFooter(boolean isShow) {
        try {
            if (isShow) {
                loadMoreFooter.setVisibility(VISIBLE);
                loadMoreFooter.getFooterChildView().setVisibility(VISIBLE);
            } else {
                loadMoreFooter.setVisibility(GONE);
                loadMoreFooter.getFooterChildView().setVisibility(GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下拉刷新及上拉加载字段
    @Override
    public void onRefresh() {
        isRefreshing = true;
        refresh();
    }

    public void showNoData(boolean isShow, String msg) {
        if (isShow) {
            layoutNoData.setVisibility(VISIBLE);
        } else {
            layoutNoData.setVisibility(GONE);
        }
        if (!CheckUtil.isEmpty(msg)) {
            tvNoData.setText(msg);
        }
    }

    /**
     * 显示无数据图片和提示语
     *
     * @param isShow
     * @param msg
     * @param imgid  图片id
     */
    public void showNoData(boolean isShow, String msg, int imgid) {
        if (isShow) {
            layoutNoData.setVisibility(VISIBLE);
        } else {
            layoutNoData.setVisibility(GONE);
        }
        tvNoData.setText(msg);
        if (0 != imgid) {
            ivNodata.setImageResource(imgid);
        }
    }

    public int getColumnCount() {
        return 1;
    }

    @Override
    public void onDestroy() {
        if (getAdapter() != null) {
            getAdapter().release();
        }
        recyclerView = null;
        swipeRefreshLayout = null;
        tvNoData = null;
        layoutNoData = null;
        super.onDestroy();
    }

    @Override
    public void onErr(String retFlag, Object response, String flag) {
        super.onErr(retFlag, response, flag);
        setRefreshing(false);
        setLoadMore(false);
    }
}

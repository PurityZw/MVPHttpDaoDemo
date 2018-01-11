package com.bestvike.mvphttpdaodemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestvike.mvphttpdaodemo.R;
import com.bestvike.mvphttpdaodemo.utils.CheckUtil;


/**
 * 项目名称：
 * 项目作者：hqx
 * 创建日期：2017/4/17 17:07.
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------------------------------------------------------
 */
public class TitleFragment extends BaseFragment{

    public static final int NO_IMAGE = 0;
    public static final int NOT_CHANGE = 1;

    protected int leftImageId = NO_IMAGE;//左侧图片的id
    protected int rightImageId = NO_IMAGE;//右侧图片的id
    protected String rightText;//右侧文字
    protected String title;//标题栏文字

    protected ImageView header_iv_left;//左侧图片控件
    protected View.OnClickListener ivLeftListener;//左侧按钮点击侦听

    protected ImageView header_iv_right;//右侧图片控件
    protected View.OnClickListener ivRightListener;//右侧按钮点击侦听

    protected TextView header_tv_title;//标题
    protected View.OnClickListener tvTitleListener;//标题点击侦听

    protected TextView header_tv_right;//右侧文字控件
    protected View.OnClickListener tvRightListener;//右侧文字点击侦听

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.header, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //左侧按钮的图片和点击侦听
        header_iv_left = (ImageView) view.findViewById(R.id.head_left);
        header_iv_left.setImageResource(R.drawable.icon_title_leftarrow_black);
        if(leftImageId != NO_IMAGE && leftImageId != NOT_CHANGE){
            header_iv_left.setImageResource(leftImageId);
        }
        if(ivLeftListener == null){
            //左键默认为回退键
            header_iv_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }else{
            header_iv_left.setOnClickListener(ivLeftListener);
        }

        //右侧图片设置及点击侦听
        header_iv_right = (ImageView) view.findViewById(R.id.head_right);
        header_iv_right.setOnClickListener(ivRightListener);
        if(rightImageId == NO_IMAGE){
            header_iv_right.setOnClickListener(null);
        }
        if(rightImageId != NO_IMAGE && rightImageId != NOT_CHANGE){
            header_iv_right.setVisibility(View.VISIBLE);
            header_iv_right.setImageResource(rightImageId);
        }

        //标题文字及点击侦听
        header_tv_title = (TextView) view.findViewById(R.id.head_title);
        if(title != null)
            header_tv_title.setText(title);
        if(tvTitleListener != null)
            header_tv_title.setOnClickListener(tvTitleListener);

        //右侧文字设置及点击侦听
        header_tv_right = (TextView) view.findViewById(R.id.head_rightText);
        if(!CheckUtil.isEmpty(rightText)){
            header_tv_right.setText(rightText);

            //有文字才能设置点击侦听
            if(tvRightListener != null)
                header_tv_right.setOnClickListener(tvRightListener);
        }
    }

    /** 设置左侧图片、右侧图片、右侧文字、标题栏 */
    public void setLeftImage(int resourceId, View.OnClickListener listener) {
        this.leftImageId = resourceId;
        this.ivLeftListener = listener;
        if(header_iv_left != null){
            if(resourceId != NO_IMAGE && resourceId != NOT_CHANGE)
                header_iv_left.setImageResource(resourceId);
            if(listener != null)
                header_iv_left.setOnClickListener(listener);

            if(leftImageId == NO_IMAGE && ivLeftListener == null){
                header_iv_left.setImageResource(0);
                header_iv_left.setOnClickListener(null);
            }
        }
    }
    public void setRightImage(int resourceId, View.OnClickListener listener) {
        this.rightImageId = resourceId;
        this.ivRightListener = listener;
        if(header_iv_right != null){
            header_iv_right.setOnClickListener(listener);
            if(rightImageId == NO_IMAGE){
                header_iv_right.setOnClickListener(null);
            }
            if(rightImageId != NO_IMAGE && rightImageId != NOT_CHANGE){
                header_iv_right.setImageResource(resourceId);
            }
        }
    }
    public void setRightText(String text, View.OnClickListener listener) {
        this.rightText = text;
        this.tvRightListener = listener;
        if(header_tv_right != null){
            header_tv_right.setOnClickListener(listener);
            if(!CheckUtil.isEmpty(text)){
                header_tv_right.setText(text);
            }else{
                header_tv_right.setOnClickListener(null);
            }
        }
    }

    public void setTitle(String title){
        this.title = title;
        if(header_tv_title != null){
            if(title != null)
                header_tv_title.setText(title);
        }
    }
    public void setTitle(View.OnClickListener listener) {
        this.tvTitleListener = listener;
        if(header_tv_title != null){
            header_tv_title.setOnClickListener(listener);
        }
    }

    @Override
    protected void destroyPresenter() {

    }
}

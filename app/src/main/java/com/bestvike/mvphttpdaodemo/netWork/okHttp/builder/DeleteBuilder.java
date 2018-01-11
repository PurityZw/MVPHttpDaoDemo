package com.bestvike.mvphttpdaodemo.netWork.okHttp.builder;

import android.util.Log;


import com.bestvike.mvphttpdaodemo.netWork.okHttp.request.DeleteStringRequest;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * 功能：
 * 创建：hqx
 * 日期: on 16/5/13 09:36
 */
public class DeleteBuilder extends   OkHttpRequestBuilder {

    private String content;
    private MediaType mediaType;


    public DeleteBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    public DeleteBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }
    public DeleteBuilder url(String url) {
        this.url = url;
        return this;
    }

    public DeleteBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    public DeleteBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public DeleteBuilder addParams(String key, String val) {
        if (this.params == null)
        {
            params = new LinkedHashMap<String, String>();
        }
        params.put(key, val);
        return this;
    }

    public DeleteBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public DeleteBuilder addHeader(String key, String val) {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<String, String>();
        }
        headers.put(key, val);
        return this;
    }
    @Override
    public RequestCall build()
    {
        Log.e("请求报文体", url + "");
        return new DeleteStringRequest(url, tag, params, headers, content, mediaType).build();
    }


}

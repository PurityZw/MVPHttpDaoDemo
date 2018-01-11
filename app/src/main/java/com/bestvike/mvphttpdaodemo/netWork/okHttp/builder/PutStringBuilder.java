package com.bestvike.mvphttpdaodemo.netWork.okHttp.builder;



import com.bestvike.mvphttpdaodemo.netWork.okHttp.request.PutRequest;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.request.RequestCall;
import com.bestvike.mvphttpdaodemo.utils.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/** 
 **********************
 * PutStringBuilder.java
 * package com.zhy.http.okhttp.builder;
 * com.zhy.http.okhttp.builder
 * 
 * hqx
 * public class PutStringBuilder{ }
 * PutStringBuilder
 *************************
 */
public class PutStringBuilder extends OkHttpRequestBuilder
{
    private String content;
    private MediaType mediaType;


    public PutStringBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    public PutStringBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }


    @Override
    public RequestCall build()
    {
        Logger.e("请求报文体", url+" \n headers： "+headers+"\ncontent:"+content);
        return new PutRequest(url, tag, params, headers, content, mediaType).build();
    }

    @Override
    public PutStringBuilder url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public PutStringBuilder tag(Object tag)
    {
        this.tag = tag;
        return this;
    }

    @Override
    public PutStringBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    @Override
    public PutStringBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<String, String>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PutStringBuilder headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    @Override
    public PutStringBuilder addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<String, String>();
        }
        headers.put(key, val);
        return this;
    }
}
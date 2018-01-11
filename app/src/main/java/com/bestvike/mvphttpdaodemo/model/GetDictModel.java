package com.bestvike.mvphttpdaodemo.model;

import com.bestvike.mvphttpdaodemo.base.BaseModel;
import com.bestvike.mvphttpdaodemo.bean.GetDictBeanRtn;
import com.bestvike.mvphttpdaodemo.netWork.BaseUrl;
import com.bestvike.mvphttpdaodemo.netWork.HttpDAO;
import com.bestvike.mvphttpdaodemo.netWork.NetResultCallBack;
import com.bestvike.mvphttpdaodemo.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by use on 2017/7/19.
 * 实例，model  一个字典项查询
 */

public class GetDictModel extends BaseModel {

    public GetDictModel(NetResultCallBack callBack) {
        super(callBack);
    }

    public void get(){
        HttpDAO httpDAO = new HttpDAO(callBack());
        Map<String,String> map = new HashMap<String, String>();
        httpDAO.get(BaseUrl.URL_GETDICT,map,BaseUrl.URL_GETDICT);
    }


    @Override
    public Object parseResponse(String response) {
        GetDictBeanRtn getDictBeanRtn = JsonUtils.json2Class(response,GetDictBeanRtn.class);
        return getDictBeanRtn;
    }
}

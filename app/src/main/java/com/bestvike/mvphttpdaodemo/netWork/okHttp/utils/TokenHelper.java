package com.bestvike.mvphttpdaodemo.netWork.okHttp.utils;



import com.bestvike.mvphttpdaodemo.bean.RequestParamsForToken;
import com.bestvike.mvphttpdaodemo.bean.Token;
import com.bestvike.mvphttpdaodemo.netWork.BaseUrl;
import com.bestvike.mvphttpdaodemo.netWork.HttpDAO;
import com.bestvike.mvphttpdaodemo.netWork.NetResultCallBack;
import com.bestvike.mvphttpdaodemo.netWork.okHttp.callback.DaoStringCallBack;
import com.bestvike.mvphttpdaodemo.utils.CheckUtil;
import com.bestvike.mvphttpdaodemo.utils.EncryptUtil;
import com.bestvike.mvphttpdaodemo.utils.JsonUtils;
import com.bestvike.mvphttpdaodemo.utils.SpHelper;
import com.bestvike.mvphttpdaodemo.utils.UiUtil;
import com.bestvike.mvphttpdaodemo.utils.interfaceUtils.SpKey;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：ysApprove
 * 项目作者：hqx
 * ----------------------------------------------------------------------------------------------------
 * 文件描述：token失效后重新获取token的帮助类
 * ----------------------------------------------------------------------------------------------------
 */
public class TokenHelper implements NetResultCallBack {

    public static final String TOKEN_FLAG = "request_token_flag#";
    public final Map<String, RequestParamsForToken> requestMap = new HashMap<>();

    /**
     * 获取token
     *
     * 若refresh为空，则是请求token;否则刷新token
     *
     * 若callBack不为null，则请求token成功后不需要继续请求，而是回调给客户端
     */
    public void getToken(String flag, NetResultCallBack callBack){
        flag = TOKEN_FLAG + flag;

        // 请求token
        HttpDAO dao;
        if(callBack == null){
            dao = new HttpDAO(this);
        }else{
            dao = new HttpDAO(callBack);
        }
        String userid = SpHelper.getInstance().readMsgFromSp(SpKey.LOGIN, SpKey.LOGIN_USERID);
        Map<String, String> map = new HashMap<>();
        map.put("client_secret", SpHelper.getInstance().readMsgFromSp(SpKey.LOGIN,SpKey.LOGIN_CLIENTSERCERT));
        map.put("grant_type", "client_credentials");
        map.put("client_id", EncryptUtil.simpleEncrypt("AND-" + UiUtil.getDeviceID() + "-" + userid));
        dao.get(BaseUrl.URL_TOKEN, map, flag);
    }

    public boolean isTokenOutOfDate(String response){
        return !CheckUtil.isEmpty(response) && response.contains("invalid_token");
    }

    @Override
    public void onSuccess(Object response, String flag) {
        Token bean = JsonUtils.json2Class((String) response, Token.class);
        SpHelper.getInstance().saveMsgToSp(SpKey.LOGIN,SpKey.LOGIN_ACCESSTOKEN,bean.access_token);
        flag = flag.substring(flag.indexOf("#") + 1);
        RequestParamsForToken requestParams = requestMap.get(flag);
        if(requestParams == null) return;
        HttpDAO dao = new HttpDAO(requestParams.callBack.callBack);
        if("POST".equals(requestParams.method)){
            dao.post(requestParams.url, requestParams.params, flag);
        }else if("GET".equals(requestParams.method)){
            dao.get(requestParams.url, (HashMap)requestParams.params, flag);
        }else if("PUT".equals(requestParams.method)){
            dao.put(requestParams.url, requestParams.params, flag);
        }
        requestParams.release();
    }

    @Override
    public void onErr(String retFlag, Object response, String flag) {
        flag = flag.substring(flag.indexOf("#") + 1);
        RequestParamsForToken requestParams = requestMap.remove(flag);
        try{
            if(!(requestParams.callBack.callBack instanceof TokenHelper)){
                requestParams.callBack.callBack.onErr(retFlag, response, flag);
            }
            requestParams.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private TokenHelper(){}
    private static final class SingleTonHelper{
        private static final TokenHelper instance = new TokenHelper();
    }
    public static TokenHelper getInstance(){
        return SingleTonHelper.instance;
    }

    /** 保存请求参数。用于重新发起请求 */
    public void saveRequestParams(String url, Object params, String flag, String method, DaoStringCallBack callBack){
        if(!flag.startsWith(TokenHelper.TOKEN_FLAG) && !HttpDAO.NO_TOKEN.contains(url)){
            RequestParamsForToken rpft = new RequestParamsForToken();
            rpft.url = url;
            rpft.params = params;
            rpft.flag = flag;
            rpft.method = method;
            rpft.callBack = callBack;
            TokenHelper.getInstance().requestMap.put(flag, rpft);
        }
    }
}

package com.bestvike.mvphttpdaodemo.presenter;

import com.bestvike.mvphttpdaodemo.base.BasePresenter;
import com.bestvike.mvphttpdaodemo.bean.GetDictBeanCodeName;
import com.bestvike.mvphttpdaodemo.bean.GetDictBeanRtn;
import com.bestvike.mvphttpdaodemo.iview.GetDictView;
import com.bestvike.mvphttpdaodemo.model.GetDictModel;
import com.bestvike.mvphttpdaodemo.utils.Logger;

import java.util.List;

/**
 * Created by use on 2017/7/19.
 */

public class GetDictPresenter extends BasePresenter<GetDictView> {

    public static List<GetDictBeanCodeName> POSITION;//职务
    public static List<GetDictBeanCodeName> MAIL_ADDR;//房产地址
    public static List<GetDictBeanCodeName> COM_KIND;//行业性质
    public static List<GetDictBeanCodeName> POSITION_OPT;//从业性质
    public static List<GetDictBeanCodeName> EDU_TYP;//学历
    public static List<GetDictBeanCodeName> LOCAL_RESID;//户口性质
    public static List<GetDictBeanCodeName> MARR_STS;//婚姻状况
    public static List<GetDictBeanCodeName> CURR_SITUATION;//有无贷款
    public static List<GetDictBeanCodeName> PPTY_LIVE_OPT;//房产地址
    public static List<GetDictBeanCodeName> RELATION;//关系

    public GetDictPresenter(GetDictView view) {
        super(view);
    }

    public void get(){
        new GetDictModel(this).get();
    }

    @Override
    public void onSuccess(Object response, String flag) {
        Logger.e("-----","字典查询成功"+response.toString());
        GetDictBeanRtn getDictBeanRtn = (GetDictBeanRtn) new GetDictModel(this).parseResponse(response.toString());
        POSITION = getDictBeanRtn.POSITION;
        MAIL_ADDR = getDictBeanRtn.MAIL_ADDR;
        COM_KIND = getDictBeanRtn.COM_KIND;
        POSITION_OPT = getDictBeanRtn.POSITION_OPT;
        EDU_TYP = getDictBeanRtn.EDU_TYP;
        LOCAL_RESID = getDictBeanRtn.LOCAL_RESID;
        MARR_STS = getDictBeanRtn.MARR_STS;
        CURR_SITUATION = getDictBeanRtn.CURR_SITUATION;
        PPTY_LIVE_OPT = getDictBeanRtn.PPTY_LIVE_OPT;
        RELATION = getDictBeanRtn.RELATION;
        Logger.e("--------", "字典请求数据集合：" + "职务:" + POSITION.size() + ",学历：" + EDU_TYP.size() + ",关系：" + RELATION.size()
                + ",从业性质：" + POSITION_OPT.size() + ",行业性质：" + COM_KIND.size() + ",户口性质:" + LOCAL_RESID.size() + ",房产地址：" + MAIL_ADDR.size()
                + ",婚姻状况：" + MARR_STS.size() + ",房产状况：" + PPTY_LIVE_OPT.size() + ",有无贷款" + CURR_SITUATION.size());

        view.getCict();
    }
}

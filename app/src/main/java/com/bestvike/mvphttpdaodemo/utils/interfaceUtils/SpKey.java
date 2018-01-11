package com.bestvike.mvphttpdaodemo.utils.interfaceUtils;

/**
 * Created by zhangjie on 2017/6/5.
 * 定义一个接口，用来存放存取SP时的key值
 * 外部使用时，直接 SpKey. 即可
 * ex：SpKey.LOGIN
 */

public interface SpKey
{
    /**
     * 登录相关信息
     */
    String LOGIN = "LOGIN";
    String LOGIN_CLIENTSERCERT = "clientSecret";//请求token用
    String LOGIN_USERID = "userId";//用户id
    String LOGIN_INPUTID = "inputId";//用户手输的id
    String LOGIN_MOBILE = "login_mobile";//绑定手机号
    String LOGIN_ACCESSTOKEN = "accessToken";//接口请求中用到的token
    String LOGIN_USERPROVIDER = "userProvider";//用户身份，是不是海尔会员，Y：是海尔会员
    String LOGIN_DEVICEID = "deviceId";//设备号
    String LOGIN_CHANNELNO = "channelNo";//渠道号

    String LOGIN_BR_LOGIN = "brLogin";//百融登录事件

    /**
     * 实名相关信息，启动页会清除该sp文件
     */
    String USER = "USER";
    String USER_CUSTNO = "custNo";//客户编号
    String USER_CUSTNAME = "custName";//用户姓名
    String USER_CERTTYPE = "certType";//证件类型
    String USER_CERTNO = "certNo";//证件号 身份证号
    String USER_CARDNO = "cardNo";//银行卡号
    String USER_MOBILE = "mobile";//实名认证手机号
    String USER_ACCTNO = "acctNo";//账号
    String USER_ACCTNAME = "acctName";//账户名
    String USER_ACCTBANKNO = "acctBankNo";//银行代码
    String USER_ACCTBANKNAME = "acctBankName";//银行名称
    String USER_ACCTPROVINCE = "acctProvince";//开户省
    String USER_ACCTCITY = "acctCity";//开户市
    String USER_ACCTAREA = "acctArea";//开户区
    String USER_ACCBCHCDE = "accBchCde";//支行代码
    String USER_ACCBANKNAME = "accBchName";//支行名称

    String USER_EDU_CRDAMT = "userCrdAmt";//额度总金额
    String USER_EDU_CRDNORAMT = "userCrdNorAmt";//自主支付总额度-现金贷总额度
    String USER_EDU_CRDCOMAVAILANT = "userCrdComAvailAnt";//受托支付可用额度-商品代可用额度
    String USER_EDU_CRDNORAVAILAMT = "userCrdNorAvailAmt";//自主支付可用额度-现金贷可用额度
    String USER_EDU_CRDCOMAMT = "userCrdComAmt";//受托支付总额度-商品代总额度
    String USER_EDU_CRDSTS = "userCRDSTS";//额度状态
    String USER_EDU_OUTSTS = "userOUTSTS";//当前申请状态
    String USER_EDU_FLAG = "userFLAG";//是否可以发起额度申请
    String USER_EDU_APPLTYPE = "userAPPLTYPE";//01贷款申请 02额度申请
    String USER_EDU_CRDSEQ = "userCRDSEQ";//在途申请流水号
    String USER_EDU_EXPECTCREDIT = "userEXPECTCREDIT";//额度期望值
    String USER_EDU_AMOUNT = "userAmount";//近七日待还总金额
    String USER_EDU_TOTALPOINTS = "userTotalPoints";//集团总积分
    String USER_EDU_JUDGELINES = "USER_EDU_JUDGELINES";//用作判断是否有额度Y时有额度 N没有额度

    /**
     * 位置相关信息，启动页会清除该sp文件
     */
    String LOCATION = "LOCATION";
    String LOCATION_LON = "longitude";//经度
    String LOCATION_LAT = "latitude";//纬度
    String LOCATION_COUNTRYNAME = "countryName";//国家名称
    String LOCATION_COUNTRYCODE = "countryCode";//国家代码
    String LOCATION_PROVINCENAME = "provinceName";//省名称
    String LOCATION_PROVINCECODE = "provinceCode";//省代码
    String LOCATION_CITYNAME = "cityName";//市名称
    String LOCATION_CITYCODE = "cityCode";//市代码
    String LOCATION_AREANAME = "areaName";//区名称
    String LOCATION_AREACODE = "areaName";//区代码

    /**
     * 信息状态标识（是否登录，实名，有无支付密码等）等信息，启动页会清除该sp文件
     */
    String STATE = "STATE";
    String STATE_LOGINSTATE = "loginState";//是否登录（登录状态）Y：类型，N：已登录
    String STATE_ISAUTHENTICATION = "isAuthentication";//是否做过实名认证。Y：有，N：没有，E：查询失败
    String STATE_HASGESTURESPAS = "hasGesturesPas";//是否有手势密码。Y：有，N：没有，E：查询失败
    String STATE_HASPAYPAS = "hasPayPas";//是否有支付密码。Y：有，N：没有，E：查询失败

    /**
     * 流程标识相关信息，流程结束后一定clean该文件下的所有标识，启动页也会清除该sp文件，MainActivity的onStart中也会清除
     * 包括流程中可能需要保存的信息，比如流水号等
     */
    String FLAG = "FLAG";
    String FLAG_IFCHANGEPAYPASS = "ifChangePayPass";//标记是否从录单修改支付密码Y 是  N 不是
    String FLAG_APPLSEQ = "applSeq";//流程中的流水号
    String FLAG_TYPCDE = "typCde";//流程中的贷款品种代码
    String FLAG_ORDERNO = "orderNo";//流程中的订单号
    String FLAG_APPLCDE = "applCde";//申请编号
    String FLAG_FACE = "faceFlag";//标记什么途径进入人脸识别;MentionTheForehead 提额 , PersonalCenter  个人中心
    String FLAG_EXPECTCREDIT = "expectcredit";//可能用到的用户在额度申请或录单时，输入的期望额度
    String FLAF_ISSPECIAL = "isSpecial";//是不是特殊状态的额度被退回，Y：是
    String FLAG_FUND = "fund";//魔蝎公积金
    String FLAG_BANK = "bank";//魔蝎银联

    /**
     * 系统配置项（默认参数），启动页会清除该sp文件
     */
    String CONFIGURE = "CONFIGURE";
    String CONFIGURE_MAXNUM = "maxNum";//商品默认的可选个数
    String CONFIGURE_MINLOANMONEY = "minLoanMoney";//没有申请额度用户最低借款金额
    String CONFIGURE_MAXLOANMONEY = "maxLoanMoney";//没有申请额度用户最高借款金额
    String CONFIGURE_GOODSMINMONER = "goodsMinMoney";//没有申请额度用户商品最低借款金额
    String CONFIGURE_GOODSMAXMONER = "goodsMaxMoney";//没有申请额度用户商品最高借款金额

    /**
     * 其他未分类信息
     */
    String OTHER = "OTHER";
    String OTHER_PHOTOID = "photoId";//广告开屏页图片的id

    /**
     * 锁屏计时相关
     */
    String LOCK = "LOCK";
    String LOCK_CURRENT_TIME = "currentTime";//
}

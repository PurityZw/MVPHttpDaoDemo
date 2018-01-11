package com.bestvike.mvphttpdaodemo.netWork;

/**
 * Created by Administrator on 2017/6/1.
 */

public class BaseUrl {
    //接口地址
    /**
     * 生产环境https
     */
//    public static final String baseUrl = "https://app.haiercash.com:6688/";

    /**
     * 测试环境
     */
    public static final String baseUrl = "http://10.164.194.123:9000/";//测试  环境

    /**
     * 新封版测试环境
     * 内部人员测试可以用ip地址访问，速度快；给外部人员打包需要用域名访问
     */
//    public static final String baseUrl = "http://10.164.194.123:9001/";//封测2017-5-10 添加
//    public static final String baseUrl = "https://testpm.haiercash.com/";

    public static final String URL_TOKEN = "app/appserver/token";
    //6.82.	(GET)检测app版本
    public static final String URL_CHECKVERSION = "app/appmanage/version/check";
    //版本更新
    public static final String URL_APP_UPDATE = "app/appmanage/version/downloadBySource";
    //用户注册
    public static final String URL_SAVE_UAUTH_USERS = "app/appserver/uauth/saveUauthUsers";
    //2.5.	+(GET)发送短信验证码(免token)
    public static final String URL_SMS_SEND_VERIFY = "app/appserver/smsSendVerify";
    //2.5.	(POST)校验短信验证码
    public static final String URL_SMS_VERIFY = "app/appserver/smsVerify";
    //登录
    public static final String URL_CUSTOMER_LOGIN = "app/appserver/customerLogin";
    //刷新图片验证码
    public static final String URL_HAIER_CAPTCHA = "app/appserver/uauth/haierCaptcha";
    //3.4.23.(POST) 校验验证码并绑定设备号
    public static final String URL_VERIFY_AND_BIND_DEVICE_ID = "app/appserver/verifyAndBindDeviceId";
    //是否设置过手势密码和支付密码**
    public static final String URL_VALIDATEUSERFLAG = "app/appserver/uauth/validateUserFlag";
    //3.4.5.(GET)手势密码验证
    public static final String URL_VALIDATE_GESTURE = "app/appserver/uauth/validateGesture";
    //手势密码设置**
    public static final String URL_GESTURE = "app/appserver/uauth/gesture";
    //账号查询（是否注册过）
    public static final String URL_ISREGISTER = "app/appserver/uauth/isRegister";
    //1.5.(POST)意见反馈
    public static final String URL_FEEDBACK = "app/appmanage/feedback/save";
    //1.1.1.APP广告查询接口(免token)
    public static final String URL_GET_ADVERTISE = "app/appserver/ad/getAdInfoCheck";
    //支付密码验证**
    public static final String URL_VALIDATEPAYPASSWD = "app/appserver/uauth/validatePayPasswd";
    //    6.1.120.	(POST)保存身份证信息
    public static final String URL_SAVE_CARD_MSG = "app/appserver/saveCardMsg";
    //3.1.29.(GET)查询客户实名认证信息（根据USERID）(APP_PERSON)(CRM17)
    public static final String URL_GET_CUSTINFO = "app/appserver/crm/cust/queryPerCustInfo";
    //6.126.(post)信息完整查询接口
    public static final String url_CheckIfMsgComplete = "/checkIfMsgComplete";
    //6.35.	(GET)字典项查询
    public static final String URL_GETDICT = "app/appserver/cmis/getDict";
    //6.116.APP广告图片下载接口
    public static final String URL_ADVERTISE_DOWNLOAD = "app/appserver/ad/getAdImag";
    //6.102.(GET) 系统参数列表查询
    public static final String URL_SELECT_BY_PARAMS = "app/appserver/appmanage/param/selectByParams";
    //3.4.10.(PUT)头像设置
    public static final String URL_SETHEADPHOTO = "app/appserver/uauth/avatarUrl";
    //3.4.2.(GET) 获取图片 此接口不做封装 使用原接口: /app/uauth/getUserPic
    public static final String URL_GETUSERPIC = "app/uauth/getUserPic";
    //3.1.17(GET)查询指定客户的所有银行卡（根据客户编号）列表(APP)
    public static final String URL_GETBANKCARD = "/app/appserver/crm/cust/getBankCard";
    /*帮助中心*/
    //1.1.	(GET)查询帮助信息列表
    public static final String URL_HELPCENTER = "app/appmanage/help/pageByHelpType";
    //帮助信息详情
    public static final String URL_HELPCENTER_DETAIL = "app/appmanage/help/";
    //支付密码修改(验证码)**
    public static final String URL_UPDATEPAYPASSWD = "app/appserver/uauth/updatePayPasswd";
    //    3.4.17.	(PUT) 客户支付密码设置、修改（实名认证+验证码）
    public static final String URL_CUST_VERIFY_UPDATEPAYPWD = "/app/appserver/uauth/custVerifyUpdatePayPwd";
    //    3.4.7.	 (PUT)支付密码设置
    public static final String URL_PAYPASSWD = "/app/appserver/uauth/payPasswd";
    // 6.1.131.	(GET)查询首页头部信息展示(实际上是在6.1.102额度校验接口的基础上增加了两个字段，因此额度校验接口可以调这个)
    public static final String URL_EDUAPPL = "app/appserver/getHomeUserMsg";
    //    6.1.92.	(GET) 身份证格式校验接口(免token)
    public static final String URL_CHECKIDNO = "/app/appserver/validate/checkIdNo";
    //    3.1.5.(GET)查询指定银行卡的所有信息(CRM64)
    public static final String URL_GETBANKINFO = "/app/appserver/crm/cust/getBankInfo";
    //    3.1.6.(GET)查询所有支持的银行列表(CRM12)
    public static final String URL_GETBANKLIST = "/app/appserver/crm/cust/getBankList";
    //6.1.25.(GET)查询银行支行列表
    public static final String URL_BRANCH_LIST = "app/appserver/cmis/bankList";
    //    3.1.1. (POST)修改保存客户所有扩展信息(CRM85)
    public static final String URL_SAVEAllCUSTEXTINFO = "app/appserver/crm/cust/saveAllCustExtInfo";
    //3.2.13.(GET)商品查询（包含图片）
    public static final String URL_GET_GOODS_ADD_IMG = "app/appserver/pub/gm/getGoodsAddImgByCode";
    //6.1.116.	查询CRM中客户扩展信息（二）
    public static final String URL_GETAllCUSTEXTINFO = "app/appserver/getAllCustExtInfo";
    //3.1.3. (POST)新增/修改 联系人(CRM6)
    public static final String URL_SAVECUSTFCiCUSTCONTACT = "app/appserver/crm/saveCustFCiCustContact";
    //联系人查询
    public static final String URL_findCustFCiCustContactByCustNo = "app/appserver/crm/cust/findCustFCiCustContactByCustNo";
    //    3.4.11.	 (GET) 实名认证
    public static final String URL_IDENTIFY = "app/appserver/uauth/identify";
    //3.1.7.(POST)验证并新增实名认证信息(CRM66)
    public static final String URL_F_CI_CUST_REAL_THREE_INFO = "app/appserver/crm/cust/fCiCustRealThreeInfo";
    //3.2.16. (GET)根据SKU码和区编码查询库存
    public static final String URL_QUERY_INVENTORY = "app/appserver/pub/gm/queryInventoryBySkucode";
    //3.1.25.	(GET)查询客户的准入资格(APP+APPSERVER) (CRM28)
    public static final String URL_GETCUSTISPASS = "/app/appserver/crm/cust/getCustIsPass";
    //6.1.94.(GET)通过姓名+身份证号查询额度申请审批进度(返回最新一条贷款申请的审批进度)
    public static final String URL_NEWEST_PROCESS = "app/appserver/cmis/approvalProcessByCust";
    //6.1.93.(GET)通过流水号查询贷款或额度申请审批进度
    public static final String URL_APPLSEQ_PROCESS = "app/appserver/cmis/approvalProcessBySeq";
    //设置默认还款卡
    public static final String URL_SAVE_DEFAULT_CARD = "app/appserver/crm/cust/saveDefaultBankCard";
    //删除银行卡  2016-12-7 更换删除银行卡列表接口调用后台
    public static final String URL_DELETE_BANK_CARD = "app/appserver/deleteBankCard";
    //影像下载
    public static final String URL_ATTACHPIC = "app/appserver/attachPic";
    //6.2.2.	(GET)影像分类型查询数量-个人版
    public static final String URL_ATTACHSEARCHPERSON = "app/appserver/attachSearchPerson";
    //6.2.11.	(GET)查询本次提额影像信息-个人版
    public static final String URL_GET_TE_ATTACH_SEARCH_PERSON = "app/appserver/getTeAttachSearchPerson";
    //6.2.5.	(GET)影像列表按类型查询-个人版
    public static final String URL_ATTACHTYPESEARCHPERSON = "app/appserver/attachTypeSearchPerson";

    //修改登录密码（验证码）**
    public static final String URL_CUST_UPDATE_PWD = "app/appserver/uauth/custUpdatePwd";
    //修改登录密码（实名认证）app/uauth/custUpdatePassword**
    public static final String URL_CUST_VERIFY_UPDATE_PWD = "app/appserver/uauth/custVerifyUpdatePwd";
    //修改绑定手机号**
    public static final String URL_DUPDATE_MOBILE = "app/appserver/uauth/updateMobile";
    //    6.2.1.	(POST)影像上传-个人版
    public static final String URL_ATTACHUPLOADPERSON = "app/appserver/attachUploadPerson";
    //6.1.80.	(POST)提额影像上传
    public static final String URL_attachUploadPersonByGetEd = "app/appserver/attachUploadPersonByGetEd";
    //6.2.6.	(DELETE)影像删除-个人版
    public static final String URL_ATTCHDELETEPERSON = "app/appserver/attachDeletePerson";
    //6.1.44.	(GET) 待还款信息查询(月度、近七日)
    public static final String URL_RETURN_LIST = "app/appserver/apporder/queryApplListByIdNo";
    //首页下部广告
    public static final String URL_HOME_AD = "app/appserver/ad/getHomeView";
    //精选商品
    public static final String URL_HIGH_QUALITY_AD = "app/appserver/appmanage/getGoodsApplPage";
    //6.1.45.	(GET) 待还款信息查询(全部)
    public static final String URL_ALL_RETURN_LIST = "/app/appserver/apporder/queryApplAllByIdNo";
    //6.1.17.	(POST)还款试算
    public static final String URL_GET_PAY_SS = "app/appserver/customer/getPaySs";
    //6.1.97.	+(POST) 人脸识别（新）
    public static final String URL_FACECHECK = "app/appserver/faceCheck2";
    //6.1.81.	(GET)银行卡变更授权书签章(免token)
    public static final String URL_BANK_CARD_GRANT = "app/appserver/bankCardGrant";
    //3.1.20.	(POST)修改银行卡信息（根据客户编号银行卡号）(APP)(CRM78)
    public static final String URL_UPDATE_CARD_INFO = "app/appserver/crm/cust/updateBankCardInfo";
    //3.1.18(POST)新增客户银行卡(APP)
    //指定客户，增加银行卡，如果银行卡号（无论客户编号是否相同）已存在，则不予增加，也不予修改，直接返回失败；不存在的增加。
    public static final String URL_SAVE_BANK_CARD = "app/appserver/crm/cust/saveBankCard";
    //    3.1.22.	(GET)查询默认还款银行卡（根据客户编号）(APP) (CRM60)
    public static final String URL_GET_DEFAULT_CARD = "app/appserver/crm/cust/getDefaultBankCard";
    //商品图片地址
    public static final String URL_GOODS_IMAGE = "pub/gm/getGoodsPic";
    //    6.1.83.	(GET)银行卡变更授权书展示
    public static final String URL_GRANT = "app/appserver/grant";
    //    7.2.	(POST)主动还款
    public static final String URL_ZDHK_RES = "app/appserver/customer/zdhkRes";
    //7.3.	(POST)主动还款（多个借据同时提交）
    public static final String URL_ZDHK_RES_LIST = "app/appserver/customer/zdhkResByList";
    //6.1.21(GET)查询贷款品种详情
    public static final String URL_LOAN_TYP = "app/appserver/cmis/pLoanTyp";
    //    6.2.9.	–(GET)查询全部贷款信息列表-个人版
    public static final String URL_ALL_ORDER = "app/appserver/apporder/getDateAppOrderPerson";
    //6.2.10.	(GET) 查询待提交订单列表—个人版
    public static final String URL_WAIT_COMMIT_ORDER = "app/appserver/apporder/getWtjAppOrderCust";
    //    6.1.45.	(GET) 待还款信息查询(全部)
    public static final String URL_WAIT_PAY_ORDER = "app/appserver/apporder/queryApplAllByIdNo";
    //6.2.8.	–(GET)查询已提交贷款申请列表-个人版
    public static final String URL_APPLLIST_ORDER = "app/appserver/cmis/queryApplListPerson";
    //商品图片地址
    public static final String URL_GOODS_PIC = "pub/gm/getGoodsPic";
    //3.1.27.(GET)根据客户编号查询邀请原因(APP) (CRM55)
    public static final String URL_IS_EXISTS_INVITED = "app/appserver/crm/cust/getInvitedCustByCustNo";
    //6.2.12.(GET) 录单校验（个人版）
    public static final String URL_CHECK_ORDER = "app/appserver/apporder/getCustInfoAndEdInfoPerson";
    //6.2.3.(POST)个人版订单保存
    public static final String UEL_SAVE_ORDER = "app/appserver/apporder/saveAppOrderInfo";
    //8.8.(GET)查询贷款品种省市是否放开
    public static final String URL_CHECK_CITY = "app/appmanage/citybean/checkCity";
    //6.1.13.(POST)保存订单贷款信息
    public static final String URL_UPDATE_ORDER = "app/appserver/apporder/updateAppOrder";
    //6.1.99.活动详情查询接口(免token)
    public static final String URL_GET_ADV_DETAIL = "app/appserver/ad/getActiveInfo";
    //    6.1.7.	(GET)查询订单详情
    public static final String URL_ORDERS_DETAIL = "app/appserver/apporder/getAppOrderAndGoods";
    //6.56.	(POST)设置待提交订单还款卡号  订单号**
    public static final String URL_SET_DEFALT_CARDNO = "app/appserver/appInfo/setHkNo";
    //    6.1.72.	(GET)贷款取消
    public static final String URL_CANCLE_ORDER = "app/appserver/apporder/cancelAppOrder";
    //    6.1.8.	 (DELETE)删除订单
    //根据订单编号，删除订单及该订单下的所有商品
    public static final String URL_DELETE_ORDER = "app/appserver/apporder/deleteAppOrder";
    //6.1.54.	-(GET) 查询贷款详情
    public static final String URL_LOAN_DETAIL = "app/appserver/apporder/queryAppLoanAndGoods";
    //6.1.47.	(GET) 按贷款申请查询分期账单
    public static final String URL_LOAN_BILL = "app/appserver/queryApplListBySeq";
    //7.1.	(POST) 欠款查询(参照核算接口5.1)
    public static final String URL_GETQFCHECK = "app/appserver/customer/getQFCheck";
    //8.16退货原因 GET
    public static final String URL_SELECT_REASON = "app/appserver/appmanage/returnreason/selectReason";
    //3.5.2退货申请 POST
    public static final String URL_RETURN_GOODS = "app/appserver/apporder/returnGoods";
    //6.1.79.(POST)额度申请进度查询（提额最新的进度）
    public static final String URL_GET_ED_APPL_PROGRESS = "app/appserver/apporder/getEdApplProgress";
    //6.1.86.	(GET) 查询是否可以提交额度申请（提额申请）
    public static final String URL_IF_ED_APPL = "app/appserver/cmis/ifEdAppl";
    //    6.1.70.	(GET) 申请放款
    public static final String URL_SUB_SIGN_CONT = "app/appserver/subSignCont";
    //    6.1.62.	(GET) 退回订单修改
    //    说明：发送下列报文，返回订单编号
    public static final String URL_GET_RETURN_ORDER = "/app/appserver/customer/getReturnOrder";
    //6.83.	(GET) 获取取货码(个人版)
    public static final String URL_GET_TRADE_CODE = "app/appserver/apporder/getTradeCode";
    //查看合同
    public static final String URL_CONTRACT_PDF = "app/appserver/downContractPdf";
    //6.1.46.(POST) 外部风险信息采集
    public static final String URL_UPDATE_RISK_INFO = "app/appserver/updateRiskInfo";
    //6.3.5.(POST) 外部风险信息采集
    public static final String URL_UPDATE_LIST_RISK_INFO = "app/appserver/updateListRiskInfo";
    //    1.	专项贷款页面的读取接口
//    请求方式：GET
    public static final String URL_GET_EXCLUSIVE_APPLPAGE = "app/appmanage/getExclusiveApplPage";
    //3.5.1物流信息 GET
    public static final String URL_LOGISTIC_INFO = "app/appserver/order/";
    public static final String URL_LOGISTIC_INFO2 = "/sg/logisticsInfo";
    //2.	专项贷款对应的商户界面的读取接口
    //    请求方式：GET
    public static final String URL_GET_APP_STORE = "app/appserver/appmanage/getAppStoreInfoPage";
    //3.1.35.	(GET) 查询客户当前集团总积分(CRM90)
    public static final String URL_INTEGRAL = "app/appserver/crm/cust/queryPointByCustNo";
    //98、(POST)查询客户指定订单可用优惠券列表
    public static final String URL_COUPONS_FOR_ORDER = "app/crm/cust/getCustCouponsForOrder";
    //99、(POST) 使用客户优惠券
    public static final String URL_USE_CUST_COUPON = "app/crm/cust/useCustCoupon";
    //102、(GET) 取消使用优惠券
    public static final String URL_CANCLE_USE_COUPON = "app/crm/cust/cancelUseCustCoupon";
    //101、(GET) 根据券号查询优惠券
//    说明：根据券号查询券号配置信息，及券号领兑或使用的客户信息，和使用时的条件信息。
    public static final String URL_GET_COUPON_INFO = "app/crm/cust/getCouponInfo";
    //103、(POST) 判定优惠券是否符合配置
    public static final String URL_QUALIFY_USE_COUPON = "app/crm/cust/qualifyUseCustCoupon";
    //97、(GET)查询客户所有优惠券
//    说明：将返回指定客户所有优惠券，包括已领兑、已使用、使用超期、失效等所有状态的优惠券
    public static final String URL_GET_CUST_COUPONS = "app/crm/cust/getCustCoupons";
    //注册协议
    public static final String urlRegisterUri = "app/appserver/register";
    //提额征信授权书展示
    public static final String url_edCredit = "app/appserver/edCredit";
    //借款合同
    public static final String URL_CONTRACT = "app/appserver/contract";
    //征信协议
    public static final String URL_CREDIT = "app/appserver/credit";
    //6.1.39.(GET) 合同签约提交
    public static final String URL_SUB_SIGN_CONTRACT = "app/appserver/subSignContract";
    //6.1.14.(GET)订单提交
    public static final String URL_COMMIT_APP_ORDER = "app/appserver/apporder/commitAppOrder";
    //6.69.	(GET) 消息列表查询
    public static final String URL_GET_MESSAGE_LIST = "app/appserver/customer/getMessageList";
    //6.86.	(GET)确认消息已读
    public static final String URL_UPDATE_MSG_STATUS = "app/appserver/message/updateMsgStatus";
    //6.1.133.	(GET)获取个人中心信息
    public static final String URL_PERSONAL_CENTER_INFO = "app/appserver/getPersonalCenterInfo";
    //6.1.16.(GET)额度查询(免token)
    public static final String URL_GET_ED_CHECK = "app/appserver/getEdCheck";
    //6.1.134.	(GET)根据图片地址下载图片接口(免token)
    public static final String URL_GET_ADIMAGE_BY_ADDR = "app/appserver/ad/getAdImagByAddr";
    //6.1.29.(POST)订单协议确认
    public static final String URL_ORDER_AGREEMENT = "app/appserver/apporder/updateOrderAgreement";
    //6.1.30.(POST)订单合同确认
    public static final String URL_UPDATE_CONTRACT = "app/appserver/apporder/updateOrderContract";
    //6.1.67.(GET)额度申请
    public static final String URL_GET_ED_APPL_INFO = "app/appserver/customer/getEdApplInfo";
    //6.1.128.	(GET) 扫码获取信息接口
    public static final String URL_getMsgByScan = "app/appserver/getMsgByScan";
    //6.1.134.(GET)根据图片地址下载图片接口(免token)
    public static final String URL_GET_AD_IMAGE = "app/appserver/ad/getAdImagByAddr";
    //6.1.130.	(GET) 查询贷款品种信息列表
    public static final String URL_pLoanTypList = "app/appserver/cmis/pLoanTypList";
    //查询已认证客户的贷款品种及小类(APP)
    public static final String URL_CUST_LOAN_LEL = "app/appserver/crm/cust/getCustLoanCodeAndLel";

    //    6.98.	(GET) 查询可选影像列表
    public static final String URL_TYP_IMAGES_LIST = "app/appserver/cmis/typImagesList";
    //1.11(GET)查询商品品牌
    public static final String URL_getBrandDic = "app/appserver/pub/gm/getBrandDic";
    //1.12(GET)查询商品类型
    public static final String URL_getKindDic = "app/appserver/pub/gm/getKindDic";
//    1.13(GET)查询贷款品种
    public static final String URL_getLoanDic = "app/appserver/pub/gm/getLoanDic";
    //    6.1.135.	(POST) 修改或保存收货地址 (CRM111)
    public static final String URL_SAVE_ADDRESS = "app/appserver/crm/apporder/consignessAddressAddOrModify";

}

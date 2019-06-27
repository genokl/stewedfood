package cn.zgyt.controller;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.zgyt.basic.bean.ProductType;
import cn.zgyt.basic.bean.vo.SearchVo;
import cn.zgyt.repo.ProductTypeRepository;
import cn.zgyt.util.CommonUtils;
import cn.zgyt.util.ConstantPoot;
import cn.zgyt.util.ResponseUtils;
import cn.zgyt.util.xcx.PayUtil;
import cn.zgyt.util.xcx.XcxConfig;

@Controller
@RequestMapping("/xcx/order")
public class OrderController {
	@Autowired
	private XcxConfig xcxConfig;

	private Logger log = Logger.getLogger(OrderController.class);

    public JSONObject createUnifiedOrder(HttpServletRequest request,HttpServletResponse response) {
    	log.info("微信 统一下单 接口调用");
        //设置最终返回对象
        JSONObject resultJson = new JSONObject();
        //创建条件
        Criteria criteria = new Criteria();
    
        //接受参数(金额)
        String amount = request.getParameter("amount");
        //接受参数(openid)
        String openid = request.getParameter("openid");
        //接口调用总金额单位为分换算一下(测试金额改成1,单位为分则是0.01,根据自己业务场景判断是转换成float类型还是int类型)
        //String amountFen = Integer.valueOf((Integer.parseInt(amount)*100)).toString();
        //String amountFen = Float.valueOf((Float.parseFloat(amount)*100)).toString();
        String amountFen = "1";
        //创建hashmap(用户获得签名)
        SortedMap<String, String> paraMap = new TreeMap<String, String>();
        //设置body变量 (支付成功显示在微信支付 商品详情中)
        String body = "啦啦啦测试";
        //设置随机字符串
        String nonceStr = CommonUtils.getUUIDString().replaceAll("-", "");
        //设置商户订单号
        String outTradeNo = CommonUtils.getUUIDString().replaceAll("-", "");
        
        
        //设置请求参数(小程序ID)
        paraMap.put("appid", xcxConfig.getAPPID());
        //设置请求参数(商户号)
        paraMap.put("mch_id", xcxConfig.getMCHID());
        //设置请求参数(随机字符串)
        paraMap.put("nonce_str", nonceStr);
        //设置请求参数(商品描述)
        paraMap.put("body", body);
        //设置请求参数(商户订单号)
        paraMap.put("out_trade_no", outTradeNo);
        //设置请求参数(总金额)
        paraMap.put("total_fee", amountFen);
        //设置请求参数(终端IP)
        paraMap.put("spbill_create_ip", CommonUtils.getIpAddr(request));
        //设置请求参数(通知地址)
        paraMap.put("notify_url", CommonUtils.getBasePath(request)+"wechat/wechatAppletGolf/payCallback");
        //设置请求参数(交易类型)
        paraMap.put("trade_type", "JSAPI");
        //设置请求参数(openid)(在接口文档中 该参数 是否必填项 但是一定要注意 如果交易类型设置成'JSAPI'则必须传入openid)
        paraMap.put("openid", openid);
        //调用逻辑传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        String stringA = PayUtil.formatUrlMap(paraMap, false, false);
        //第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
        String sign = CommonUtils.MD5Encode(stringA+"&key="+KEY,null).toUpperCase();
        //将参数 编写XML格式
        StringBuffer paramBuffer = new StringBuffer();
        paramBuffer.append("<xml>");
        paramBuffer.append("<appid>"+xcxConfig.getAPPID()+"</appid>");
        paramBuffer.append("<mch_id>"+xcxConfig.MCHID+"</mch_id>");
        paramBuffer.append("<nonce_str>"+paraMap.get("nonce_str")+"</nonce_str>");
        paramBuffer.append("<sign>"+sign+"</sign>");
        paramBuffer.append("<body>"+body+"</body>");
        paramBuffer.append("<out_trade_no>"+paraMap.get("out_trade_no")+"</out_trade_no>");
        paramBuffer.append("<total_fee>"+paraMap.get("total_fee")+"</total_fee>");
        paramBuffer.append("<spbill_create_ip>"+paraMap.get("spbill_create_ip")+"</spbill_create_ip>");
        paramBuffer.append("<notify_url>"+paraMap.get("notify_url")+"</notify_url>");
        paramBuffer.append("<trade_type>"+paraMap.get("trade_type")+"</trade_type>");
        paramBuffer.append("<openid>"+paraMap.get("openid")+"</openid>");
        paramBuffer.append("</xml>");
        
        try {
            //发送请求(POST)(获得数据包ID)(这有个注意的地方 如果不转码成ISO8859-1则会告诉你body不是UTF8编码 就算你改成UTF8编码也一样不好使 所以修改成ISO8859-1)
            Map<String,String> map = doXMLParse(getRemotePortData(URL, new String(paramBuffer.toString().getBytes(), "ISO8859-1")));
            //应该创建 支付表数据
            if(map!=null){
                //清空
                criteria.clear();
                //设置openId条件
                criteria.put("openId", openid);
                //获取数据
                List<WechatAppletGolfPayInfo> payInfoList = appletGolfPayInfoMapper.selectByExample(criteria);
                //如果等于空 则证明是第一次支付
                if(CollectionUtils.isEmpty(payInfoList)){
                    //创建支付信息对象
                    WechatAppletGolfPayInfo appletGolfPayInfo = new  WechatAppletGolfPayInfo();
                    //设置主键
                    appletGolfPayInfo.setPayId(outTradeNo);
                    //设置openid
                    appletGolfPayInfo.setOpenId(openid);
                    //设置金额
                    appletGolfPayInfo.setAmount(Long.valueOf(amount));
                    //设置支付状态
                    appletGolfPayInfo.setPayStatus("0");
                    //插入Dao
                    int sqlRow = appletGolfPayInfoMapper.insert(appletGolfPayInfo);
                    //判断
                    if(sqlRow == 1){
                        logger.info("微信 统一下单 接口调用成功 并且新增支付信息成功");
                        resultJson.put("prepayId", map.get("prepay_id"));
                        resultJson.put("outTradeNo", paraMap.get("out_trade_no"));
                        return resultJson;
                    }
                }else{
                    //判断 是否等于一条
                    if(payInfoList.size() == 1){
                        //获取 需要更新数据
                        WechatAppletGolfPayInfo wechatAppletGolfPayInfo = payInfoList.get(0);
                        //更新 该条的 金额
                        wechatAppletGolfPayInfo.setAmount(Long.valueOf(amount));
                        //更新Dao
                        int sqlRow = appletGolfPayInfoMapper.updateByPrimaryKey(wechatAppletGolfPayInfo);
                        //判断
                        if(sqlRow == 1){
                            logger.info("微信 统一下单 接口调用成功 修改支付信息成功");
                            resultJson.put("prepayId", map.get("prepay_id"));
                            resultJson.put("outTradeNo", paraMap.get("out_trade_no"));
                            return resultJson;
                        }
                    }
                }
            }
            //将 数据包ID 返回
            
            System.out.println(map);
        } catch (UnsupportedEncodingException e) {
            logger.info("微信 统一下单 异常："+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.info("微信 统一下单 异常："+e.getMessage());
            e.printStackTrace();
        }
        logger.info("微信 统一下单 失败");
        return resultJson;
    }
}

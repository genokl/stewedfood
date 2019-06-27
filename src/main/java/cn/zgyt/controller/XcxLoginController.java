package cn.zgyt.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.zgyt.app.UserCacheInfo;
import cn.zgyt.basic.bean.Member;
import cn.zgyt.service.MemberService;
import cn.zgyt.util.CommonUtils;
import cn.zgyt.util.ConstantPoot;
import cn.zgyt.util.ResponseUtils;
import cn.zgyt.util.xcx.AesCbcUtil;
import cn.zgyt.util.xcx.HttpRequest;
import cn.zgyt.util.xcx.XcxConfig;
import cn.zgyt.util.xcx.XcxConstant;
import cn.zgyt.util.xcx.XcxUtil;
@Controller
@RequestMapping("/xcxlogin")
public class XcxLoginController{

	@Resource(name="memberService")
	private MemberService memberService;
	
	@Autowired
	private XcxConfig xcxConfig;
	
	private Logger log = Logger.getLogger(XcxLoginController.class);
	/**
	 * 登录controller
	 */
	@RequestMapping("/login")
	public void login(
			HttpServletRequest request,
			HttpServletResponse response,
			UserCacheInfo u,
			String code,
			String requestKey,
			String encryptedData,
			String iv) throws Exception{
		Member loginMember = u.getM();
		String res = "";
		log.info("收到【"+CommonUtils.getIpAddr(request)+"】登录请求！！！ ");
		JSONObject rjo =new JSONObject();
		int statusCode = 0;
		try {
			boolean isSessionLogin= false,
					isLoginTrue=false;
//			System.out.println(XcxUtil.testRequestKey(request, requestKey,u.getLastRequestTime()));
			if((!CommonUtils.checkFull(requestKey))&&(loginMember!=null&&loginMember.getId()>0)){
				if(XcxUtil.testRequestKey(request, requestKey,u.getLastRequestTime())){
					loginMember=memberService.getInstanceByID(loginMember.getId());
					isSessionLogin=true;
				}
			}
			
			if((!CommonUtils.checkFull(code))&&isSessionLogin==false){
//				System.out.println(XcxConfig.getAPPID());
//				System.out.println("code:"+code);
				String params = "appid=" + xcxConfig.getAPPID() + "&secret=" +xcxConfig.getAPPSECRET()+"&js_code=" + code + "&grant_type=" + XcxConstant.GRANR_TYPE;
				System.out.println(params);
			    String userSessionKey = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
//			    https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
				JSONObject json = JSONObject.parseObject(userSessionKey);
				String userInfo = AesCbcUtil.decrypt(encryptedData, json.get("session_key").toString(), iv, "UTF-8");
				loginMember=memberService.mergeMembersByWebJSONAndXcxJSON(userInfo, XcxConfig.getAPPSECRET());
				isLoginTrue=true;
				statusCode=1;
			}else{
				log.info("来自【"+CommonUtils.getIpAddr(request)+"】登录失败 ！！ 参数缺失！  !code:"+code
						+" ;encryptedData :  "+encryptedData+"  ; iv: "+iv);
				statusCode=-1;
			}
			
			if(isLoginTrue){
				long lastTime= System.currentTimeMillis();
				//session 设置登录对象
//				String waiterList = memberService.loadWaiterList();
				request.getSession().setAttribute(XcxConstant.XCX_LOGIN_MEMBER, loginMember);
				request.getSession().setAttribute(XcxConstant.XCX_LOGIN_MEMBER_ID, loginMember.getId());
				//服务器端相应参数
//				rjo.put(XcxConstant.XCX_REQUEST_KEY, XcxUtil.createResponseKey(request,lastTime));
//				rjo.put(XcxConstant.XCX_LAST_REQUEST_TIME,System.currentTimeMillis());
				rjo.put(XcxConstant.XCX_LOGIN_MEMBER_ID, loginMember.getId());
				rjo.put(XcxConstant.XCX_LOGIN_MEMBER, loginMember);
//				rjo.put(XcxConstant.XCX_LOGIN_MEMBER_TYPE, loginMember.getMemberType());
				rjo.put(XcxConstant.XCX_LOGIN_MEMBER_SESSION_ID, request.getSession().getId());
//				rjo.put("loginMember", loginMember);
				log.info("用户【"+loginMember.getId()+"】登录成功，来自【"+CommonUtils.getIpAddr(request)+"】 ");
			}
			rjo.put(ConstantPoot.STATUS_STR, statusCode);
			ResponseUtils.xcxRenderJSON(response,request, rjo);
		} catch (Exception e) {
			log.info("来自【"+CommonUtils.getIpAddr(request)+"】登录失败！！发生异常  !code:"+code
					+" ;encryptedData :  "+encryptedData+"  ; iv: "+iv +" ; ex: " +e.getMessage());
			statusCode=-1;
			e.printStackTrace();
		}
	}
}

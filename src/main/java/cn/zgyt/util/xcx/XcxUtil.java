package cn.zgyt.util.xcx;

import javax.servlet.http.HttpServletRequest;

import cn.zgyt.util.CommonUtils;


/**
 * 小程序特有工具
 * @author pzg
 * @version 2.0
 * @createTime 2017年6月5日上午11:09:06
 */
public class XcxUtil {
	/**
	 * 生成本次相应key
	 * @param request
	 * @return
	 */
	public static String createResponseKey(HttpServletRequest request,long lastRequestTime){
		String sessionId= request.getSession().getId();
		int memberId=0;
		if( request.getSession().getAttribute(XcxConstant.XCX_LOGIN_MEMBER_ID)!=null){
			memberId= (Integer) request.getSession().getAttribute(XcxConstant.XCX_LOGIN_MEMBER_ID);
		}
		return CommonUtils.strToMD5(sessionId+lastRequestTime+memberId,32);
	}
	/**
	 * 验证key有效性
	 * @param request
	 * @param requestKey
	 * @return
	 */
	public static boolean testRequestKey(HttpServletRequest request,String requestKey,long lastRequestTime){
		String rk = createResponseKey(request,lastRequestTime);
		return rk.equals(requestKey);
	}
}

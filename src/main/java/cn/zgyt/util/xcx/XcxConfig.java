package cn.zgyt.util.xcx;

import org.springframework.stereotype.Component;

@Component
public class XcxConfig {

	
	/**
	 * AppID(应用ID)
	 */
	public static String APPID ;
	/**
	 * AppSecret(应用密钥)
	 */
	public static String APPSECRET;
	
	/**
	 * 商户ID
	 */
	public static String MCHID;
	
	
	public static String getAPPID() {
		return APPID;
	}
	public static void setAPPID(String aPPID) {
		APPID = aPPID;
	}
	public static String getAPPSECRET() {
		return APPSECRET;
	}
	public static void setAPPSECRET(String aPPSECRET) {
		APPSECRET = aPPSECRET;
	}
	public static String getMCHID() {
		return MCHID;
	}
	public static void setMCHID(String mCHID) {
		MCHID = mCHID;
	}
	
	
	
	

}

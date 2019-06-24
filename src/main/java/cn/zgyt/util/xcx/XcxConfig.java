package cn.zgyt.util.xcx;

//@Component
//@ConfigurationProperties
public class XcxConfig {

	
	/**
	 * AppID(应用ID)
	 */
	public static String APPID = "wx4196510759f1f16d";
	/**
	 * AppSecret(应用密钥)
	 */
	public static String APPSECRET = "5cfac39d850f24062a9ddd16358cf6a9";
	
	
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
	
	
	

}

package cn.zgyt.util;

/**
 * 加密工具类
 * @author geno
 *
 */
public class EncryptUtil {

	/**
	 * 移除登录凭证
	 * value username
	 * @param jsessionid
	 * @return
	 */
	public static String getKey(String key) {
		return CommonUtils.strToMD5(key, 16);
	}
}

package cn.zgyt.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 工具类
 * 
 * @author pzg
 * @createDate 2016年11月9日上午10:10:15
 * @version 1.0
 */
public class CommonUtils {
	public static void main(String[] args) {
		System.out.println(random(10));
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public static Integer random(Integer count) {
		return (int) ((Math.random() * 9 + 1) * (Math.pow(10, count)));
	}

	/**
	 * md5 编码
	 * 
	 * @param origin
	 * @param charsetname
	 * @return
	 */
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 生成16随机字符串
	 * 
	 * @return
	 */
	public static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 16; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}

	/**
	 * 异常信息转换为异常字符串
	 * 
	 * @param ex
	 * @return
	 */
	public static String exception2Str(Exception ex) {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ex.printStackTrace(new java.io.PrintWriter(buf, true));
		String expMessage = buf.toString();
		try {
			buf.close();
		} catch (IOException e) {
			expMessage = "exception2Str Excetopn";
		}
		return expMessage;
	}

	/** 判断空字对象，空map,空collection **/
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof String) {
			return ((String) o).trim().length() == 0;
		} else if (o instanceof Collection) {
			return ((Collection<?>) o).size() == 0;
		} else if (o instanceof Map) {
			return ((Map<?, ?>) o).size() == 0;
		} else if (o.getClass().isArray()) {
			return Array.getLength(o) == 0;
		}
		return false;
	}

	/**
	 * 将日期转换为字符串
	 * 
	 * @param date     需要转换的日期
	 * @param template 主要转换的模板 eg:yyyy-MM-dd HH:mm:ss
	 * @return 转换后的字符串
	 */
	public static String getDate2Str(Date date, String template) {
		SimpleDateFormat ss = null;
		if (template == null || "".equals(template)) {
			ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			ss = new SimpleDateFormat(template);
		}
		return ss.format(date);
	}

	/**
	 * 将日期转换为字符串
	 * 
	 * @param date     需要转换的日期
	 * @param template 主要转换的模板 eg:yyyy-MM-dd HH:mm:ss
	 * @return 转换后的字符串
	 */
	public static Long getDate2Num(Date date, String template) {
		SimpleDateFormat ss = null;
		if (template == null || "".equals(template)) {
			ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			ss = new SimpleDateFormat(template);
		}
		return Long.parseLong(ss.format(date).replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
	}

	/***
	 * 检查字符串是否为空, 为空则返回true 反之返回false
	 * 
	 * @param param
	 * @return
	 */
	public static boolean checkFull(String param) {
		return ((null == param || "".equals(param.trim()) || "null".equals(param.trim())) ? true : false);
	}

	/**
	 * 根据请求信息获取 IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = "";
		if (request != null) {
			ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		}
		String[] ips = ip.split(",");
		if (ips.length == 2) {
			return ips[0];
		} else {
			return ip;
		}
	}

	/**
	 * 验证IPv4
	 * 
	 * @param ipAddress
	 * @return
	 */
	public static boolean isIpv4(String ipAddress) {
		if (CommonUtils.checkFull(ipAddress)) {
			return false;
		}
		String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

	/**
	 * 字节数字 转换 string
	 * 
	 * @param byteArray 需要转换的字节数字
	 * @return 转换后的str
	 */
	public static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	public static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

	/**
	 * 验证手机号格式
	 * 
	 * @param phoneNumStr
	 * @return 2016-11-12下午8:19:59
	 */
	public static boolean isPhoneNum(String phoneNumStr) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(phoneNumStr);
		return m.matches();
	}

	/**
	 * 验证邮箱格式
	 * 
	 * @param email
	 * @return 2016-11-15下午2:27:50
	 */
	public static boolean isEmail(String email) {
		Pattern p = Pattern.compile("^([a-zA-Z0-9_-]{1,})+@([a-zA-Z0-9_-]{1,})+(.[a-zA-Z0-9_-]{1,})+");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 验证多选格式 【-数字:选项值;】 出现1次或者多次
	 * 
	 * @param str
	 * @return 2016-11-16下午2:27:00
	 */
	public static boolean isduoxuan(String str) {
		String pattern = "^((-|\\+)?\\d+:.{1,};)+$";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		return m.matches();
	}

	/**
	 * 验证毫秒值 13位数字
	 * 
	 * @param str
	 * @return 2016-11-22下午5:58:47
	 */
	public static boolean isMillitimes(String str) {
		Pattern p = Pattern.compile("[0-9]{13}");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 计算两个日期之间差几天
	 * 
	 * @return
	 */
	public static int daysBetween(Date begindate, Date enddate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		begindate = sdf.parse(sdf.format(begindate));
		enddate = sdf.parse(sdf.format(enddate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(begindate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(enddate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/***
	 * 将字符串转换成MD5码 type 加密位数 16 32
	 * 
	 * @return
	 */
	public static String strToMD5(String code, int type) {
		String md5Code = null;
		if (!checkFull(code)) {
			md5Code = bytesToMD5(code.getBytes(), type);
		}
		return md5Code;
	}

	/**
	 * 把字节数组转换成md5
	 * 
	 * @param input 将要转换的字节数组
	 * @param type  进制数 16（默认） 32 64
	 * @return
	 */
	public static String bytesToMD5(byte[] input, int type) {
		String md5str = null;
		try {
			// 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算后获得字节数组
			byte[] buff = md.digest(input);
			md5str = bytesToHex(buff, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	/**
	 * 把字节数组转成16进位制数
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes, int type) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];
			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));

		}
		if (type == 16) {
			return md5str.toString().substring(8, 24);
		} else {// 默认32
			return md5str.toString();
		}
	}

//		/**
//		 * 02
//		 * @Description 发起https请求并获取结果 03
//		 * @author temdy 04
//		 * @Date 2015-06-19 05
//		 * @param requestUrl
//		 *            请求地址 06
//		 * @param requestMethod
//		 *            请求方式（GET、POST） 07
//		 * @param outputStr
//		 *            提交的数据 08
//		 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 09
//		 */
//		public static JSONObject httpRequest(String requestUrl,
//				String requestMethod, String outputStr) {
//			JSONObject jsonObject = null;
//			StringBuffer buffer = new StringBuffer();
//			try {
//				// 创建SSLContext对象，并使用我们指定的信任管理器初始化
//
//				TrustManager[] tm = { new MyX509TrustManager() };
//
//				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//
//				sslContext.init(null, tm, new java.security.SecureRandom());
//
//				// 从上述SSLContext对象中得到SSLSocketFactory对象
//
//				SSLSocketFactory ssf = sslContext.getSocketFactory();
//
//				URL url = new URL(requestUrl);
//
//				HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
//						.openConnection();
//
//				httpUrlConn.setSSLSocketFactory(ssf);
//
//				httpUrlConn.setDoOutput(true);
//
//				httpUrlConn.setDoInput(true);
//
//				httpUrlConn.setUseCaches(false);
//
//				// 设置请求方式（GET/POST）
//
//				httpUrlConn.setRequestMethod(requestMethod);
//
//				if ("GET".equalsIgnoreCase(requestMethod))
//
//					httpUrlConn.connect();
//
//				// 当有数据需要提交时
//
//				if (null != outputStr) {
//
//					OutputStream outputStream = httpUrlConn.getOutputStream();
//
//					// 注意编码格式，防止中文乱码
//
//					outputStream.write(outputStr.getBytes("UTF-8"));
//
//					outputStream.close();
//
//				}
//
//				// 将返回的输入流转换成字符串
//
//				InputStream inputStream = httpUrlConn.getInputStream();
//
//				InputStreamReader inputStreamReader = new InputStreamReader(
//						inputStream, "utf-8");
//
//				BufferedReader bufferedReader = new BufferedReader(
//						inputStreamReader);
//
//				String str = null;
//
//				while ((str = bufferedReader.readLine()) != null) {
//
//					buffer.append(str);
//
//				}
//
//				bufferedReader.close();
//
//				inputStreamReader.close();
//
//				// 释放资源
//
//				inputStream.close();
//
//				inputStream = null;
//
//				httpUrlConn.disconnect();
//
//				jsonObject = JSONObject.parseObject(buffer.toString());
//
//			} catch (ConnectException ce) {
//
//				ce.printStackTrace();
//
//			} catch (Exception e) {
//
//				e.printStackTrace();
//
//				// log.error("https request error:{}", e);
//
//			}
//
//			return jsonObject;
//
//		}

	/**
	 * 判断当前时间是否在某个时间段之内
	 * 
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
//	        Calendar date = Calendar.getInstance();
//	        date.setTime(nowTime);
//
//	        Calendar begin = Calendar.getInstance();
//	        begin.setTime(beginTime);
//
//	        Calendar end = Calendar.getInstance();
//	        end.setTime(endTime);

		if (nowTime.after(beginTime) && nowTime.before(endTime)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断当前时间是否是白天
	 * 
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean isDayTime(Date nowTime) {
		Date beginTime = new Date();
		if (nowTime.after(getHourDate(7)) && nowTime.before(getHourDate(24))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取正点时间对象
	 * 
	 * @param date
	 * @return ... HH:00:00.000
	 */
	public static Date getHourDate(Integer hour) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

}
//package cn.zgyt.util.xcx;
//
//import java.security.MessageDigest;
//
//import cn.zgyt.util.CommonUtils;
///**
// * MD5 解密类
// * @version 3.0
// */
//public class MD5Util {
//
//	 public static String MD5(String originString){
//	        if (originString!=null) {
//	            try {
//	                //创建具有指定算法名称的信息摘要
//	                MessageDigest md5 = MessageDigest.getInstance("MD5");
//	                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
//	                byte[] results = md5.digest(originString.getBytes());
//	                //将得到的字节数组变成字符串返回
//	                String result = CommonUtils.byteArrayToHexString(results);
//	                return result;
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        return null;
//	    }
//
//}

package cn.zgyt.util.xcx;
//package cn.zgyt.util;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//import ms.basic.util.CacheUtil;
//
///**
// * 小程序服务
// * @author pzg
// * @version 2.0
// * @createTime 2017年6月1日下午2:18:37
// */
//public class XcxServer {
//	/**
//	 * 初始化 小程序
//	 * @param serverType 当前服务类型 1 测试环境 0生产环境
//	 */
//	public static void initXcxServer(String serverType){
//		try {
//			
//			CacheUtil.LoadWaiterOnlineList();
//			InputStream in = new BufferedInputStream (new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("/").getPath()+"xcx.properties"));
//			Properties pro = new Properties();
//			pro.load(in);
//			in.close();
//			
//			XcxConstant.GRANR_TYPE=pro.getProperty("xcx.grant_type");
//			XcxConstant.SESSION_KEY_URL=pro.getProperty("xcx.session.key.url");
//			if("1".equals(serverType)){
//				XcxConstant.APPID=pro.getProperty("xcx.test.wxspAppid");
//				XcxConstant.APPSECRET=pro.getProperty("xcx.test.wxspSecret");
//			}else{
//				XcxConstant.APPID=pro.getProperty("xcx.produce.wxspAppid");
//				XcxConstant.APPSECRET=pro.getProperty("xcx.produce.wxspSecret");
//			}
//		}  catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}

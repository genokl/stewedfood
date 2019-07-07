package cn.zgyt.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.zgyt.util.xcx.XcxConstant;
import cn.zgyt.util.xcx.XcxUtil;

/**
 * HttpServletResponse帮助类
 */
public final class ResponseUtils {
	
	public static void xcxRenderJSON(HttpServletResponse response,HttpServletRequest request,JSONObject jo) {
		long lastRequstTime = System.currentTimeMillis();
		jo.put(XcxConstant.XCX_LAST_REQUEST_TIME,lastRequstTime);
		jo.put(XcxConstant.XCX_REQUEST_KEY, XcxUtil.createResponseKey(request,lastRequstTime));
		render1(response, "text/plain;charset=UTF-8", JSON.toJSONString(jo, SerializerFeature.DisableCircularReferenceDetect));
	}
	
	/**
	 * 发送文本。使用UTF-8编码。
	 * @param response HttpServletResponse
	 * @param text  发送的字符串
	 */
	public static void renderText(HttpServletResponse response, JSONObject text) {
		render(response, "text/plain;charset=UTF-8", text);
	}

	/**
	 * 发送json。使用UTF-8编码。
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderJson(HttpServletResponse response, JSONObject text) {
		render(response, "application/json;charset=UTF-8", text);
	}

	/**
	 * 发送xml。使用UTF-8编码。
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderXml(HttpServletResponse response, JSONObject text) {
		render(response, "text/xml;charset=UTF-8", text);
	}

	/**
	 * 发送内容。使用UTF-8编码。
	 * @param response
	 * @param contentType
	 * @param text
	 */
	public static void render(HttpServletResponse response, String contentType,
			JSONObject text) {
//		System.out.println(text);
//		text.put("uid", LoginUtil.createUUid());
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text.toJSONString());
			response.getWriter().close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public static void render1(HttpServletResponse response, String contentType,
			String text) {
//		System.out.println(text);
//		text.put("uid", LoginUtil.createUUid());
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
			response.getWriter().close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}


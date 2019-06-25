package cn.zgyt.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 * 
 * @author pzg
 * @createDate 2016年11月9日上午10:10:15
 * @version 1.0
 */
public class EntityUtils {

	/**
	 * md5 编码
	 * 
	 * @param origin
	 * @param charsetname
	 * @return
	 */
//	public static List<BaseBean> genSelfUrl(List<BaseBean> ll, String selfurl, Class<?> clazz) {
//		for (int i = 0; i < ll.size(); i++) {
//			BaseBean b = ll.get(i);
////			List list = getFiledsInfo(b);
//			Map<String,String> m=new HashMap<>();
//			m.put("self", selfurl + getFieldValueByName("id", b));
//			b.setLinks(m);
//			ll.set(i, b);
//		}
//		return ll;
//	}

	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static List getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		List list = new ArrayList();
		Map infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			list.add(infoMap);
		}
		return list;
	}

}
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
public class ConstantPoot {
 	
	public static String STATUS_STR="statusCode";
	public static String STATUS_INFO_STR="info";
	
	public static Integer STATUS_INIT = 0;
	public static Integer STATUS_SUCCESS=1;
	
}
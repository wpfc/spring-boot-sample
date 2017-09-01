package cn.edu.ntu.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * json字符串转对象
	 * @param source   json字符串
	 * @param clazz    class类
	 * @return  T      object对象
	 */
	public static <T> T parseString2Object(String source, Class<T> clazz) {
		try {
			T t = MAPPER.readValue(source, clazz);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

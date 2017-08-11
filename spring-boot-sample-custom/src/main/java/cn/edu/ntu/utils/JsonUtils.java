package cn.edu.ntu.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 */
public final class JsonUtils {

	/**
	 * ���캯��˽�л�
	 */
	private JsonUtils() {
	}

	// ����jackson����
	private static final ObjectMapper MAPPER = new ObjectMapper();
	

	/**
	 * ������ת����json�ַ�����
	 * 
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��json�����ת��Ϊ����
	 * 
	 * @param jsonData
	 *            json����
	 * @param clazz
	 *            �����е�object����
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��json����ת����pojo����list
	 * 
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	

	public static String toJson(Object data) {
		return objectToJson(data);
	}
	public static <T> T toObject(String json, Class<T> clazz) {
		return jsonToPojo(json, clazz);
	}
	public static <T> List<T> toList(String json, Class<T> clazz) {
		return jsonToList(json, clazz);
	}


}

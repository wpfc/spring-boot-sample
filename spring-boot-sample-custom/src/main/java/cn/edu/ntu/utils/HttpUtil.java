package cn.edu.ntu.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.ntu.entity.User;

public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	private static final int STATUS_SUCCESS = 200;
	
	public static void sendGetRequest(String urlStr){
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			//如果打算使用 URL 连接进行输出
			connection.setDoOutput(false);
			connection.setDoInput(true);
			connection.setReadTimeout(5000);
			
			if(STATUS_SUCCESS != connection.getResponseCode()){
				logger.info("连接异常！");
			}
			// 获取网络的输入流
			InputStream is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            String tempLine = null;
            StringBuffer resultBuffer = new StringBuffer();
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            User user = JsonUtils.jsonToPojo(resultBuffer.toString(), User.class);
			logger.info(resultBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		sendGetRequest("http://localhost:8081/reformSomeone");
	}
	
}

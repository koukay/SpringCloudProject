package com.houkai.admin;

import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DingDingMessageUtil {
	// 从钉钉群获取的https://oapi.dingtalk.com/robot/send?access_token=e15a3ac9d2b73a42120c471e367098743b5c35248b3e2e8745f89a95d99f6345
	public static String access_token = "e15a3ac9d2b73a42120c471e367098743b5c35248b3e2e8745f89a95d99f6345";
//	https://oapi.dingtalk.com/robot/send?access_token=e15a3ac9d2b73a42120c471e367098743b5c35248b3e2e8745f89a95d99f6345
	public static void sendTextMessage(String msg) {
		
		
		//https://oapi.dingtalk.com/robot/send?access_token=55420aefab13c4cf75b3ad144a0efa71ba406ba0037e2619fb99ad2ca5e2c452
		try {
			Message message = new Message();
			message.setMsgtype("text");
			message.setText(new MessageInfo(msg));
			URL url = new URL("https://oapi.dingtalk.com/robot/send?access_token=" + access_token);
			// 建立 http 连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
			conn.connect();
			OutputStream out = conn.getOutputStream();
			String textMessage = JSONObject.toJSONString(message);
			byte[] data = textMessage.getBytes();
			out.write(data);
			out.flush();
			out.close();
			InputStream in = conn.getInputStream();
			byte[] data1 = new byte[in.available()];
			in.read(data1);
			System.out.println(new String(data1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

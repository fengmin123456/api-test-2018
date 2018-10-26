package com.edu.demo1026;

import static org.testng.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import javax.security.auth.login.LoginContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;

public class PostJsonDemo2 {
	public String login(JSONObject user) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url="http://study-perf.qa.netease.com/common/fgadmin/login";
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type","application/json");
		HttpEntity data=new StringEntity(user.toString());
		post.setEntity(data);
		CloseableHttpResponse respone = httpClient.execute(post);
		
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println(content);
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();
		JSONObject json =JSONObject.fromObject(content);
		String message=json.getString("message");
		return message;
	
	}
	
	@Test(priority=0)
	public void LoginBySuccess() throws Exception {

		JSONObject success_user = new JSONObject();
		success_user.element("phoneArea", "86");
		success_user.element("phoneNumber", "20000000000");
		success_user.element("password", "netease123");
		
		String message=this.login(success_user);
		assertEquals(message, "success");
		
	}
	
	@Test(priority=1)
	public void LoginByFail() throws Exception {
		JSONObject user = new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber", "20000000000");
		user.element("password", "netease1234");
		String message=this.login(user);
		assertEquals(message, "用户名或者密码错误");
		}
}

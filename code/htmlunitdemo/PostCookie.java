package com.edu.htmlunitdemo;

import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;

import com.gargoylesoftware.htmlunit.util.Cookie;

import net.sf.json.JSONObject;

public class PostCookie {
	String baseUrl = "http://study-perf.qa.netease.com";
	String url = "/common/fgadmin/login";
	Set<Cookie> cookie =null;
	@Test(priority=0)
	public void login() throws Exception {
		// 1.创建WebClient对象
		WebClient client = new WebClient();
		// 2.创建 WebRequest请求
		WebRequest request = new WebRequest(new URL(baseUrl + url), HttpMethod.POST); // 3.设置header的Content-Type
		client.addRequestHeader("Content-Type", "	\r\n" + "application/json");
		// 4.设置请求体
		request.setRequestBody("{\"phoneArea\":\"86\",\"phoneNumber\":\"20000000000\",\"password\":\"netease123\"}");
		// 5.执行请求
		Page page = client.getPage(request);
		// 6.获得响应
		WebResponse response = page.getWebResponse();
		// 7.获得响应正文
		String result = response.getContentAsString();
		System.out.println("login:"+result);
		JSONObject json =JSONObject.fromObject(result);
		System.out.println(json.getString("message"));
		cookie=client.getCookieManager().getCookies();
	
//		System.out.println(cookie);
		// 8.关闭Client对象
		client.close();

	}

	@Test(priority=1)
	public void getAddressList() throws Exception {
		String listUrl="http://study-perf.qa.netease.com/fgadmin/address/list";
		WebClient client = new WebClient();
		
		Iterator<Cookie> iterator=cookie.iterator();
		while(iterator.hasNext()) {
			client.getCookieManager().addCookie(iterator.next());
			
		}
		
		WebRequest request = new WebRequest(new URL(listUrl), HttpMethod.GET);
		Page page = client.getPage(request);
	
		WebResponse response = page.getWebResponse();
		String result = response.getContentAsString();
		System.out.println("getAddressList:"+result);
		client.close();
	}


}

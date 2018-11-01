package com.edu.htmlunitdemo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

import net.sf.json.JSONObject;

public class PostDemo {
	String baseUrl = "http://study-perf.qa.netease.com";
	String url = "/common/fgadmin/login";
	WebClient client =null;
	@BeforeClass
	public void startUpWebClient() {
		
		client = new WebClient();
	}
	
	@Test
	public void login() throws Exception {
		// 1.创建WebClient对象
//		WebClient client = new WebClient();
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
	
		// 8.关闭Client对象
		client.close();

	}

	@Test(dependsOnMethods="login")
	public void getAddressList() throws Exception {
		String listUrl="http://study-perf.qa.netease.com/fgadmin/address/list";
//		WebClient client = new WebClient();
		WebRequest request = new WebRequest(new URL(listUrl), HttpMethod.GET);
		Page page = client.getPage(request);
		WebResponse response = page.getWebResponse();
		String result = response.getContentAsString();
		System.out.println("getAddressList:"+result);
		client.close();
	}
	
	@Test
	public  void doPostByForm() throws FailingHttpStatusCodeException, IOException {

		Map<String, String> user =new HashMap<>();
		user.put("username", "lihuanzhen");
		user.put("password", "123456");
		
		List list=new ArrayList();
		for(Entry<String, String> entry :  user.entrySet()){
		          
		NameValuePair param=new NameValuePair(entry.getKey(),entry.getValue());
		list.add(param);
		
		        }
	

		String baseUrl="http://study-miniblog-new.qa.netease.com/ajax/user/login";
		WebClient client = new WebClient();
		WebRequest request = new WebRequest(new URL(baseUrl), HttpMethod.POST); // 3.设置header的Content-Type
		client.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setRequestParameters(list);
		Page page = client.getPage(request);
		WebResponse response = page.getWebResponse();
		String result = response.getContentAsString();
		System.out.println("login:" + result);
		client.close();
	}

}

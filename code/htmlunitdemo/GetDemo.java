package com.edu.htmlunitdemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;

public class GetDemo {

	String baseUrl="http://127.0.0.1:8080/Supermarket/analysis/lookupprice?goodsCode={\"pId\":\"123456\"}";
	String url="/common/skuList?goodsId=1";
	@Test
	public void get1() throws Exception {
//1.创建WebClient对象
		WebClient client = new WebClient();
//2.创建	WebRequest请求
		WebRequest request =new WebRequest(new URL(baseUrl),HttpMethod.GET);
//3.执行请求
		Page page=client.getPage(request);
//4.获得响应
		WebResponse response=page.getWebResponse();
//5.获得响应正文
		String result=response.getContentAsString();
		System.out.println(result);
//6.关闭Client对象
		client.close();
		
	}
}

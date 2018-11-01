package com.edu.htmlunitdemo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;

import net.sf.json.JSONObject;

public class HttpUtils {

//	public static String doGet(String url) {
//		
//	}
//	public static String doGet(String url,String para) {
//	
//	}
//	public static String doGet(String url,Map<String, Object> para) {
//	
//		
//	}
//	
//		
//	public static String doPost(String url,JSONObject para) {
//		
//	}
	public static String doPostByForm(String url, Map<String, Object> para) throws Exception {

		String baseUrl="http://study-miniblog-new.qa.netease.com/ajax/user/login";
		WebClient client = new WebClient();
		WebRequest request = new WebRequest(new URL(baseUrl + url), HttpMethod.POST); // 3.…Ë÷√headerµƒContent-Type
		client.addRequestHeader("Content-Type", "	\r\n" + "application/json");
		request.setRequestBody("{\"phoneArea\":\"86\",\"phoneNumber\":\"20000000000\",\"password\":\"netease123\"}");
		Page page = client.getPage(request);
		WebResponse response = page.getWebResponse();
		String result = response.getContentAsString();
		return result;
	}

}

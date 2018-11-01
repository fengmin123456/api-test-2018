package com.edu.htmlunitdemo;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;

import com.gargoylesoftware.htmlunit.util.Cookie;

import net.sf.json.JSONObject;

public class Common {
	
	public static Set<Cookie> getCookie() throws Exception, IOException{
		WebClient client = new WebClient();
		
		WebRequest request = new WebRequest(new URL(""), HttpMethod.POST); // 3.…Ë÷√headerµƒContent-Type
		client.addRequestHeader("Content-Type", "	\r\n" + "application/json");
		
		request.setRequestBody("{\"phoneArea\":\"86\",\"phoneNumber\":\"20000000000\",\"password\":\"netease123\"}");
				Page page = client.getPage(request);
				WebResponse response = page.getWebResponse();
		String result = response.getContentAsString();
		System.out.println("login:"+result);
		Set<Cookie> cookie=client.getCookieManager().getCookies();
		client.close();
		return cookie; 
	}

}

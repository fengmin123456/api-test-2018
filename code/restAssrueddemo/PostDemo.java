package com.edu.restAssrueddemo;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import net.sf.json.JSONObject;

public class PostDemo {
	@Test
	public void postDemo1() {
	
		JSONObject user =new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber", "20000000000");
		user.element("password", "netease123");
		Response response = given().contentType("application/json")
			.body(user.toString())
			.post("http://study-perf.qa.netease.com/common/fgadmin/login");
			response.print();
	}
	@Test
	public void postDemo2() {
	
		Response response = given().contentType("application/x-www-form-urlencoded")
			.param("username","lihuanzhen")
			.param("password","123456")
			.post("http://study-miniblog-new.qa.netease.com/ajax/user/login");
			response.print();
	}
	
	public Map getLoginCookie() {

		JSONObject user =new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber", "20000000000");
		user.element("password", "netease123");
		Response response1 = given().contentType("application/json")
			.body(user.toString())
			.post("http://study-perf.qa.netease.com/common/fgadmin/login");
			response1.print();
			
		Map logincookie=response1.getCookies();
		
		return logincookie;
	}
	
	@Test
	public void postGetCookie() {
	
		Response response2 = given().cookies(this.getLoginCookie()).get("http://study-perf.qa.netease.com/fgadmin/address/list");
//		response2.print();
		int code=response2.getStatusCode();
		System.out.println(code);
//		String result=response2.getBody().asString();
//		System.out.println(result);
		String msg=response2.jsonPath().getString("message");
		
		
	}
}

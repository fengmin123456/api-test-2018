package com.edu.restAssrueddemo;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import javax.sound.sampled.LineListener;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class GetDemo1 {

	@Test
	public void getdemo1() {
		Response  response=given().get("http://study-perf.qa.netease.com/common/skuList?goodsId=1");
		response.print();
	}
	@Test
	public void getdemo11() {
		Response  response=given().param("goodsId", "1").get("http://study-perf.qa.netease.com/common/skuList");
		response.print();
	}
	@Test
	public void getdemo2() {
//		Response response = given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
//					.params("q", "测试","start","0","","2").get("https://api.douban.com/v2/book/search");
//		
		Response response = given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params("q", "测试")
				.params("start","0")
				.params("count","2")
				.get("https://api.douban.com/v2/book/search");
//		或者用以下的方式传参
//		Response response = given().get("https://api.douban.com/v2/book/search?q=测试");
			
		response.print();
	}

	@Test
	public void test1() {
		Response response =given().get("https://api.douban.com/v2/book/search?q=%E6%B5%8B%E8%AF%95&start=0&count=2");
		ArrayList list =response.jsonPath().get("books.subtitle");
	
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
		ArrayList list2 =response.jsonPath().get("books.rating.max");
	}
}

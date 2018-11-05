package com.edu.restAssrueddemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import net.sf.json.JSONObject;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class AssertDemo {
	@Test
	public void test1() {
//		Response response=given()
//				.expect()
//				.body("count",equalTo(21))
//				.body("total", equalTo(14529))
//				.body("books.subtitle", hasItems("像google一样进行软件测试","测试驱动开发的编程技术"))
//				.when().get("https://api.douban.com/v2/book/search?q=%E6%B5%8B%E8%AF%95&start=0&count=2");
		
		Response response=given().get("https://api.douban.com/v2/book/search?q=%E6%B5%8B%E8%AF%95&start=0&count=2");
	JSONObject json=JSONObject.fromObject(response.getBody().toString());

	
		Assert.assertEquals(10000, json.getInt("total"));
	}
	@Test public void
	lotto_resource_returns_200_with_expected_id_and_winners() {

	    given().when().
	            get("/lotto/{id}", 5).
	    then().
	            statusCode(200).
	            body("lotto.lottoId", equalTo(5),
	                 "lotto.winners.winnerId", hasItems(23, 54));

	}
}

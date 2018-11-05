package com.edu.restassure;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class JSONDemo {

	@Test
	public void DoubanDemo() {
		Response response = given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params("q", "����").get("https://api.douban.com/v2/book/search");
		response.print();
		// ��ȡResponse ������ headers �����
		Headers headers = response.getHeaders();
		System.out.println(headers.toString());
		// ��ȡResponse��header��ΪContent-Type��ֵ
		String contentType = response.getHeader("Content-Type");
		System.out.println("contentType:" + contentType);
		// У��ĳ��Header �Ƿ����

		System.out.println(headers.hasHeaderWithName("Server"));
		// ���Response ��headers��Ϊ���򷵻�true
		System.out.println(headers.exist());
		Map<String, String> cookiesMap = response.cookies();
		for (String key : cookiesMap.keySet()) {
			System.out.println(key + ":" + cookiesMap.get(key));
		}
		System.out.println(response.cookie("bid"));
		// ��Response ��bodyת��string����
		System.out.println(response.getBody().asString());
		int count = response.jsonPath().getInt("count");
		System.out.println("count:" + count);
		// ��ȡ���е� subtitle
		ArrayList<String> subtitles = response.jsonPath().get("books.subtitle");
		for (int i = 0; i < subtitles.size(); i++) {
			System.out.println(subtitles.get(i));
		}
		// ��ȡ�ض�ĳ����subtitle
		String subtitle = response.jsonPath().get("books.subtitle[0]");
		System.out.println(subtitle);
		// ��ȡ�����ڶ�����subtitle
		String subtitle1 = response.jsonPath().get("books.subtitle[-2]");
		System.out.println(subtitle1);
		// ��ȡ�ض�tags���µ�����title
		ArrayList<String> tagTitle = response.jsonPath().get("books.tags[2].title");
		for (int i = 0; i < tagTitle.size(); i++) {
			System.out.println(tagTitle.get(i));
		}
		// ��ȡ���е� title
		ArrayList<ArrayList<String>> tagTitles = response.jsonPath().get("books.tags.title");
		for (int i = 0; i < tagTitles.size(); i++)

		{
			for (int j = 0; j < tagTitles.get(i).size(); j++) {
				System.out.println(tagTitles.get(i).get(j));
			}
			System.out.println("---------------------");
		}
		// ��ȡResponse json��������title = "�������"��title
		String title = response.jsonPath().get("books.title.findAll{title ->title==\"�������\"}").toString();
		System.out.println(title);

//		 ��ȡResponse json�� 1< numRaters <=20������ numRaters
		String numRaters = response.jsonPath()
				.get("books.rating.numRaters.findAll{numRaters -> numRaters>100 && numRaters<=400}").toString();
		System.out.println(numRaters);

//		 ��ȡResponse json��title = "��googleһ�������������"��Ӧ�� author
		String title2 = response.jsonPath().get("books.findAll{it.subtitle==\"��googleһ�������������\"}.author").toString();
		System.out.println(title2);


	}
}

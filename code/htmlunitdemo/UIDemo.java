package com.edu.htmlunitdemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class UIDemo {
//	@Test
//	public void test1() {
//		
//	}

	@Test
	public void homePage() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://www.baidu.com");
			
			System.out.println(page.getTitleText());
//			System.out.println("******************");
			//打印html页面的源代码
			System.out.println(page.asXml());
//			System.out.println("******************");
			System.out.println(page.asText());
//			Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
//
//			final String pageAsXml = page.asXml();
//			Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
//
//			final String pageAsText = page.asText();
//			Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
		}
	}
}

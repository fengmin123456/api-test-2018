package com.edu.demo1026;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.VersionInfo;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;

public class Submit {
	CloseableHttpClient httpClient =null;
	@BeforeClass
	public void startUp() {
		httpClient= HttpClients.createDefault();
	}
	@AfterClass
	public void tearDown() throws Exception {
		httpClient.close();
	}
	
	public void doGet(String url) {
		
	}
	public void doPost(String url,JSONObject para) {
		
	}
	@Test(priority=0)
	public void loginSuccess() throws Exception {
		JSONObject success_user = new JSONObject();
		success_user.element("phoneArea", "86");
		success_user.element("phoneNumber", "20000000000");
		success_user.element("password", "netease123");
		
		
		String url="http://study-perf.qa.netease.com/common/fgadmin/login";
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type","application/json");
		HttpEntity data=new StringEntity(success_user.toString());
		post.setEntity(data);
		CloseableHttpResponse respone = httpClient.execute(post);
		
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("login"+content);
		EntityUtils.consume(entity);
		respone.close();
	
	}
	public String address;
	public float fee;
	@Test
	public void getAddress() {
		address=
	}
	
	@Test
	public void getFee() {
		fee=
	}
	
	@Test(priority=1)
	public void submit() throws Exception {
		
		String url="http://study-perf.qa.netease.com/fgadmin/orders/submit";
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type","application/json");
		HttpEntity data=new StringEntity("{\"skuIds\":\"2,3\",\"skuNumbers\":\"1,1\",\"stockIds\":\"74966312,74966313\",\"receiverName\":\"晓晓\",\"cellPhone\":\"15215235548\"\r\n" + 
				",\"addressDetail\":\"河北师大新校区\",\"province\":\"河北省\",\"city\":\"石家庄市\",\"area\":\"裕华区\",\"voiceStatus\":0,\"needInvoice\"\r\n" + 
				":0,\"invoiceHead\":\"\",\"transportFee\":0,\"logisticsCompanyId\":1,\"accessSource\":\"noSource\",\"accessDevice\"\r\n" + 
				":0}");
		post.setEntity(data);
		CloseableHttpResponse respone = httpClient.execute(post);
		
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("submit"+content);
		EntityUtils.consume(entity);
		respone.close();
	
	}

}

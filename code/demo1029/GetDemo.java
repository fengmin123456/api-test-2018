package com.edu.demo1029;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import net.sf.json.JSONObject;

public class GetDemo {
	@Test
	public void skulist() throws Exception {

		String result = HttpDriver.doGet(ReadPro.getProValue("url")+"/common/skuList");
		System.out.println(result);

	}
	
	@Test
	public void skulist1() throws Exception {

		String result = HttpDriver.doGet(ReadPro.getProValue("url")+"/common/skuList","goodsId=1");
		System.out.println(result);

	}
	
	@Test
	public void mapToString() throws IOException, Exception { 
	
				
		Map<String,String> map = new HashMap();
		
		map.put("goodsId", "1");
		map.put("addressDetail", "Ê¯¼Ò×¯");
		HttpDriver.doGet(ReadPro.getProValue("url")+"/common/skuList",map);

	}
//	@Test
//	public void getByJSON() {
//		String  baseUrl="http://127.0.0.1:8080/Supermarket/analysis/lookupprice";
//	
//		JSONObject para = new JSONObject();
//		para.element("pId", "123456");
//		String result=HttpDriver.doGet(baseUrl, para);
//		
//	}

}

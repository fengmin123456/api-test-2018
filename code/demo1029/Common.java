package com.edu.demo1029;

import java.io.IOException;

import org.apache.http.client.CookieStore;

import net.sf.json.JSONObject;

public class Common {
	public static CookieStore getLoginCookie() throws IOException, Exception {
		JSONObject user = new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber", "20000000000");
		user.element("password", "netease123");

		CookieStore cookie = HttpDriver.doPostgetCookie(ReadPro.getProValue("url") + "/common/fgadmin/login", user);
		return cookie;
	}

}

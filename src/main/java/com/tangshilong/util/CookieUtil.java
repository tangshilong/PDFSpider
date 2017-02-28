package com.tangshilong.util;

import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
	public static Map<String, String> getCookie() {
		Map<String, String> values = new HashMap<>();
		String cookieString = getCookieString();
		String[] split = cookieString.split("; ");
		for (String string : split) {
			int indexOf = string.indexOf("=");
			String key = string.substring(0, indexOf);
			String value = string.substring(indexOf + 1, string.length());
			values.put(key, value);
		}
		return values;
	}

	public static String getCookieString() {
		return "bdshare_firstime=1487776497482; Hm_lvt_cb1b8b99a89c43761f616e8565c9107f=1487776502; ASL=17219,0000x,2405c019; ADVC=34db3f9a44ebe3; hxck_sqkf_home_userbind_tip=1; hxck_sq_home_logincout29199426=1|20170222; hxck_sq_zyjca=tZfhj4S2fWsTXiJe3iRnx4UdM2eqZ1q8mehztMqi5OQ=; hxck_sq_common=LoginStateCookie=mRMWZPL%2fpH0YBgXDblG03g%3d%3d&SnapCookie=mRMWZPL%2fpH1oVIcMk3CdJB3PKWytNeHM29ONUr3FMbcDl1rSGfCE5%2bzp920f%2f4hGzn2hNqoF2GAcPBbSC4yqJX00GB9yEVxYHI8Vo2baKblTRqIH%2bVlFIQ%3d%3d; ASP.NET_SessionId=2qvb10innznbpzuquf3iw53j; userToken=29199426%7c0000%7c0%2cTN%2bjyhbXz8teWpvob2jWneFsLL2Gst22EnkavpO53cgOQechRwsBYy9px5ZNjjS4pJpi4SUrkl2%2beEzBZVN8GB8tYSVGIeRcKxGWzAcRnI5wjqx7IBTNgINSRReYtT1LThdAF%2fLvFNn3WPL49mFFCBcZqx6q2An5UUe3ilJoOU%2fpp7iCKeE2j07w0ll9BEqA9VSzZpvYghwkt1aM4H6rYg%3d%3d; hxck_fsd_lcksso=765785F554A89C3183FEF79B03143448BF6EEA62902F4CB9EFEDDCAD6E49AAFBBB80290FFC68A999F4921D490DEC535718A84F39C4D97A255BF97710E6336537AF4B0DBCFA04F210881CBEBB1F7232F085222C1135CE5483A4AEB4170D90085A8802CEB2563B1BBC; HexunTrack=SID=20170222231455146883f585602e640058b09cd803c7fb93e&CITY=34&TOWN=340100";
	}

	public static void main(String[] args) {
		getCookie();
	}
}

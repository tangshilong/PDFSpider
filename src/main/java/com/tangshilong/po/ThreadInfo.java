package com.tangshilong.po;

import com.tangshilong.dao.InfoDao;
import com.tangshilong.util.CookieUtil;
import com.xiechanglei.code.utils.http.HttpRequest;
import com.xiechanglei.code.utils.http.HttpResponse;
import com.xiechanglei.code.utils.http.HttpUtil;
import com.xiechanglei.code.web.page.catcher.CatchHandler;
import com.xiechanglei.code.web.page.catcher.Resolver;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ThreadInfo extends Thread {
	private int num;

    @Override
	public void run() {
		int num2 = this.num;
		String homeUrl = "http://yanbao.stock.hexun.com/listnews1_" + num2 + ".shtml";
		HttpRequest requestInfo = HttpRequest.createDefaultRequestInfo();
		requestInfo.setCookies(CookieUtil.getCookie());
		requestInfo.setCharset("GBK");
		HttpResponse responseInfo;
		try {
			responseInfo = HttpUtil.get(homeUrl, requestInfo);
			CatchHandler.parse(responseInfo.getContent(), new Resolver() {
				@Override
				public void success() {
					Elements elements = $(".tab_cont tr").getElements();
					List<Info> infos = new ArrayList<
                            >();
					for (Element element : elements) {
						if (!element.text().contains("研究报告标题 研报来源 研报作者 投资评级 研报日期 摘要")) {
							// 信息分开存储
							Info info = new Info();
							info.setName(element.select("td:eq(0)").text());
							info.setSource(element.select("td:eq(1)").text());
							info.setWriter(element.select("td:eq(2)").text());
							info.setLevel(element.select("td:eq(3)").text());
							info.setDate(element.select("td:eq(4)").text());
							info.setUrl(
									"http://yanbao.stock.hexun.com/" + element.select("td:eq(0) .fxx_wb").attr("href"));
							infos.add(info);
						}
					}
					try {
						save(infos);
					} catch (Exception e) {
						System.out.println("信息存储出错！");
						e.printStackTrace();
					}
				}

			});
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		System.out.println(num2);
	}

	private static void save(List<Info> infos) throws Exception {
		for (Info info : infos) {
			InfoDao.saveInfo(info);
		}
	}

	public ThreadInfo(int num) {
		super();
		this.num = num;
	}

}

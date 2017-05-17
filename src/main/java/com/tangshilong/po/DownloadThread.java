package com.tangshilong.po;

import com.tangshilong.dao.InfoDao;
import com.tangshilong.util.CookieUtil;
import com.xiechanglei.code.web.page.catcher.CatchHandler;
import com.xiechanglei.code.web.page.catcher.RequestInfo;
import com.xiechanglei.code.web.page.catcher.Resolver;
import org.apache.log4j.lf5.util.StreamUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class DownloadThread implements Runnable {
	private int i;

	public DownloadThread(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println("开启下载线程------------------" + this.i);
		while (true) {
			System.out.println("获取线程(" + this.i + ")需要下载的数据");
			List<Info> list = getData();
			System.out.println("获取成功(" + this.i + ")");
			if (list == null || list.size() == 0) {
				System.out.println("获取成功(" + this.i + ") 暂无数据需要下载，退出");
				break;
			} else {
				System.out.println("获取成功(" + this.i + ") 发现" + list.size() + "条数据，开始下载");
				for (Info info : list) {
					System.out.println("开始检索:" + info.getName());
					download(info);
				}
			}
		}
	}

	private void download(Info info) {
		try {
			RequestInfo request = RequestInfo.createDefaultRequestInfo();
			request.setCookies(CookieUtil.getCookie());
			CatchHandler.get(info.getUrl(), request, new Resolver() {
				@Override
				public void success() {
					System.out.println("检索成功!");
					String name = rename(info);
					name = name.replaceAll("\\*", " ");//星号不符合文件名语法
					File file = new File("h:spider\\" + info.getId() % 32 + "\\" + name + ".pdf.temp");
					try {
						if (!file.exists()) {
							file.getParentFile().mkdirs();
							file.createNewFile();
						}
						System.out.println("开始下载");
						downloadUrl($(".check-pdf").attr("href"), file);
						file.renameTo(new File("h:spider\\" + info.getId() % 32 + "\\" + name + ".pdf"));
						System.out.println("下载成功,更新数据库记录s");
						System.out.println("****************************************************  "+info.getId());
						InfoDao.changeStatus(info.getId());
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(info.getId()+"__"+info.getName());
						System.out.println("下载异常......");
					}
				}
			});

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	private List<Info> getData() {
		try {
			return InfoDao.getListByI(this.i);
		} catch (Exception e) {
			System.out.println("获取数据异常，结束下载程序");
			return new ArrayList<>();
		}
	}

	private static void downloadUrl(String url, File file) throws IOException {
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
		connection.setRequestProperty("Referer", "http://yanbao.stock.hexun.com/dz_gg.aspx?gcode=761416");
		connection.setRequestProperty("Cookie", CookieUtil.getCookieString());
		InputStream inputStream = null;
		file.createNewFile();
		OutputStream outputStream = null;
		try {
			inputStream = connection.getInputStream();
			outputStream = new FileOutputStream(file);
			StreamUtils.copy(inputStream, outputStream);
		} catch (IOException e) {
			assert outputStream != null;
			outputStream.close();
			outputStream = null;
			e.printStackTrace();
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	private static String rename(Info info) {
		String[] names = info.getWriter().split(" ");
		StringBuilder writer = new StringBuilder();
		for (int i = 0; i < names.length; i++) {
			if (i == 0) {
				writer.append(names[i]);
			} else {
				writer.append(",").append(names[i]);
			}
		}
		return info.getName() + "-" + info.getSource() + "-" + writer + "-" + info.getLevel() + "-"
				+ info.getDate();
	}
}

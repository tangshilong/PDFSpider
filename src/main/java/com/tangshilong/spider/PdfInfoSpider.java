package com.tangshilong.spider;

import com.tangshilong.po.ThreadInfo;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * http://yanbao.stock.hexun.com/dzgg761417.shtml
 * 
 * @author tangshilong
 *
 */
public class PdfInfoSpider {
	private static final int MAX = 4030;

	private static final int MAX_THREAD = 40;

	public static void main(String[] args) throws IOException {
		ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD);

		/*
		 * 从网站下载pdf信息
		 */
		// ****************************************
		for (int i = 1; i <= MAX; i++) {
			pool.execute(new ThreadInfo(i));
		}
		// ****************************************
	}

}

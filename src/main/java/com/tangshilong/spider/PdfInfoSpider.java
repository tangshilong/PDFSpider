package com.tangshilong.spider;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tangshilong.po.ThreadInfo;

/**
 * http://yanbao.stock.hexun.com/dzgg761417.shtml
 * 
 * @author tangshilong
 *
 */
public class PdfInfoSpider {
	public static final int MAX = 4030;

	public static final int MAX_THREAD = 40;

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

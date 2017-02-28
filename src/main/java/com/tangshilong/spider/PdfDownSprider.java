package com.tangshilong.spider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tangshilong.po.DownloadThread;

public class PdfDownSprider {
	public static final int MAX_THREAD = 50;

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD);
		for (int i = 0; i < MAX_THREAD; i++) {
			pool.execute(new DownloadThread(i));
		}
	}
}

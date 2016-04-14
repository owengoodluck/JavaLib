package com.owen.wms.web.service;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.owen.wms.web.thread.PictureDownLoadThread;

public class PictureDownloadService {
	
	private final ExecutorService pool;
	
	public PictureDownloadService(){
		pool = Executors.newFixedThreadPool(10);
	}
	
	public void downloadPic(String url, File downloadFolder){
		PictureDownLoadThread t = new PictureDownLoadThread(url,downloadFolder);
		pool.execute(t);
	}
}

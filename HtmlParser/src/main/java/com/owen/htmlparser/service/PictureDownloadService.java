package com.owen.htmlparser.service;

import java.util.List;

public interface PictureDownloadService {
	
	void downloadPictue(String url,String targetRootFolder,Integer picFilterSize);
	
	List<String> pareseAmazonUrlToGetPictureUrlList(String url);
}

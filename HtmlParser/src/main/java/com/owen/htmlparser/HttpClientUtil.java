package com.owen.htmlparser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HtmlParser {
	private String url = "http://detail.1688.com/offer/40923537011.html?spm=a261y.7663282.1998411376.2.CSbdHm";

	
	public static String getHtmlSource(String url) throws Exception {

		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		// ��ȡ��Ӧʵ��
		HttpEntity entity = response.getEntity();
		// ��ӡ��Ӧ״̬
		// System.out.println(response.getStatusLine());
		if (entity != null) {
			// ��ӡ��Ӧ���ݳ���
			// System.out.println("Response content length: " +
			// entity.getContentLength());
			// ��ӡ��Ӧ����
			String content = EntityUtils.toString(entity);
			// System.out.println("Response content: " + content);
			
//			parseHtmlPicture(content, labelStart, labelEnd, downloadRootFolder);
			return content;
		}else{
			System.out.println("fail to get content");
			return null;
		}
	}

	public static void parseHtmlPicture(String html,String labelStart,String labelEnd,String downloadRootFolder)throws Exception{
		//create subfolder for the url
		File subFolder=makeSubFolder(downloadRootFolder);
		
		String imgUrl=null;
		int lengthOfOriginalLabel= labelStart.length();
		int indexBegin=html.indexOf(labelStart);
		int indexEnd=-1;
		while(indexBegin!=-1){
			indexBegin+=lengthOfOriginalLabel;
			indexEnd = html.indexOf( labelEnd ,indexBegin);
			if(indexEnd!=-1){
				imgUrl = html.substring(indexBegin, indexEnd);
				downloadPicture(imgUrl,subFolder.getAbsolutePath());
			}else{
				System.out.println("can't find original lable ");
				break;
			}
			indexBegin=html.indexOf(labelStart,indexEnd);
		}
	}
	
	public static void parseHtmlPicture4TaoBao(String html,String downloadRootFolder)throws Exception{
		//create subfolder for the url
		File subFolder=makeSubFolder(downloadRootFolder);
		
		String lableBegin="auctionImages:[";
		String lableEnd="]";
		int lengthOfLableBegin= lableBegin.length();
		int indexBegin=html.indexOf(lableBegin);
		int indexEnd=-1;
		if(indexBegin!=-1){
			indexBegin+=lengthOfLableBegin;
			indexEnd = html.indexOf( lableEnd ,indexBegin);
			if(indexEnd!=-1){
				String imgUrls= html.substring(indexBegin,indexEnd);
				String[] urls = imgUrls.split(",");
				for(String url:urls){
					downloadPicture("http:"+url.trim().replace("\"", ""),subFolder.getAbsolutePath());
				}
			}else{
				System.out.println("can't find the end lable");
			}
		}else{
			System.out.println("Can't find lable of [auctionImages:]");
		}
	}
	
	private static File  makeSubFolder(String downloadRootFolder){
		File rootFolder = new File(downloadRootFolder);
		File subFolder=null;
		if(!rootFolder.exists()){
			rootFolder.mkdir();
		}
		String[] list = rootFolder.list();
		if(list==null || list.length==0){
			subFolder= new File(downloadRootFolder+"/1");
		}else{
			subFolder= new File(downloadRootFolder+"/"+(list.length+1));
		}
		subFolder.mkdir();
		return subFolder;
		
	}
	
	private static void downloadPicture(String url,String downloadFolder) throws Exception{
		System.out.println(url+">>>>>>>>>>>>>>>>>"+downloadFolder);
		HttpClient client =  new DefaultHttpClient();  
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = client.execute(httpget);
        File storeFile = new File(downloadFolder+"/"+url.substring(url.lastIndexOf("/")));  
        FileOutputStream output = new FileOutputStream(storeFile);  
        InputStream instream = response.getEntity().getContent();
        try {
	        byte b[] = new byte[1024];
	        int j = 0;
	        while( (j = instream.read(b))!=-1){
	        output.write(b,0,j);
	        }
        }finally{
	        output.flush();
	        output.close();
        }
	}
	
	
	public static void main(String[] args) throws Exception{
		//test 1688
		String url="http://detail.1688.com/offer/41136916713.html?spm=0.0.0.0.wzVln0";
		String downloadRootFolder="C:/Users/owen/Desktop/Amazon/��Դ/0916";
		String originalLabel="\",\"original\":\"";
		String endLabel="\"}'>";
	}
}
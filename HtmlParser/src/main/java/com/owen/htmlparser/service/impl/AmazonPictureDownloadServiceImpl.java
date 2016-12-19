package com.owen.htmlparser.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.owen.htmlparser.service.PictureDownloadService;
import com.owen.htmlparser.util.FileUtil;
import com.owen.htmlparser.util.HtmlParserUtil;
import com.owen.htmlparser.util.PictureDownloadUtil;
import com.owen.htmlparser.util.StringUtil;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class AmazonPictureDownloadServiceImpl implements PictureDownloadService {

	private Logger log = Logger.getLogger(this.getClass());
	
	public List<String> pareseAmazonUrlToGetPictureUrlList(String url){
		List<String> picUrlList = new ArrayList<String> ();
		
		//1.  get html content
		String htmlContent = null;
		try {
			htmlContent = HtmlParserUtil.getHtmlContent(url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		//2. getJsonStringOfPics
		String jsonStringOfPics = this.getJsonStringOfPics(htmlContent);
		
		//3. parse JSON string
		Map<String, List<String>> map = this.parseSubitemsPic(jsonStringOfPics);
		if(map!=null &&!map.isEmpty()){
			for(String key:map.keySet()){
				if(map.get(key)!=null){
					picUrlList.addAll(map.get(key));
				}
			}
		}
		
		return picUrlList;
	}
	
	public void downloadPictue(String url, String targetRootFolder,Integer picFilterSize) {
		this.log.info("-----Parse Amazon URL: "+url);
		//1.  get html content
		String htmlContent = null;
		try {
			htmlContent = HtmlParserUtil.getHtmlContent(url);
//			title = HtmlParserUtil.getHtmlTitleTag(htmlContent);
//			title.replace("Amazon.com", "");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return;
		}

		//2. create sub folder
//		title = StringUtil.removeIlleaglePathCharacter(title);
		String asin = StringUtil.getAmazonASINFromUrl(url);
		File subFolder = new File(targetRootFolder+"/"+asin);
		if(!subFolder.exists()){
			if(subFolder.mkdirs()){
				this.log.info(subFolder.getAbsolutePath()+" folder is created ");
			}else{
				this.log.info(subFolder.getAbsolutePath()+" folder is fail to be created ");
			}
		}

		//3. create short cut url
		FileUtil.createInternetShortcut(subFolder.getAbsolutePath()+"/Amazon_"+asin+".url", url);

		//4
		String jsonDataOfPics =  this.getJsonStringOfPics(htmlContent);
		
		//5.
		Map<String, List<String>> map = this.parseSubitemsPic(jsonDataOfPics);
		if(map!=null &&!map.isEmpty()){
			for(String key:map.keySet()){
				File subFolderKey = new File(subFolder.getAbsolutePath()+"/"+key.replaceAll(" ", ""));
				if(!subFolderKey.exists()){
					subFolderKey.mkdirs();
				}
				if(map.get(key)!=null){
					for(String picUrl:map.get(key)){
						PictureDownloadUtil.downloadPicture(picUrl, subFolderKey,picFilterSize);
					}
				}
			}
		}
	}

	
	private String getJsonStringOfPics(String htmlContent){
		String jsonDataOfPics = null;
		String originalLabel = "data[\"colorImages\"] =";
		String endLabel = "data[\"heroImage\"]";
		int indexStart = htmlContent.indexOf(originalLabel);
		int indexEnd = htmlContent.indexOf(endLabel);
		//check if sub item data exists
		if(indexStart >-1){
			//there are sub items
			jsonDataOfPics = htmlContent.substring(indexStart+originalLabel.length(), indexEnd-1).trim();
			if(jsonDataOfPics.endsWith(";")){
				jsonDataOfPics = jsonDataOfPics.substring(0, jsonDataOfPics.length()-1);
			}
		}
		
		//no sub items
		if(jsonDataOfPics.trim().length()<=2){
			originalLabel = " 'colorImages':";
			endLabel = "'colorToAsin':";
			indexStart = htmlContent.indexOf(originalLabel);
			indexEnd = htmlContent.indexOf(endLabel);
			jsonDataOfPics = htmlContent.substring(indexStart+originalLabel.length(), indexEnd-1).trim();
			if(jsonDataOfPics.endsWith(",")){
				jsonDataOfPics = jsonDataOfPics.substring(0, jsonDataOfPics.length()-1);
			}
		}
		
		this.log.info(jsonDataOfPics);
		return jsonDataOfPics;
	}
	
	
	/**
	 * Parse amazon json data to get all sub item's pic url
	 * @param jsonString
	 */
	private Map<String,List<String>> parseSubitemsPic(String jsonString) {
		Map<String,List<String>> urlMap = new HashMap<String,List<String>>();
		
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonString);
		Map<String, ArrayList> map = (Map) JSONObject.toBean(jsonObject, Map.class);
		
		for(String key:map.keySet()){
			List<String> picUrlList = new ArrayList<String> ();
			ArrayList<MorphDynaBean> subItemPicList = map.get(key);
			
			//for each sub item , there are pictures
			for (MorphDynaBean pic : subItemPicList) {
				String picUrl=null;
				try {
					picUrl = pic.get("hiRes").toString();
				} catch (Exception e) {
					picUrl = pic.get("large").toString();
				}
				picUrlList.add(picUrl);
			}
			
			if(!picUrlList.isEmpty()){
				urlMap.put(key, picUrlList);
			}
		}
		return urlMap;
	}
}

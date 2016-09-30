package com.owen.wms.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileUtil {
	private static Logger log = Logger.getLogger(FileUtil.class);
	
	public static void writeObject(Serializable obj,File file){
		if(file==null){
			log.warn("Target file doesn't exist ");
		}else{
			ObjectOutputStream out = null;  
			try {
				out= new ObjectOutputStream(new FileOutputStream(file,true));
				out.writeObject(obj);
				out.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(out!=null){
					try { out.close();  } catch (IOException e) { }
				}
			}
		}
	}
	
	public static File createSubFolder(String downloadRootFolder) {
		File rootFolder = new File(downloadRootFolder);
		File subFolder = null;
		if (!rootFolder.exists()) {
			rootFolder.mkdir();
		}
		String[] list = rootFolder.list();
		if (list == null || list.length == 0) {
			subFolder = new File(downloadRootFolder + "/1");
		} else {
			subFolder = new File(downloadRootFolder + "/" + (list.length + 1));
		}
		subFolder.mkdir();
		return subFolder;
	}

	public static void write2File(String content, File file) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(content);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void write2File(String content, String filePath, String... extensions) {
		if (extensions != null && extensions.length > 0) {
			for (String ext : extensions) {
				if ("url".equals(ext)) {
					createInternetShortcut(filePath+".url"  ,content);
				} else {
					write2File(content, new File(filePath + "." + ext));
				}
			}
		} else {
			write2File(content, new File(filePath));
		}
	}

	public static void createInternetShortcut( String filePath, String target) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filePath);
			fw.write("[InternetShortcut]\r\n");
			fw.write("URL=" + target + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFile2String( String filePath) {
		StringBuffer buf = new StringBuffer();
		FileInputStream fin = null;
		BufferedReader br = null;
		try{
			fin = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(fin));
			String str = br.readLine();
			while (str != null) {
				buf.append(str);
				str = br.readLine();
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			return null;
		}finally{
			try {
				if(br!=null ) br.close();
			} catch (IOException e) { }
			try {
				if(fin!=null ) fin.close();
			} catch (IOException e) { }
		}
		return buf.toString();
	}

	public static void copy(String oldPath, String newPath){
		copy(new File(oldPath), new File(newPath));
	}
	
	public static void copy(File oldfile, File newFile) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int byteread = 0;
			// File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				inStream = new FileInputStream(oldfile);
				fs = new FileOutputStream(newFile);
				byte[] buffer = new byte[4096];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
			}
			fs.flush();
		} catch (Exception e) {
			log.error(e.getMessage());
		}finally{
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e) { }
			}
			if(inStream!=null){
				try {
					inStream.close();
				} catch (IOException e) { }
			}
		}
	}
	
	public static List<String>  readFile2List( String filePath,boolean ignoreEmptyLine) {
		List<String> list = new ArrayList<String>();
		FileInputStream fin = null;
		BufferedReader br = null;
		try{
			fin = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(fin));
			String str = br.readLine();
			while (str != null) {
				if(str.trim().length()>0 || ignoreEmptyLine){
					list.add(str);
				}
				str = br.readLine();
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			return null;
		}finally{
			try {
				if(br!=null ) br.close();
			} catch (IOException e) { }
			try {
				if(fin!=null ) fin.close();
			} catch (IOException e) { }
		}
		return list;
	}
	
	final static void showAllFiles(File dir) throws Exception {
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].isDirectory()) {
				try {
					showAllFiles(fs[i]);
				} catch (Exception e) { }
			}else{
				String newFileName = fs[i].getParent()+"\\"+getNumber(fs[i].getName())+".flv";
				System.out.println(fs[i].getAbsolutePath()+"====="+newFileName);
				fs[i].renameTo(new File(newFileName));
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		File f = new File("D:/乡村爱情2");
//		showAllFiles(f);
		addBaseNumber(30,f);
	}
	
	public static String getNumber(String str){
		if(str == null){
			return null;
		}else{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < str.length(); i++){
				  if (Character.isDigit(str.charAt(i))){
					   sb.append(str.charAt(i));
				  }
			}
			return sb.toString();
		}
	}
	
	public static void addBaseNumber(int num,File dir){
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].isFile()) {
				String newFileName = fs[i].getParent()+"\\"+(Integer.valueOf(getNumber(fs[i].getName()))+num)+".flv";
				System.out.println(fs[i].getAbsolutePath()+"====="+newFileName);
				fs[i].renameTo(new File(newFileName));
			}
		}
	}
}

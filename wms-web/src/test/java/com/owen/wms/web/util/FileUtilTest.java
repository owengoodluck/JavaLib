package com.owen.wms.web.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.owen.htmlparser.util.FileUtil;

public class FileUtilTest {

	private Logger log = Logger.getLogger("expressLogger");
	
	@Test
	public void testWriteString2File() throws Exception{
		String input ="<xml>test123</xml>";
		File outputFile = new File("C:/Users/owen/git/wms-web/src/test/resources/testWriteString2File.xml");
		FileUtil.write2File(input , outputFile );
	}
	
	@Test
	public void testLogger(){
		this.log.info("test");
		this.log.info("test");
		this.log.info("test");
		this.log.info("test");
		this.log.info("test");
	}
}

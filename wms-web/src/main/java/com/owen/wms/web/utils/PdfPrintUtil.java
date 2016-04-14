package com.owen.wms.web.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.apache.log4j.Logger;

public class PdfPrintUtil {

	private static Logger log = Logger.getLogger(PdfPrintUtil.class);
	
	public static void main(String[] args) throws Exception{
		String filePath = "C:/Users/owen/Desktop/tmp/20151117_091204_052.pdf";
		printViaCommandLine(filePath);
	}

	public static void printViaCommandLine(String filePath) throws Exception{
		String command = "C:/Program Files (x86)/Adobe/Reader 11.0/Reader/AcroRd32.exe /s /o /N /T "+filePath;
		log.info(command);
		Process p = Runtime.getRuntime().exec(command);
		StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");              
		errorGobbler.start();  
//        p.waitFor();
	}
	
	private static PrintService getDefaultPrinter(){
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		return getDefaultPrinter(flavor, patts);
	}
	
	private static PrintService getDefaultPrinter(DocFlavor flavor , PrintRequestAttributeSet patts){
		PrintService service = null;
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
		if (ps.length == 0) {
		    throw new IllegalStateException("No Printer found");
		}else{
			service = ps[0];
		    System.out.println(service.getName());
		}
		return service;
	}
	
	public static void test(String filePath) throws PrintException, IOException {
	    PrintService myService = getDefaultPrinter();
	    FileInputStream fis = new FileInputStream(filePath);
	    Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
	    DocPrintJob printJob = myService.createPrintJob();
	    printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
	    fis.close();        
	}
}

class StreamGobbler extends Thread {    
    InputStream is;    
    String type;    
    OutputStream os;    
            
    StreamGobbler(InputStream is, String type) {    
        this(is, type, null);    
    }    
    
    StreamGobbler(InputStream is, String type, OutputStream redirect) {    
        this.is = is;    
        this.type = type;    
        this.os = redirect;    
    }    
        
    public void run() {    
        InputStreamReader isr = null;    
        BufferedReader br = null;    
        PrintWriter pw = null;    
        try {    
            if (os != null)    
                pw = new PrintWriter(os);    
                    
            isr = new InputStreamReader(is);    
            br = new BufferedReader(isr);    
            String line=null;    
            while ( (line = br.readLine()) != null) {    
                if (pw != null)    
                    pw.println(line);    
                System.out.println(type + ">" + line);        
            }    
                
            if (pw != null)    
                pw.flush();    
        } catch (IOException ioe) {    
            ioe.printStackTrace();      
        } finally{    
            pw.close();
            try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }    
    }    
} 
package com.owen.wms.web.utils;

public class PdfPrintThread extends Thread{

	private String command;
	
	public PdfPrintThread(String command){
		this.command = command;
	}

	@Override
	public void run() {
		try {
			PdfPrintUtil.printViaCommandLine(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

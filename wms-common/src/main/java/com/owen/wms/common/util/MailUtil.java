package com.owen.wms.common.util;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.lf5.util.StreamUtils;

public class MailUtil {

	private static final String folder ="C:\\Users\\owen\\Desktop\\tmp\\yanwen\\";
	public static void main(String[] args) throws Exception {
		reciveMail();
	}

	
	public static void reciveMail() throws Exception {
		String protocol = "pop3";
		boolean isSSL = true;
		String host = "pop.163.com";
		int port = 995;
		String username = "oushunwu@163.com";
		String password = null;//System.in.read();

		System.out.print("请输入密码：");
		Scanner  sc=new Scanner(System.in);
		password=sc.next();
		
		Properties props = new Properties();
		props.put("mail.pop3.ssl.enable", isSSL);
		props.put("mail.pop3.host", host);
		props.put("mail.pop3.port", port);

		Session session = Session.getDefaultInstance(props);

		Store store = null;
		Folder folder = null;
		try {
			store = session.getStore(protocol);
			store.connect(username, password);

			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			int size = folder.getMessageCount();
			
			for(int i=size;i>0;i--){
				Message message = folder.getMessage(i);
				processYanwenMessage(message);
			}

			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (folder != null) {
					folder.close(false);
				}
				if (store != null) {
					store.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	private static void processYanwenMessage(Message msg) throws Exception{
		if(!isYanwenBill(msg)){
			return;
		}
		
		 //check if the content has attachment
	   if (msg.isMimeType("multipart/*")) {
	         Multipart mp = (Multipart) msg.getContent();
	         int count = mp.getCount();
	         for (int i = 0; i < count; i++){
	        	 BodyPart part = mp.getBodyPart(i);
	        	 processYanwenBodyPart(part);
	         }
	      } 
	}

	private static void processYanwenBodyPart(BodyPart p) throws Exception {
		Object o = p.getContent();
		if (o instanceof String) {
			System.out.println("This is a string");
			System.out.println("---------------------------");
			System.out.println((String) o);
		} else if (o instanceof InputStream) {
			System.out.println("This is just an input stream");
			System.out.println("---------------------------");
			InputStream is = (InputStream) o;
			File f = new File(folder+System.currentTimeMillis()+".xls");
			OutputStream os = new FileOutputStream(f);
			StreamUtils.copy(is, os, 1024);
			is.close();
			os.flush();
			os.close();
		} else {
			System.out.println("This is an unknown type");
			System.out.println("---------------------------");
			System.out.println(o.toString());
		}
	}

	/**
	 * Check the mail if it is a yanwen bill
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	private static Boolean isYanwenBill(Message msg) throws Exception {
		if (msg == null) {
			return false;
		}
		String subject = msg.getSubject();
		if (subject != null && subject.indexOf("电子帐单") > -1 && subject.indexOf("燕文物流") > -1) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * This method checks for content-type based on which, it processes and
	 * fetches the content of the message
	 */
	public static void writePart(Part p) throws Exception {
		if (p instanceof Message) {
			// Call methos writeEnvelope
			writeEnvelope((Message) p);
		}

		System.out.println("----------------------------");
		System.out.println("CONTENT-TYPE: " + p.getContentType());

		// check if the content is plain text
		if (p.isMimeType("text/plain")) {
			System.out.println("This is plain text");
			System.out.println("---------------------------");
			System.out.println((String) p.getContent());
		}
		// check if the content has attachment
		else if (p.isMimeType("multipart/*")) {
			System.out.println("This is a Multipart");
			System.out.println("---------------------------");
			Multipart mp = (Multipart) p.getContent();
			int count = mp.getCount();
			for (int i = 0; i < count; i++)
				writePart(mp.getBodyPart(i));
		}
		// check if the content is a nested message
		else if (p.isMimeType("message/rfc822")) {
			System.out.println("This is a Nested Message");
			System.out.println("---------------------------");
			writePart((Part) p.getContent());
		}
		// check if the content is an inline image
		// else if (p.isMimeType("image/jpeg")) {
		// System.out.println("--------> image/jpeg");
		// Object o = p.getContent();
		//
		// InputStream x = (InputStream) o;
		// // Construct the required byte array
		// System.out.println("x.length = " + x.available());
		// int i = 0;
		// while ((i = (int) ((InputStream) x).available()) > 0) {
		// int result = (int) (((InputStream) x).read(bArray));
		// if (result == -1)
		// byte[] bArray = new byte[x.available()];
		//
		// break;
		// }
		// FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
		// f2.write(bArray);
		// }
		else if (p.getContentType().contains("image/")) {
			System.out.println("content type" + p.getContentType());
			File f = new File("image" + new Date().getTime() + ".jpg");
			DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			com.sun.mail.util.BASE64DecoderStream test = (com.sun.mail.util.BASE64DecoderStream) p.getContent();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = test.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		} else {
			Object o = p.getContent();
			if (o instanceof String) {
				System.out.println("This is a string");
				System.out.println("---------------------------");
				System.out.println((String) o);
			} else if (o instanceof InputStream) {
				System.out.println("This is just an input stream");
				System.out.println("---------------------------");
				InputStream is = (InputStream) o;
				is = (InputStream) o;
				int c;
				while ((c = is.read()) != -1)
					System.out.write(c);
			} else {
				System.out.println("This is an unknown type");
				System.out.println("---------------------------");
				System.out.println(o.toString());
			}
		}

	}

	/*
	 * This method would print FROM,TO and SUBJECT of the message
	 */
	public static void writeEnvelope(Message m) throws Exception {
		System.out.println("This is the message envelope");
		System.out.println("---------------------------");
		Address[] a;

		// FROM
		if ((a = m.getFrom()) != null) {
			for (int j = 0; j < a.length; j++)
				System.out.println("FROM: " + a[j].toString());
		}

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++)
				System.out.println("TO: " + a[j].toString());
		}

		// SUBJECT
		if (m.getSubject() != null)
			System.out.println("SUBJECT: " + m.getSubject());

	}

}

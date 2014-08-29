package net;

import java.io.*;

public class Netsh {
	public static String execute(String command) throws IOException{
		Process process = Runtime.getRuntime().exec("netsh "+command);
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Reader reader = new InputStreamReader(process.getInputStream());
		StringBuffer ouputBuffer = new StringBuffer();
		char[] buffer = new char[100];
		int numRead = 0;
		while( (numRead = reader.read(buffer)) > 0 )
			ouputBuffer.append(buffer, 0, numRead);
		
		return ouputBuffer.toString();
	}
}

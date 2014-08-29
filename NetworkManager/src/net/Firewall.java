package net;

import java.io.*;

public class Firewall {
	public static void enable(){
		try {
			Runtime.getRuntime().exec("netsh advfirewal set allprofiles state on");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void disable(){
		try {
			Runtime.getRuntime().exec("netsh advfirewal set allprofiles state off");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

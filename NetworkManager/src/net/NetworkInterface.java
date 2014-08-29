package net;

import java.io.*;
import java.util.*;

public class NetworkInterface {
	private final String name;
	private final Map<String, String> params;

	NetworkInterface(String name, Map<String, String> params){
		this.name = name;
		Map<String, String> rawParams = new HashMap<String, String>();
		rawParams.putAll(params);
		this.params = Collections.unmodifiableMap(rawParams);
	}
	
	public String getInterfaceName(){
		return name;
	}
	
	public String getParamValue(String key){
		return params.get(key);
	}
	
	public Map<String, String> getParams(){
		return params;
	}

	public String getIPAddress() {
		return getParamValue("IP Address");
	}

	public void setDhcp() {
		try {
			System.out.println(Netsh.execute("interface ip set address \""+name+"\" dhcp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setIp(String ip, String netmask, String gateway) {
		try {
			System.out.println(Netsh.execute("interface ip set address name=\""+name+"\" static "+ip+" "+netmask+" "+gateway));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

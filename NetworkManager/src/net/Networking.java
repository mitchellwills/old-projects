package net;

import java.util.*;
import java.util.regex.*;


public class Networking {
	private static final Pattern namePattern = Pattern.compile("Configuration for interface \"(.+)\"");
	public static List<NetworkInterface> getInterfaces(){
		try{
			String output = Netsh.execute("interface ip show config");
			String[] lines = output.split("[\\r\\n]+");
			
			List<NetworkInterface> interfaces = new ArrayList<NetworkInterface>();
			
			String interfaceName = null;
			String lastKey = null;
			Map<String, String> params = new HashMap<String, String>();
			for(int i = 0; i<lines.length; ++i){
				String line = lines[i];
				if(line.isEmpty())
					continue;
				if(!line.startsWith("    ")){
					if(interfaceName!=null){
						interfaces.add(new NetworkInterface(interfaceName, params));
						params.clear();
						lastKey = null;
					}
					Matcher nameMatcher = namePattern.matcher(line);
					if(nameMatcher.find())
						interfaceName = nameMatcher.group(1);
					else
						continue;
				}
				else{
					String[] paramSplit = line.split(":", 2);
					if(paramSplit.length==2){
						lastKey = paramSplit[0].trim();
						params.put(lastKey, paramSplit[1].trim());
					}
					else if(lastKey!=null){
						String currentValue = params.get(lastKey);
						params.put(lastKey, currentValue+"\n"+line.trim());
					}
					else
						System.err.println("Unkown line: "+line);
				}
			}
			if(interfaceName!=null)
				interfaces.add(new NetworkInterface(interfaceName, params));
			
			return interfaces;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static NetworkInterface getInterface(String name){
		List<NetworkInterface> interfaces = getInterfaces();
		for(NetworkInterface i:interfaces){
			if(i.getInterfaceName().equals(name))
				return i;
		}
		return null;
	}
}

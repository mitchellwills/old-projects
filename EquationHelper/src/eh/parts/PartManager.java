package eh.parts;

import java.util.HashSet;
import java.util.Set;

import eh.OutputMode;

public class PartManager {
	private PartManager(){
	}
	
	private static Set<Part> parts = new HashSet<Part>();
	
	public static void register(Part part){
		parts.add(part);
	}
	
	public static Set<Part> getParts(){
		return parts;
	}
	
	public static void printSupport(){

		System.out.print("\t");
		for(OutputMode mode:OutputMode.values()){
			System.out.print(mode.toString().substring(0, mode.toString().length()<6?mode.toString().length():6)+"\t");
		}
		System.out.println();
		for(Part part:parts){
			System.out.print(part.getName().substring(0, part.getName().length()<6?part.getName().length():6));
			for(OutputMode mode:OutputMode.values()){
				System.out.print("\t"+(part.suppots(mode)?"(x)":"( )"));
			}
			System.out.println();
		}
	}
}

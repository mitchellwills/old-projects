package site.nav;


public class SimpleNavEntry implements NavEntry {
	private final String name;
	private final String target;

	public SimpleNavEntry(String name, String target){
		this.name = name;
		this.target = target;
	}

	@Override
	public String buildNavEntry(String location) {
		if(getTarget().equals(location))
			return "<a class=\"nav-selected\" href=\""+getTarget()+"\">"+getName()+"</a>";
		else
			return "<a href=\""+getTarget()+"\">"+getName()+"</a>";
	}

	public String getTarget() {
		return target;
	}

	public String getName() {
		return name;
	}
}

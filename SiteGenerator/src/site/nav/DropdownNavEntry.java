package site.nav;
import java.util.ArrayList;
import java.util.List;


public class DropdownNavEntry implements NavEntry {
	private final NavEntry parent;
	private final List<NavEntry> children = new ArrayList<NavEntry>();
	public DropdownNavEntry(NavEntry parent){
		this.parent = parent;
	}
	
	public void addPage(NavEntry entry){
		children.add(entry);
	}
	
	public NavEntry getParent(){
		return parent;
	}
	
	
	public List<NavEntry> getChildren() {
		return children;
	}

	@Override
	public String buildNavEntry(String location) {
		String content = "<li class=\"dropdown\">";
		content += parent.buildNavEntry(location);
		content+="<ul>";
		for (NavEntry entry:children) {
			content += "<li>";
			content += entry.buildNavEntry(location);
			content += "</li>\n";
		}
		content+="</ul>";
		content+="</li>\n";
		return content;
	}
}

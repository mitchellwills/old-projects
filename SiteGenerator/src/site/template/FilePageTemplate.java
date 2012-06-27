package site.template;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import site.FileUtil;
import site.nav.DropdownNavEntry;
import site.nav.NavEntry;


public class FilePageTemplate implements PageTemplate{
	private final String templateSource;
	private final List<NavEntry> nav;
	private final Map<String, String> buildValues;
	public FilePageTemplate(Path sourceFile, List<NavEntry> nav, Map<String, String> buildValues) {
		this.nav = nav;
		this.buildValues = buildValues;
		templateSource = FileUtil.readFile(sourceFile);
	}
	
	@Override
	public String generatePage(TemplateContentSource pageEntry){
		String output = templateSource;

		for(Map.Entry<String, String> buildValue:pageEntry.getTemplateContent().entrySet()){
			output = output.replace("!!!"+buildValue.getKey()+"!!!", buildValue.getValue());
		}
		
		for(Map.Entry<String, String> buildValue:buildValues.entrySet()){
			output = output.replace("!!!"+buildValue.getKey()+"!!!", buildValue.getValue());
		}
		
		String navContent = "";
		for (NavEntry entry:nav) {
			if(!(entry instanceof DropdownNavEntry))
				navContent += "<li>";
			navContent += entry.buildNavEntry(pageEntry.getLocation());
			if(!(entry instanceof DropdownNavEntry))
				navContent += "</li>\n";
		}
		output = output.replace("!!!NAVLINKS!!!", navContent);
		return output;
	}
}

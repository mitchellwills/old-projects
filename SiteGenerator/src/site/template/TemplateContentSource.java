package site.template;

import java.util.Map;

public interface TemplateContentSource {
	String getLocation();
	
	Map<String, String> getTemplateContent();

}

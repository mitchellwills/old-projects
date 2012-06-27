package site.page;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import site.FileUtil;
import site.template.TemplateContentSource;
import site.template.TemplateSource;

public class TemplatedPage implements Page, TemplateContentSource{
	private final Path contentSource;
	private final String title;
	private final TemplateSource templateSource;
	private final String location;
	public TemplatedPage(String location, Path contentSource, String title, TemplateSource templateSource){
		this.location = location;
		this.contentSource = contentSource;
		this.title = title;
		this.templateSource = templateSource;
	}
	@Override
	public String getContent() {
		return templateSource.getTemplate().generatePage(this);
	}
	@Override
	public Map<String, String> getTemplateContent() {
		Map<String, String> values = new HashMap<String, String>();
		values.put("TITLE", title);
		values.put("CONTENT", FileUtil.readFile(contentSource));
		return values;
	}
	@Override
	public String getLocation() {
		return location;
	}
	public String toString(){
		return "Templated Page: "+getLocation();
	}

}

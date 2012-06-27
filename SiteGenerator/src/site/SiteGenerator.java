package site;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import site.nav.NavEntry;
import site.nav.SimpleNavEntry;
import site.page.Page;
import site.page.TemplatedPage;
import site.template.FilePageTemplate;
import site.template.PageTemplate;
import site.template.TemplateSource;


public class SiteGenerator implements TemplateSource{
	public static void main(String[] args) throws Exception{
		if(args.length!=3){
			System.out.println("Site Generator, by Mitchell Wills");
			System.out.println("Usage:");
			System.out.println("SiteGenerator.jar sourceFolder buildConfigFile buildFolder");
			return;
		}
		Path sourceFolder = FileSystems.getDefault().getPath(args[0]);
		Path buildConfigFile = FileSystems.getDefault().getPath(args[1]);
		Path buildFolder = FileSystems.getDefault().getPath(args[2]);
		System.out.println("Source Folder: "+sourceFolder);
		System.out.println("Build Config: "+buildConfigFile);
		System.out.println("Build Folder: "+buildFolder);
		
		SiteGenerator generator = new SiteGenerator(sourceFolder);
		generator.loadConfig(buildConfigFile);

		generator.autoBuild(buildFolder);
	}

	private final Path sourceDir;
	private final Map<String, Path> rawFiles = new HashMap<String, Path>();
	private final Set<Page> pages = new HashSet<Page>();
	private final List<NavEntry> nav = new ArrayList<NavEntry>();
	private final Map<String, String> buildValues = new HashMap<String, String>();
	private String templateFile;

	public SiteGenerator(Path sourceDir) {
		this.sourceDir = sourceDir;
	}

	@Override
	public PageTemplate getTemplate() {
		return new FilePageTemplate(sourceDir.resolve(templateFile), nav, buildValues);
	}

	public void loadConfig(Path path){
		try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8); Scanner scanner = new Scanner(reader)){
			while(scanner.hasNextLine()){
				String data = scanner.nextLine();
				if(data.isEmpty() || data.startsWith("//"))
					continue;

				if(data.startsWith("Resources:")){
					String resourcesPath = data.replaceFirst("Resources:", "");
					copyResources(sourceDir.resolve(resourcesPath));
				}
				else if(data.startsWith("Pages:")){
					String resourcesPath = data.replaceFirst("Pages:", "");
					buildPages(sourceDir.resolve(resourcesPath));
				}
				else if(data.startsWith("Nav:")){
					String resourcesPath = data.replaceFirst("Nav:", "");
					buildNav(sourceDir.resolve(resourcesPath));
				}
				else if(data.startsWith("Template:")){
					templateFile = data.replaceFirst("Template:", "");
				}
				else
					throw new SiteBuildException("Badly formatted config line: "+data);
			}
		} catch (IOException e) {
			throw new SiteBuildException(e);
		}
	}

	public void copyResources(Path path){
		try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8); Scanner scanner = new Scanner(reader)){
			while(scanner.hasNextLine()){
				String data = scanner.nextLine();
				if(data.isEmpty() || data.startsWith("//"))
					continue;
				String[] splitData = data.split(",");

				Path sourceFile;
				String targetFile;
				if(splitData.length==1){
					targetFile = data;
					sourceFile = sourceDir.resolve(targetFile);
				}
				else if(splitData.length==2){
					targetFile = splitData[0];
					sourceFile = sourceDir.resolve(splitData[1]);
				}
				else
					throw new SiteBuildException("Badly formatted resource line: "+data);

				rawFiles.put(targetFile, sourceFile);
			}
		} catch (IOException e) {
			throw new SiteBuildException(e);
		}
	}

	public void buildPages(Path path){
		try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8); Scanner scanner = new Scanner(reader)){
			while(scanner.hasNextLine()){
				String data = scanner.nextLine();
				if(data.isEmpty() || data.startsWith("//"))
					continue;
				String[] splitData = data.split(",");

				String target;
				String title;
				Path sourceFile;
				if(splitData.length==2){
					target = splitData[0];
					title = splitData[1];
					sourceFile = sourceDir.resolve(target);
				}
				else if(splitData.length==3){
					target = splitData[0];
					title = splitData[1];
					sourceFile = sourceDir.resolve(splitData[2]);
				}
				else
					throw new SiteBuildException("Badly formatted page line: "+data);

				pages.add(new TemplatedPage(target, sourceFile, title, this));
			}
		} catch (IOException e) {
			throw new SiteBuildException(e);
		}
	}

	public void buildNav(Path path){
		try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8); Scanner scanner = new Scanner(reader)){
			while(scanner.hasNextLine()){
				String data = scanner.nextLine();
				if(data.isEmpty() || data.startsWith("//"))
					continue;
				String[] splitData = data.split(",");
				if(splitData.length==2){
					String title = splitData[0];
					String target = splitData[1];
					nav.add(new SimpleNavEntry(title, target));
				}
				else
					throw new SiteBuildException("Badly formatted nav line: "+data);
			}
		} catch (IOException e) {
			throw new SiteBuildException(e);
		}
	}

	public void autoBuild(final Path buildDir) throws IOException{
		build(buildDir);

		final WatchService watcher = sourceDir.getFileSystem().newWatchService();
		final Map<WatchKey, Path> pathWatchKeys = new HashMap<WatchKey, Path>();
		Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>(){
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException{
				pathWatchKeys.put(dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY), dir);
				return FileVisitResult.CONTINUE;
			}
		});

		while(true){
			WatchKey key;
			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}

			Path file = pathWatchKeys.get(key);
			if(file==null)
				continue;

			for (WatchEvent<?> event: key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (kind == OVERFLOW)
					continue;

				@SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>)event;
				Path filename = ev.context();

				System.out.println(filename+" updated");
			}
			key.reset();
			build(buildDir);
		}
	}


	public void build(Path buildDir) throws IOException{
		if(Files.exists(buildDir))
			FileUtil.deleteAll(buildDir);
		String timeString = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
		System.out.println("Building at "+timeString);
		buildValues.put("BUILD_TIME", timeString);
		buildValues.put("YEAR", new SimpleDateFormat("yyyy").format(new Date()));
		buildValues.put("COPYRIGHT_NAME", "Mitchell E. Wills");
		buildValues.put("HEADER", "Mitchell E. Wills");
		Files.createDirectories(buildDir);
		for(Map.Entry<String, Path> copyEntry:rawFiles.entrySet()){
			Path sourceFile = copyEntry.getValue();
			Path targetFile = buildDir.resolve(copyEntry.getKey());
			FileUtil.copy(sourceFile, targetFile);
			System.out.println("\tCopied: "+sourceFile+" -> "+targetFile);
		}
		for(Page page:pages){
			Path targetFile = buildDir.resolve(page.getLocation());
			FileUtil.writeFile(page.getContent(), targetFile);
			System.out.println("\tCopied: "+page+" -> "+targetFile);
		}
		//TODO ensure that nav links have targets
	}
}

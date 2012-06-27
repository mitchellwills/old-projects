package site;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtil {
	public static void copy(Path source, Path target){
		try{
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
		} catch(IOException e){
			throw new SiteBuildException(e);
		}
	}
	public static String readFile(Path file){
		try{
			StringBuffer stringBuffer = new StringBuffer();
			
			BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
			char[] buffer = new char[500];
			int numRead = 0;
			while((numRead = reader.read(buffer))!=-1)
				stringBuffer.append(String.valueOf(buffer, 0, numRead));
			reader.close();
			return stringBuffer.toString();
		} catch(IOException e){
			throw new SiteBuildException(e);
		}
	}
	public static void writeFile(String content, Path file) {
		try(BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)){
			writer.append(content);
			writer.flush();
		} catch(IOException e){
			throw new SiteBuildException(e);
		}
	}
	public static void deleteAll(Path dir) {
		try {
			Files.walkFileTree(dir, new DeleteDirVisitor());
		} catch (IOException e) {
			throw new SiteBuildException("Error deleting a directory and its contents", e);
		}
	}
	
	private static class DeleteDirVisitor  extends SimpleFileVisitor<Path> {

	    @Override
	    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	        Files.delete(file);
	        return FileVisitResult.CONTINUE;
	    }
	    @Override
	    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
	        if(exc == null){
	            Files.delete(dir);
	            return FileVisitResult.CONTINUE;
	        }
	        throw exc;
	    }
	}

}

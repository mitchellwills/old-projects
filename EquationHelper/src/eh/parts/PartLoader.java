package eh.parts;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lisp.parser.LispParser;
import lisp.parser.LispSyntaxException;
import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.LispValue;

public class PartLoader {
	private static final File partFolder = new File("parts");

	public static void load() {
		File[] partFiles = partFolder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isFile() && file.getName().endsWith(".ehp");
			}
		});
		if (partFiles != null) {
			for (File partFile : partFiles) {
				try {
					System.out.println("Loading: "+partFile.getAbsolutePath());
					loadPart(new FileInputStream(partFile));
				} catch (Exception e) {
					System.err.println("Error loading part from file: "
							+ partFile.getAbsolutePath() + "- " + e.getClass()
							+ ": " + e.getMessage());
				}
			}
		}
	}

	public static final String UNICODE_TYPE = "UNICODE";
	public static final String LISP_TYPE = "LISP";

	public static void loadPart(InputStream partStream) throws Exception {
		Scanner scanner = new Scanner(partStream);
		try {
			String name = scanner.nextLine();
			String keyboardShortcut = scanner.nextLine();
			String type = scanner.nextLine();
			if (type.equals(UNICODE_TYPE)) {
				loadUnicode(name, keyboardShortcut, scanner);
			} else if (type.equals(LISP_TYPE)) {
				loadLisp(name, keyboardShortcut, scanner);
			} else
				throw new Exception("Unsupported Part Type: " + type);
		} finally {
			scanner.close();
		}
	}

	private static void loadUnicode(String name, String keyboardShortcut,
			Scanner scanner) {
		int windowWidth = Integer.parseInt(scanner.nextLine());
		List<Character> charList = new ArrayList<Character>();
		while (scanner.hasNextInt(16)) {
			charList.add((char) scanner.nextInt(16));
		}
		char[] characters = new char[charList.size()];
		for (int i = 0; i < charList.size(); ++i)
			characters[i] = charList.get(i);

		PartManager.register(new UnicodeMapPart(name, keyboardShortcut,
				windowWidth, characters));
	}

	private static void loadLisp(String name, String keyboardShortcut,
			Scanner scanner) {
		StringBuilder builder = new StringBuilder();
		while (scanner.hasNextLine()) {
			builder.append(scanner.nextLine());
			if(scanner.hasNextLine())
				builder.append('\n');
		}

		try {
			LispContext partContext = new LispContext(EHLispContext.getInstance());
			LispParser parser = new LispParser(builder.toString());
			List<LispValue> expressions = parser.getRootValues();
			for (LispValue e : expressions)
				e.eval(partContext);

			PartManager.register(new LispPart(name, keyboardShortcut,
					partContext));

		} catch (LispRuntimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LispSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

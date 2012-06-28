package util.input;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Reader;

public class SystemClipboard {
	private static final Clipboard clipboard = Toolkit.getDefaultToolkit()
			.getSystemClipboard();

	public static String getText() {
		try {
			Reader reader = DataFlavor.getTextPlainUnicodeFlavor()
					.getReaderForText(clipboard.getContents(null));
			char[] arr = new char[128];
			StringBuffer buf = new StringBuffer();
			int numChars;
			while ((numChars = reader.read(arr, 0, arr.length)) > 0) {
				buf.append(arr, 0, numChars);
			}
			return buf.toString();

		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Image getImage(){
		try {
			return (Image)clipboard.getContents(null).getTransferData(DataFlavor.imageFlavor);
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

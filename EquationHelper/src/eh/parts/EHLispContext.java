package eh.parts;

import javax.swing.JOptionPane;

import lisp.functions.JavaFunction;
import lisp.runtime.LispContext;
import lisp.runtime.LispJava;
import lisp.runtime.LispRuntimeException;
import util.input.keyboard.KeySequenceBuilder;
import util.input.keyboard.Keyboard;
import util.input.keyboard.WinVK;
import eh.OutputMode;

public class EHLispContext extends LispContext {
	private static EHLispContext INSTANCE = new EHLispContext();
	public static EHLispContext getInstance(){
		return INSTANCE;
	}
	
	
	private EHLispContext(){
		try {
			define("showMessage", new JavaFunction(null, EHLispContext.class, "showMessage", new Class<?>[]{String.class, String.class}));

			
			define("newDialog", new JavaFunction(null, EHLispContext.class, "newDialog", new Class<?>[]{LispPart.class, String.class, String.class}));
			define("showDialog", new JavaFunction(null, EHLispContext.class, "showDialog", new Class<?>[]{LispPartDialog.class}));
			define("addTextField", new JavaFunction(null, EHLispContext.class, "addTextField", new Class<?>[]{LispPartDialog.class, String.class, String.class, String.class}));
			define("addComboBoxField", new JavaFunction(null, EHLispContext.class, "addComboBoxField", new Class<?>[]{LispPartDialog.class, String.class, String.class, String.class}));
			define("get", new JavaFunction(null, EHLispContext.class, "get", new Class<?>[]{LispPartDialog.class, String.class}));
			

			define("newKeySequence", new JavaFunction(null, EHLispContext.class, "newKeySequence", new Class<?>[]{}));
			define("appendType", new JavaFunction(null, EHLispContext.class, "appendType", new Class<?>[]{KeySequenceBuilder.class, String.class}));
			define("appendTypeVK", new JavaFunction(null, EHLispContext.class, "appendTypeVK", new Class<?>[]{KeySequenceBuilder.class, WinVK.class}));
			define("appendPressVK", new JavaFunction(null, EHLispContext.class, "appendPressVK", new Class<?>[]{KeySequenceBuilder.class, WinVK.class}));
			define("appendReleaseVK", new JavaFunction(null, EHLispContext.class, "appendReleaseVK", new Class<?>[]{KeySequenceBuilder.class, WinVK.class}));
			define("executeSequence", new JavaFunction(null, EHLispContext.class, "executeSequence", new Class<?>[]{KeySequenceBuilder.class}));

			define("sendUnicodeEvent", new JavaFunction(null, EHLispContext.class, "sendUnicodeEvent", new Class<?>[]{Integer.TYPE}));
			for(WinVK vk:WinVK.values())
				define("WinVK_"+vk.name(), LispJava.toLispJavaValue(vk));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LispRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void showMessage(String title, String message){
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
	}
	
	
	
	//Dialogs
	public static LispPartDialog newDialog(LispPart part, String title, String mode){
		return new LispPartDialog(part, title, OutputMode.valueOf(mode));
	}
	public static void showDialog(LispPartDialog dialog){
		dialog.setVisible(true);
	}
	public static void addTextField(LispPartDialog dialog, String label, String name, String defaultValue){
		dialog.addTextField(label, name, defaultValue);
	}
	public static void addComboBoxField(LispPartDialog dialog, String label, String name, String defaultValue){
		dialog.addComboBoxField(label, name, defaultValue);
	}
	public static String get(LispPartDialog dialog, String name){
		return dialog.get(name);
	}
	
	
	//keyboard outputs
	public static KeySequenceBuilder newKeySequence(){
		return new KeySequenceBuilder();
	}
	public static void appendType(KeySequenceBuilder sequence, String text){
		sequence.type(text);
	}
	public static void appendTypeVK(KeySequenceBuilder sequence, WinVK vk){
		sequence.typeVK(vk);
	}
	public static void appendPressVK(KeySequenceBuilder sequence, WinVK vk){
		sequence.pressVK(vk);
	}
	public static void appendReleaseVK(KeySequenceBuilder sequence, WinVK vk){
		sequence.releaseVK(vk);
	}
	public static void executeSequence(KeySequenceBuilder sequence){
		sequence.build().execute();
	}
	
	public static void sendUnicodeEvent(int character){
		Keyboard.sendUnicodeEvent((char)character);
	}
}

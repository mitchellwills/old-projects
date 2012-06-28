package options;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import options.field.ValueField;
import options.gui.OptionsWindow;

public class OptionProvider {
	private OptionProvider() {
	}

	private static boolean initialized = false;
	private static String settingsFile = "options.ini";
	
	private static List<OptionSet> optionSets = new ArrayList<OptionSet>();
	public static void registerSet(OptionSet optionSet) {
		if(initialized)
			throw new RuntimeException("Cannot register option set after OptionProvider is initialized");
		
		optionSets.add(optionSet);
		getOptionsWindow().optionSetAdded(optionSet);
	}
	public static void init(String sF){
		if(initialized)
			throw new RuntimeException("Cannot reinitialize OptionProvider");
		settingsFile = sF;
		initialized = true;
		loadFromFile();
	}

	private static Map<String, ValueField<?>> values = new HashMap<String, ValueField<?>>();

	static <T> ValueField<T> put(String name, Class<T> type, T initialValue) {
		if(initialized)
			throw new RuntimeException("Cannot add option after OptionProvider is initialized");
		
		if (values.containsKey(name))
			throw new RuntimeException("Option Key: " + name
					+ " already exists");// TODO throw better exception
		ValueField<T> field = TypeManager.getField(name, initialValue, type);
		values.put(name, field);
		return field;
	}

	public static ValueField<?> getField(String name) {
		return values.get(name);
	}

	public static boolean getBoolean(String name) {
		return getField(name).getBoolean();
	}

	public static String getString(String name) {
		return getField(name).getString();
	}

	public static int getInt(String name) {
		return getField(name).getInt();
	}

	public static void save() {
		for (OptionSet set : optionSets)
			set.save();
		saveToFile();
	}

	public static void revert() {
		loadFromFile();
		for (OptionSet set : optionSets)
			set.load();
	}

	public static void loadFromFile() {
		if(!initialized)
			throw new RuntimeException("Cannot load until OptionProvider is initialized");
		
		Properties properties = new Properties();
		FileInputStream fis = null;
			try {
				properties.loadFromXML(fis = new FileInputStream(settingsFile));
			} catch (InvalidPropertiesFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.err.println("File not found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				if(fis!=null)
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			for(Entry<Object, Object> entry:properties.entrySet()){
				ValueField<?> field = values.get(entry.getKey());
				if(field!=null)
					field.set((String)entry.getValue());
			}
	}

	public static void saveToFile() {
		if(!initialized)
			throw new RuntimeException("Cannot save until OptionProvider is initialized");
		
		Properties properties = new Properties();
		for(ValueField<?> field:values.values())
			if(field.getString()!=null)
				properties.setProperty(field.getName(), field.getString());
		FileOutputStream fos = null;
		try {
			properties.storeToXML(fos = new FileOutputStream(settingsFile), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private static Set<OptionListener> listeners = new HashSet<OptionListener>();

	public static void addOptionListener(OptionListener listener) {
		listeners.add(listener);
	}

	public static void removeOptionListener(OptionListener listener) {
		listeners.remove(listener);
	}

	public static void fireOptionEvent(String name, Object newValue) {
		for (OptionListener listener : listeners)
			listener.optionUpdated(name, newValue);
	}

	private static OptionsWindow optionsWindow = new OptionsWindow();
	public static OptionsWindow getOptionsWindow() {
		return optionsWindow;
	}

}

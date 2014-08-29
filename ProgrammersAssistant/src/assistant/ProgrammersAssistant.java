package assistant;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import assistant.ui.AssistantWindow;

public class ProgrammersAssistant {
	public static void main(String[] args) throws InvocationTargetException, InterruptedException{
		Application.launch(AssistantWindow.class, args);
	}

	public static void exit() {
		System.exit(0);
	}
}

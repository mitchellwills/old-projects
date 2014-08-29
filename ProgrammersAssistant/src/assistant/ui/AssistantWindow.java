package assistant.ui;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class AssistantWindow extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Programmer's Assistant");
		Parent root = FXMLLoader.load(getClass().getResource("AssistantWindow.fxml"));
		Scene scene = new Scene(root, 800, 700);
		stage.setScene(scene);
		
		stage.show();
	}
	
	

}

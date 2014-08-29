package assistant.ui;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class AssistantWindowMenuController extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Programmer's Assistant");
		Parent root = FXMLLoader.load(getClass().getResource("AssistantWindow.fxml"));
		Scene scene = new Scene(root, 700, 700);
		stage.setScene(scene);
		
		stage.show();
	}
	
	

}

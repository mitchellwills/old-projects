package assistant.regex;

import java.io.*;
import java.net.*;
import java.util.*;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.stage.*;
import assistant.*;

public class RegularExpressionPanel implements Initializable, RegularExpressionInterface {
	@FXML private TextArea matcherRegexArea;
	@FXML private TextArea replaceArea;
	@FXML private Text errorField;
	@FXML private TextArea sourceTextArea;
	@FXML private TextArea outputTextArea;
	@FXML private SplitPane splitPane;

	@FXML private CheckBox multilinePatternCheckbox;
	@FXML private CheckBox caseInsensitivePatternCheckbox;
	@FXML private CheckBox dotAllPatternCheckbox;

	@FXML private ListView<PredefinedRegex> predefinedList;

	private RegularExpressionController controller;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = new RegularExpressionController(this);
		matcherRegexArea.textProperty().addListener(new UpdateableChangeAdapter<String>(controller));
		replaceArea.textProperty().addListener(new UpdateableChangeAdapter<String>(controller));
		sourceTextArea.textProperty().addListener(new UpdateableChangeAdapter<String>(controller));

		multilinePatternCheckbox.selectedProperty().addListener(new UpdateableChangeAdapter<Boolean>(controller));
		caseInsensitivePatternCheckbox.selectedProperty().addListener(new UpdateableChangeAdapter<Boolean>(controller));
		dotAllPatternCheckbox.selectedProperty().addListener(new UpdateableChangeAdapter<Boolean>(controller));

		splitPane.setDividerPosition(0, 0.35);

		predefinedList.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<PredefinedRegex>(){
			@Override
			public void onChanged(ListChangeListener.Change<? extends PredefinedRegex> change) {
				PredefinedRegex selected = predefinedList.getSelectionModel().getSelectedItem();
				if(selected!=null){
					matcherRegexArea.setText(selected.getMatchPattern());
					replaceArea.setText(selected.getReplacePattern());
					multilinePatternCheckbox.setSelected(selected.isMultiline());
					caseInsensitivePatternCheckbox.setSelected(selected.isCaseInsensitive());
					dotAllPatternCheckbox.setSelected(selected.isDotAll());
					
					predefinedList.getSelectionModel().clearSelection();
				}
			}

		});

		predefinedList.getItems().add(new PredefinedRegex("None", "", "", false, false, false));
		predefinedList.getItems().add(new PredefinedRegex("C define to Java constants", "#define (\\w+) (.+)$", "public static final int $1 = $2;", true, false, false));
	}

	@FXML public void copyOutput(){
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(outputTextArea.getText());
		clipboard.setContent(content);
	}

	@FXML public void moveOutputToSource(){
		sourceTextArea.setText(outputTextArea.getText());
	}

	@FXML public void loadInputFromFile(){
		FileChooser chooser = new FileChooser();
		File selectedFile = chooser.showOpenDialog(null);
		StringBuffer fileContent = new StringBuffer();
		try (FileReader fileReader = new FileReader(selectedFile)){
			char[] buf = new char[1024];
			int numRead;
			while( (numRead = fileReader.read(buf)) != -1 ){
				fileContent.append(buf, 0, numRead);
			}
			sourceTextArea.setText(fileContent.toString());
		} catch(IOException e){
			setError("Error loading from file: "+e.getMessage());
		}
	}

	@FXML public void saveOutputToFile(){
		FileChooser chooser = new FileChooser();
		File selectedFile = chooser.showSaveDialog(null);
		try ( FileWriter fileWriter = new FileWriter(selectedFile);
				StringReader outputReader = new StringReader(outputTextArea.getText()) ){
			char[] buf = new char[1024];
			int numRead;
			while( (numRead = outputReader.read(buf)) != -1 ){
				fileWriter.write(buf, 0, numRead);
			}
		} catch(IOException e){
			setError("Error saving to file: "+e.getMessage());
		}
	}

	@Override
	public String getMatchPattern() {
		return matcherRegexArea.getText();
	}

	@Override
	public String getReplacePattern() {
		return replaceArea.getText();
	}

	@Override
	public String getInput() {
		return sourceTextArea.getText();
	}

	@Override
	public void setOutput(String output) {
		outputTextArea.setText(output);
	}

	@Override
	public void setError(String message) {
		errorField.setText(message);
	}

	@Override
	public boolean multilinePattern() {
		return multilinePatternCheckbox.isSelected();
	}

	@Override
	public boolean caseInsensitivePattern() {
		return caseInsensitivePatternCheckbox.isSelected();
	}

	@Override
	public boolean dotAllPattern() {
		return dotAllPatternCheckbox.isSelected();
	}

}

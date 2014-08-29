package assistant.textCase;

import java.net.*;
import java.util.*;
import java.util.regex.*;

import javafx.fxml.*;
import javafx.scene.control.*;
import assistant.*;

public class CasePanel implements Initializable, Updateable {
	@FXML private TextArea sourceTextArea;
	@FXML private TextArea outputTextArea;

	@FXML private ToggleGroup caseSelectGroup;
	@FXML private ToggleButton upperCaseToggle;
	@FXML private ToggleButton lowerCaseToggle;
	@FXML private ToggleButton camelCaseToggle;
	@FXML private ToggleButton modCamelCaseToggle;
	@FXML private ToggleButton constantNameToggle;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sourceTextArea.textProperty().addListener(new UpdateableChangeAdapter<String>(this));
		caseSelectGroup.selectedToggleProperty().addListener(new UpdateableChangeAdapter<Toggle>(this));
	}
	
	public void update(){
		Toggle selected = caseSelectGroup.getSelectedToggle();
		if(selected==upperCaseToggle)
			toUpperCase();
		if(selected==lowerCaseToggle)
			toLowerCase();
		if(selected==camelCaseToggle)
			toCamelCase(true);
		if(selected==modCamelCaseToggle)
			toCamelCase(false);
		if(selected==constantNameToggle)
			toConstantName();
	}

	private static final Pattern WORD_PATTERN = Pattern.compile("\\S+");
	private static final Pattern LINE_PATTERN = Pattern.compile("^.+$", Pattern.MULTILINE);
	public void toUpperCase(){
		Matcher matcher = WORD_PATTERN.matcher(sourceTextArea.getText());
		StringBuffer output = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(output, matcher.group().toUpperCase());
		}
		matcher.appendTail(output);
		outputTextArea.setText(output.toString());
	}
	public void toLowerCase(){
		Matcher matcher = WORD_PATTERN.matcher(sourceTextArea.getText());
		StringBuffer output = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(output, matcher.group().toLowerCase());
		}
		matcher.appendTail(output);
		outputTextArea.setText(output.toString());
	}

	public void toCamelCase(boolean capFirstLetter){
		Matcher matcher = LINE_PATTERN.matcher(sourceTextArea.getText());
		StringBuffer output = new StringBuffer();
		while(matcher.find()){
			Matcher lineMatcher = WORD_PATTERN.matcher(matcher.group());
			for(int w = 0; lineMatcher.find(); ++w){
				if(lineMatcher.group().length()>0){
					if(!capFirstLetter && w==0)
						output.append(lineMatcher.group().toLowerCase());
					else{
						String capLetter = lineMatcher.group().substring(0, 1).toUpperCase();
						String nonCaps = lineMatcher.group().substring(1).toLowerCase();
						output.append(capLetter + nonCaps);
					}
				}
			}
			output.append("\n");
		}
		outputTextArea.setText(output.toString());
	}
	public void toConstantName(){
		Matcher matcher = LINE_PATTERN.matcher(sourceTextArea.getText());
		StringBuffer output = new StringBuffer();
		while(matcher.find()){
			Matcher lineMatcher = WORD_PATTERN.matcher(matcher.group());
			for(int w = 0; lineMatcher.find(); ++w){
				if(w!=0)
					output.append("_");
				output.append(lineMatcher.group().toUpperCase());
			}
			output.append("\n");
		}
		outputTextArea.setText(output.toString());
	}
}

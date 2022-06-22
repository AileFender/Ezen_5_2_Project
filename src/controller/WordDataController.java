package controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Word;


public class WordDataController {
	
	@FXML
	private TextField koreanField;
	@FXML
	private TextField englishField;
	
	private Stage dialogStage;
	private Word word;
	private int returnValue = 0;
	
	@FXML
	private void initialize() {
	}
	
	public void setDialogStage (Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setWord(Word word) {
		this.word = word;
		koreanField.setText(word.getKorean());
		englishField.setText(word.getEnglish());
	}
	

	
	public int getReturnValue() {
		return returnValue;
	}
	
	@FXML
	private void confirmAction() {
		if (valid()) {
			word.setKorean(koreanField.getText());
			word.setEnglish(englishField.getText());
			returnValue = 1;
			dialogStage.close();
		}
	}
	
	@FXML
	private void cancelAction() {
		dialogStage.close();
	}
	
	private boolean valid() {
		String errorMessage = "";
		if (koreanField.getText() == null || koreanField.getText().equals("")) {
			errorMessage += "한글을 입력하세요.\n";
		}
		if (englishField.getText() == null || englishField.getText().equals("")) {
			errorMessage += "영어를 입력하세요.\n";
		}
		if(errorMessage.equals("")) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("오류 메시지");
			alert.setHeaderText("값을 제대로 입력해주세요.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
	
	
}



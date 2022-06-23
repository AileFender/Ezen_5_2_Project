package controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
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
	
	// dialogStage 무대 를 설정함.
	public void setDialogStage (Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	//하나의 단어를 저장
	public void setWord(Word word) {
		this.word = word;
		koreanField.setText(word.getKorean());
		englishField.setText(word.getEnglish());
	}
	

	// 이벤트를 마치고 값을 반환하는 함수
	public int getReturnValue() {
		return returnValue;
	}
	
	//현재 사용자가 입력한 값이 정확한 지 파악하고 정확한 경우 반환값에 1을 넣어준뒤 dialog 닫음
	@FXML
	private void confirmAction() {
		if (valid()) {
			word.setKorean(koreanField.getText());
			word.setEnglish(englishField.getText());
			returnValue = 1;
			dialogStage.close();
		}
	}
	
	//dialog 창 닫음
	@FXML
	private void cancelAction() {
		dialogStage.close();
	}
	
	//입력 값들을 확인한 뒤에 비어있으면 오류 메세지를 띄우고 비어있지 않으면 True 값 반환
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



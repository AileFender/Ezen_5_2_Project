package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Word;
import model.WordDAO;

public class ComSearchController {
	private Stage dialogStage;
	
	@FXML private TextField searchField;
	
	
	
	@FXML 
	public void searchAction2() {
		WordDAO WordDAO = new WordDAO();
		String info="";
		if(valid()) {
			info = WordDAO.searchInfo(searchField.getText());
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("검색 결과");
		alert.setHeaderText("<검색 정보>");
		alert.setContentText(info);
		alert.showAndWait();
		dialogStage.close();
	}
	
	@FXML 
	public void cancelAction() {
		dialogStage.close();
	}
	
	private boolean valid() {
		String errorMessge = "";
		if( searchField.getText() == null || searchField.getText().equals("")) {
			errorMessge += "이름을 적으세요. \n";
		}
		if( errorMessge.equals("")) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("오류 메세지");
			alert.setHeaderText("문자를 제대로 입력하세요.");
			alert.setContentText(errorMessge);
			alert.showAndWait();
			return false;
		}
	}
	
	
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}

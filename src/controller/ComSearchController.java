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
	
	
	//DB와 연동하여 검색한 정보창 출력하는 컨트롤러
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
	
	//취소버튼 입력시 행동할 메소드
	@FXML 
	public void cancelAction() {
		dialogStage.close();
	}
	
	// 검색창에서 아무것도 입력하지 않았는지 확인하여 입력하지 않았을 시 error 메세지 출력하는 alert창 발생
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
	
	
	// dialog의 stage를 설정함.
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}

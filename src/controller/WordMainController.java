package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Word;

public class WordMainController {

	@FXML
	private TableView<Word> wordTable;
	@FXML
	private TableColumn<Word, String> korean;
	@FXML
	private TableColumn<Word, String> english;
	
	private Main main;
	
	@FXML
	private void initialize() {
		korean.setCellValueFactory(cellData -> cellData.getValue().getKoreanProperty());
		english.setCellValueFactory(new PropertyValueFactory<Word, String>("english"));
	}
	
	public void setMain(Main main) {
		this.main = main;
		wordTable.setItems(main.getWordList());
	}
	
	public WordMainController() {
	}
	
	@FXML
	private void addAction() {
		Word word = new Word("" , "");
		int returnValue = main.setWordDataView(word);
		if (returnValue ==1) {
			main.getWordList().add(word);
		}
	}
	
	@FXML
	private void editAction() {
		Word word = wordTable.getSelectionModel().getSelectedItem();
		if (word != null) {
			main.setWordDataView(word);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("오류 메시지");
			alert.setHeaderText("선택 오류가 발생했습니다.");
			alert.setContentText("수정 단어를 선택해주세요.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void deleteAction() {
		int selectedIndex = wordTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >=0) {
			wordTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("오류 메시지");
			alert.setHeaderText("선택 오류가 발생했습니다.");
			alert.setContentText("삭제할 단어를 선택해주세요.");
			alert.showAndWait();
		}
	}	
	
	@FXML
	private void searchAction() {
		main.setSearchView();
	}
}
	


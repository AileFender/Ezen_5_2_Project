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
	// 단어장 테이블의 두 열을 초기화 한다.
	private void initialize() {
		korean.setCellValueFactory(cellData -> cellData.getValue().getKoreanProperty());
		english.setCellValueFactory(new PropertyValueFactory<Word, String>("english"));
	}
	
	// 단어장 화면에 단어 목록을 보여주는 생성자
	public void setMain(Main main) {
		this.main = main;
		wordTable.setItems(main.getWordList());
	}
	
	// 단어장 메인 컨트롤러 생성자
	public WordMainController() {
	}
	
	@FXML
	// 추가 버튼 눌렀을 시 테이블 초기화 해주는 메소드
	private void addAction() {
		Word word = new Word("" , "");
		int returnValue = main.setWordDataView(word);
		if (returnValue ==1) {
			main.getWordList().add(word);
		}
	}
	
	@FXML
	// 단어를 고르고 수정버튼을 눌렀을 시 수정창을 출력하고 아무것도 고르지않고 수정버튼을 누르면 error alert 창 발생
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
	// 단어를 고르고 삭제버튼을 눌렀을 시 해당 단어를 삭제하고 아무것도 고르지않고 삭제버튼ㅇ을 누르면 error alert 창 발생
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
	// 검색 버튼을 눌렀을 시 메인에 검색창 출력하는 메소드
	private void searchAction() {
		main.setSearchView();
	}
}
	


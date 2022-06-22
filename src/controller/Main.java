package controller;
	
import java.io.IOException;
import java.util.Observable;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Word;
import model.WordDAO;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	public static ObservableList<Word> wordList = FXCollections.observableArrayList();

	public Main() {
	}

	public ObservableList<Word> getWordList() {
	  return wordList;
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("영한 사전 프로그램");
		setRootLayout();
		setWordMainView();
	}
	
	public void setRootLayout() {
		try {
			// FXML 파일을 이용해 루트 레이아웃을 가져옵니다.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// 루트 레이아웃을 퐘하는 Scene을 보여줍니다.
			Scene scene = new Scene(rootLayout,340,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setWordMainView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/WordMainView.fxml"));
			AnchorPane wordMainView = (AnchorPane) loader.load();
			rootLayout.setCenter(wordMainView);
			
			WordMainController controller = loader.getController();
			controller.setMain(this);
			
			WordDAO wordDAO = new WordDAO();
			ObservableList<Word> tempList = wordDAO.getWordList();
			for(int i=0 ; i<tempList.size();i++) {
				wordList.add(tempList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 현재의 메인 스테이지를 반환합니다. */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public int setWordDataView(Word word) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/WordDataView.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("단어장 관리");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
		
	
			
			WordDataController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setWord(word);
			Scene scene = new Scene(page ,610 ,210);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);
			dialogStage.showAndWait();
			
			return controller.getReturnValue();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void setSearchView() {
		try {
			FXMLLoader loader =  new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/SearchDataView.fxml"));
			AnchorPane page =  (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("한글 검색");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			
			ComSearchController controller = (ComSearchController) loader.getController();
			controller.setDialogStage(dialogStage);
			
			
			dialogStage.showAndWait();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	
	@FXML
	private void saveAction() {
		WordDAO wordDAO = new WordDAO();
		int result = wordDAO.saveWordList(wordList);
		if(result == 1) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(primaryStage);
			alert.setTitle("성공 메시지");
			alert.setHeaderText("성공적으로 수행했습니다.");
			alert.setContentText("데이터베이스에 성공적으로 접근했습니다.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(primaryStage);
			alert.setTitle("오류 메시지");
			alert.setHeaderText("오류가 발생했습니다.");
			alert.setContentText("데이터베이스 오류가 발생했습니다.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void exitAction() {
		System.exit(1);
	}
	
	@FXML
	private void aboutAction() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("알아보기");
		alert.setHeaderText("<프로그램 정보>");
		alert.setContentText("프로그램 버전 : 0.1 Ver\n"
							+ "프로그램 개발자: 김봉재 , 박성호 , 이희수 , 송민준\n"
							+ "프로그램 설명: 한영사전 프로그램입니다.");
		alert.showAndWait();
	}
	
	@FXML
	private void barChartAction() {
		  try {
			    FXMLLoader loader = new FXMLLoader();
			    loader.setLocation(Main.class.getResource("../view/BarChartView.fxml")); 
			    AnchorPane pane = (AnchorPane) loader.load();
			    Stage stage = new Stage();
			    stage.setTitle("막대 차트");
			    stage.initModality(Modality.WINDOW_MODAL);
			    stage.initOwner(primaryStage);
			    Scene scene = new Scene(pane);
			    stage.setScene(scene);
			    BarChartController controller = loader.getController();
			    controller.setWordList(wordList);
			    stage.show();
			  } catch (Exception e) {
			    e.printStackTrace();
			  }
	}
	
	@FXML
	private void searchAction() {
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}



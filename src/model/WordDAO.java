package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WordDAO {

	private Connection conn;
	private ResultSet rs;
	
	// 생성자 부분에서 데이터베이스에 접근하여 세션을 얻어옴
	public WordDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bg","bg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 데이터베이스에 접속해서 단어장에서 검색한 항목 가져오는 함수 
	public String searchInfo(String korean) {
		String SQL = "SELECT * FROM WORD";
		String info="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			int cnt=0;
			while(rs.next()) {
				if(rs.getString("korean").equals(korean)) {
					info = "한글 : " +rs.getString("korean")  +" \n영어 : "+ rs.getString("english");
					cnt++;
				}
			}
			if(cnt==0) {
				info = "그런 단어는 없습니다.";
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return info;
	}
	
	
	// 데이터베이스에 접속해서 단어장 정보를 삭제하는 함수
	public int deleteWordList() {
		String SQL = "DELETE FROM WORD";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	// 현재 모든 단어장 리스트의 정보를 데이터베이스에 삽입하는 메소드
	public int insertWordList(ObservableList<Word> wordList) {
		String SQL = "INSERT INTO WORD VALUES (?,?)";
		try {
			int i;
			for(i =0; i < wordList.size(); i++) {
				Word word = wordList.get(i);
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, word.getKorean());
				pstmt.setString(2, word.getEnglish());
				pstmt.executeUpdate();
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	// 데이터베이스에 존재하는 모든 단어장 정보를 가져와서 리스트 형태로 반환하는 함수
	public ObservableList<Word> getWordList() {
		String SQL = "SELECT * FROM WORD";
		ObservableList<Word> wordList = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Word word = new Word(rs.getString("korean"), rs.getString("english"));
				wordList.add(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wordList;
	}
	
	// 위에서 선언한 함수를 이용해서 데이터베이스에 저장하는 함수
	public int saveWordList(ObservableList<Word> wordList) {
		if(deleteWordList() == -1) {
			return -1;
		}
		if(insertWordList(wordList) == -1) {
			return -1;
		}
		return 1;
	}
	
	// 한글로 검색한 항목을 찾아서 반환하는 함수
	public ObservableList<Word> searchWordList(){
		String SQL = "SELECT * FROM WORD WHERE KOREAN LIKE ('?')";
		return null;
	}
}

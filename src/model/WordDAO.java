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
	
	public WordDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bg","bg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
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
	
	public int saveWordList(ObservableList<Word> wordList) {
		if(deleteWordList() == -1) {
			return -1;
		}
		if(insertWordList(wordList) == -1) {
			return -1;
		}
		return 1;
	}
	
	public ObservableList<Word> searchWordList(){
		String SQL = "SELECT * FROM WORD WHERE KOREAN LIKE ('?')";
		return null;
	}
}

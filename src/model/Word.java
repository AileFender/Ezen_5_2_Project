package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Word {

	private StringProperty korean;
	private StringProperty english;


	
	public Word(String korean, String english) {
		this.korean = new SimpleStringProperty(korean);
		this.english = new SimpleStringProperty(english);
	}
	
	
	public String getKorean() {
		return korean.get();
	}
	
	public void setKorean(String korean) {
		this.korean.set(korean);
	}
		
	public StringProperty getKoreanProperty() {
		return korean;
	}

	public String getEnglish() {
		return english.get();
	}
	
	public void setEnglish(String english) {
		this.english.set(english);
	}
	
	public StringProperty getEnglishProperty() {
		return english;
	}

}

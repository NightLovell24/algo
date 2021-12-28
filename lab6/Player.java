package my.nalab6;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Player {

	private SimpleStringProperty name;
	private SimpleIntegerProperty oneP;
	private SimpleIntegerProperty twoP;
	private SimpleIntegerProperty threeP;
	private SimpleIntegerProperty fourP;
	private SimpleIntegerProperty fiveP;
	private SimpleIntegerProperty sixP;
	private SimpleIntegerProperty street;
	private SimpleIntegerProperty fullHouse;
	private SimpleIntegerProperty four;
	private SimpleIntegerProperty general;

	Player(String name) {
		this.name = new SimpleStringProperty(name);
		this.oneP = new SimpleIntegerProperty(0);
		this.twoP = new SimpleIntegerProperty(0);
		this.threeP = new SimpleIntegerProperty(0);
		this.fourP = new SimpleIntegerProperty(0);
		this.fiveP = new SimpleIntegerProperty(0);
		this.sixP = new SimpleIntegerProperty(0);
		this.street = new SimpleIntegerProperty(0);
		this.fullHouse = new SimpleIntegerProperty(0);
		this.four = new SimpleIntegerProperty(0);
		this.general = new SimpleIntegerProperty(0);

	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public int getOneP() {
		return oneP.get();
	}

	public void setOneP(int oneP) {
		this.oneP = new SimpleIntegerProperty(oneP);
	}

	public int getTwoP() {
		return twoP.get();
	}

	public void setTwoP(int twoP) {
		this.twoP = new SimpleIntegerProperty(twoP);
	}

	public int getThreeP() {
		return threeP.get();
	}

	public void setThreeP(int threeP) {
		this.threeP = new SimpleIntegerProperty(threeP);
	}

	public int getFourP() {
		return fourP.get();
	}

	public void setFourP(int fourP) {
		this.fourP = new SimpleIntegerProperty(fourP);
	}

	public int getFiveP() {
		return fiveP.get();
	}

	public void setFiveP(int fiveP) {
		this.fiveP = new SimpleIntegerProperty(fiveP);
	}
	public int getSixP() {
		return sixP.get();
	}

	public void setSixP(int sixP) {
		this.sixP = new SimpleIntegerProperty(sixP);
	}
	public int getStreet() {
		return street.get();
	}

	public void setStreet(int street) {
		this.street = new SimpleIntegerProperty(street);
	}
	public int getFullHouse() {
		return fullHouse.get();
	}
	public void setFullHouse(int fullHouse) {
		this.fullHouse = new SimpleIntegerProperty(fullHouse);
	}

	public void setFour(int four) {
		this.four = new SimpleIntegerProperty(four);
	}
	public int getFour() {
		return four.get();
	}
	
	public void setGeneral(int general) {
		this.general = new SimpleIntegerProperty(general);
	}
	public int getGeneral() {
		return general.get();
	}

	
	

}

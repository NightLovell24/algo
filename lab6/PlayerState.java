package my.nalab6;

public class PlayerState {

	String name;
	boolean isFour = false;
	boolean isGeneral = false;
	boolean isStreet = false;
	boolean isFullHouse = false;

	public PlayerState(String name) {
		this.name = name;
	}
}

package my.nalab2;

public class Element {

	int key;
	String information;

	public Element(int key, String information) {
		super();
		this.key = key;
		this.information = information;
	}

	@Override
	public String toString() {
		return "{" + key + ";" + information + "}";
	}

}

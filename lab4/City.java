package my.nalab4;

public class City {

	public static final int[][] graph = new int[Main.numCities][Main.numCities]; // A -> связанные с ним; A | остальные связи 
	private final int num;
	
	public City(int num) {
		this.num = num;
	}
	public double distanceFrom(City city) {
		return graph[num][city.getNum()];
	}
	public int getNum() {
		return num;
	}
	
}

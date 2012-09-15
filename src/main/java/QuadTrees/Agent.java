package QuadTrees;

/**
 * Author:      Grant Kurtz
 */
public class Agent {

	private String name;
	private int[] coords;

	public Agent(String name, int[] coords) {
		this.name = name;
		this.coords = coords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}
}

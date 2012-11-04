package QuadTrees;

/**
 * Author:      Grant Kurtz
 */
public class Agent {

	private String name;
	private int[] coords;
	private Map quadrant;

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

	public Map getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(Map quadrant) {
		this.quadrant = quadrant;
	}
}

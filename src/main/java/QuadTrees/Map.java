package QuadTrees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Uses a QuadTree for efficient partitioning of Agents.
 * <p/>
 * Author:      Grant Kurtz
 */
public class Map {

	/**
	 * The root node of our map.
	 */
	private HashMap<Integer, Map> map;

	private static Map theRoot;

	private Map parent;

	private int quadNum;

	/**
	 * All the agents contained within this map.
	 */
	private Set<Agent> agents;

	/**
	 * The maximum number of partitions this map is allowed to have.
	 */
	private final static int MAX_PARTS = 4;

	/**
	 * The boundaries of this quadrant.
	 */
	private int[][] boundary;

	/**
	 * Creates a new map.
	 *
	 * @param boundary The boundary coordinates of this quadrant.
	 * @param part     If true, then this quadrant will be sub-divided (while
	 *                 its child quadrants will not be).  If false, this quadrant
	 *                 is not sub-divided.
	 */
	public Map(int[][] boundary, int part, Map parent, int quadNum) {
		if (theRoot == null)
			theRoot = this;
		map = new HashMap<Integer, Map>();
		agents = new HashSet<Agent>();
		this.boundary = boundary;
		this.parent = parent;
		this.quadNum = quadNum;
		if (part > 0) {
			partition(--part);
		}
	}

	/**
	 * Either stores the agent at this quadrant, or delegates to a sub-quadrant.
	 *
	 * @param a The Agent to store in our Quadrant.
	 * @return The quadrant the Agent was stored in.
	 */
	public Map trackAgent(Agent a) {
		int[] loc = a.getCoords();
		if (map.isEmpty()) {
			agents.add(a);
			a.setQuadrant(this);
		} else {
			if (loc[0] <= getMiddleXBoundary()) {
				if (loc[1] <= getMiddleYBoundary()) {
					return map.get(0).trackAgent(a);
				} else {
					return map.get(2).trackAgent(a);
				}
			} else {
				if (loc[1] <= getMiddleYBoundary()) {
					return map.get(1).trackAgent(a);
				} else {
					return map.get(3).trackAgent(a);
				}
			}
		}

		return this;
	}

	public Set<Agent> getAgents() {
		return agents;
	}

	public Set<Agent> getAgentsAtQuadrant(int quadrant) {
		return map.get(quadrant).getAgents();
	}

	/**
	 * A convenience function for determining the new boundaries of a
	 * sub-quadrant given the sub-quadrant's index.
	 * <p/>
	 * Note: Quadrants are mapped according to the following diagram:
	 * <p/>
	 * ---------
	 * | 0 | 1 |
	 * ---------
	 * | 2 | 3 |
	 * ---------
	 *
	 * @param quadrant The quadrant to compute the sub-boundaries of.
	 * @return The coordinate boundaries of the sub-quadrant.
	 */
	private int[][] findNewPartitionBoundary(int quadrant) {
		int[][] newBoundary = new int[2][2];

		// Break out stored boundary to a more convenient form
		int lowX = boundary[0][0];
		int lowY = boundary[0][1];
		int highX = boundary[1][0];
		int highY = boundary[1][1];

		// For readability, store mid points now
		int midX = getMiddleXBoundary();
		int midY = getMiddleYBoundary();

		switch (quadrant) {

			// Top Left
			case 0:
				newBoundary[0][0] = lowX;
				newBoundary[0][1] = lowY;
				newBoundary[1][0] = midX;
				newBoundary[1][1] = midY;
				break;

			// Top Right
			case 1:
				newBoundary[0][0] = midX;
				newBoundary[0][1] = lowY;
				newBoundary[1][0] = highX;
				newBoundary[1][1] = midY;
				break;

			// Bottom Left
			case 2:
				newBoundary[0][0] = lowX;
				newBoundary[0][1] = midY;
				newBoundary[1][0] = midX;
				newBoundary[1][1] = highY;
				break;

			// Bottom Right
			case 3:
				newBoundary[0][0] = midX;
				newBoundary[0][1] = midY;
				newBoundary[1][0] = highX;
				newBoundary[1][1] = highY;
				break;

			// Nowhere
			default:
		}

		return newBoundary;
	}

	private void partition(int parts) {
		if (map.isEmpty()) {
			for (int i = 0; i < MAX_PARTS; i++) {
				map.put(i, new Map(findNewPartitionBoundary(i), parts, this,
						i));
			}
		} else {
			// TODO: maybe re-partition to an exponential size?
		}
	}

	protected int getMiddleXBoundary() {
		return (boundary[1][0] - boundary[0][0]) / 2 + boundary[0][0];
	}

	protected int getMiddleYBoundary() {
		return (boundary[1][1] - boundary[0][1]) / 2 + boundary[0][1];
	}

	public Map getQuadrant(int i) {
		return map.get(i);
	}

	public int getStartX() {
		return boundary[0][0];
	}

	public int getStartY() {
		return boundary[0][1];
	}

	public int getEndX() {
		return boundary[1][0];
	}

	public int getEndY() {
		return boundary[1][1];
	}

	public int getSubQuadrantCount() {
		return map.size();
	}

	public void updateQuadrant(Agent agent) {
		agents.remove(agent);
		theRoot.trackAgent(agent); // TODO: optimize, maybe?
	}

	public int getQuadNum() {
		return quadNum;
	}

	public Map getParent() {
		return parent;
	}
}

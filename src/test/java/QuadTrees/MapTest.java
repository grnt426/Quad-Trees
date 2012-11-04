package QuadTrees;

import junit.framework.TestCase;

/**
 * Author:      Grant Kurtz
 */
public class MapTest extends TestCase {

	private int[][] coords;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		coords = new int[2][2];
		coords[0][0] = 0;
		coords[0][1] = 0;
		coords[1][0] = 100;
		coords[1][1] = 100;
	}

	public void testGetAgents() throws Exception {
		Map map = new Map(null, 0);
		map.trackAgent(new Agent(null));
		assertEquals(1, map.getAgents().size());
	}

	public void testGetMiddleXBoundary() throws Exception {
		Map map = new Map(coords, 1);
		assertEquals(50, map.getMiddleXBoundary());
	}

	public void testGetMiddleYBoundary() throws Exception {
		Map map = new Map(coords, 1);
		assertEquals(50, map.getMiddleYBoundary());
	}

	public void testGetAgentsAtQuadrant() throws Exception {
		int[] agentCoords = new int[2];
		agentCoords[0] = 75;
		agentCoords[1] = 75;
		Map map = new Map(coords, 1);
		Agent a = new Agent(agentCoords);
		map.trackAgent(a);
		assertEquals(1, map.getAgentsAtQuadrant(3).size());
		assertEquals(map.getQuadrant(3), a.getQuadrant());
	}
}

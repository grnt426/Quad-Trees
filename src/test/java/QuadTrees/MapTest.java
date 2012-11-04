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
		Map map = new Map(coords, 0, null, -1);
		map.trackAgent(new Agent(new int[2]));
		assertEquals(1, map.getAgents().size());
	}

	public void testGetMiddleXBoundary() throws Exception {
		Map map = new Map(coords, 1, null, -1);
		assertEquals(50, map.getMiddleXBoundary());
	}

	public void testGetMiddleYBoundary() throws Exception {
		Map map = new Map(coords, 1, null, -1);
		assertEquals(50, map.getMiddleYBoundary());
	}

	public void testGetAgentsAtQuadrant() throws Exception {
		int[] agentCoords = new int[2];
		agentCoords[0] = 75;
		agentCoords[1] = 75;
		Map map = new Map(coords, 1, null, -1);
		Agent a = new Agent(agentCoords);
		map.trackAgent(a);
		assertEquals(1, map.getAgentsAtQuadrant(3).size());
		assertEquals(map.getQuadrant(3), a.getQuadrant());
	}

	public void testGetAgentsAtQuadrantAfter4SubDivisions() throws Exception {
		int[] agentCoords = new int[2];
		agentCoords[0] = 93;
		agentCoords[1] = 99;
		Map map = new Map(coords, 4, null, -1);
		Agent a = new Agent(agentCoords);
		map.trackAgent(a);
		Map n = map.getQuadrant(3).getQuadrant(3).getQuadrant(3);
		assertEquals(1, n.getAgentsAtQuadrant(2).size());
		assertEquals(n.getQuadrant(2), a.getQuadrant());
	}
}

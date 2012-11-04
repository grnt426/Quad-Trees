package QuadTrees;

import java.util.Random;

/**
 * Author:      Grant Kurtz
 */
public class Agent {

	private int[] coords;
	private Map quadrant;
	private final int moveAlgo;
	private final int maxMoveAlgos = 1;
	private final int MAX_X_BOUNDARY;
	private final int MAX_Y_BOUNDARY;
	private boolean xPositive;
	private boolean yPositive;
	private static final Random gen = new Random();

	public Agent(int[] coords) {
		this.coords = coords;
		moveAlgo = gen.nextInt(maxMoveAlgos);
		MAX_X_BOUNDARY = 30 + gen.nextInt(280);
		MAX_Y_BOUNDARY = 30 + gen.nextInt(200);
		xPositive = gen.nextBoolean();
		yPositive = gen.nextBoolean();
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

	public void move(long tick) {
		switch (moveAlgo) {
			case 0:
			default:
				squareMove(tick);
		}
		quadrant.updateQuadrant(this);
	}

	private void squareMove(long tick) {
		int x = coords[0];
		int y = coords[1];

		if (!xPositive && belowXBounds(x))
			xPositive = true;
		else if (xPositive && aboveXBounds(x))
			xPositive = false;

		if (!yPositive && belowYBounds(y))
			yPositive = true;
		else if (yPositive && aboveYBounds(y))
			yPositive = false;

		coords[0] += xPositive ? 1 : -1;
		coords[1] += yPositive ? 1 : -1;
	}

	private boolean belowXBounds(int x) {
		return x < 320 - MAX_X_BOUNDARY;
	}

	private boolean belowYBounds(int y) {
		return y < 240 - MAX_Y_BOUNDARY;
	}

	private boolean aboveXBounds(int x) {
		return x > 320 + MAX_X_BOUNDARY;
	}

	private boolean aboveYBounds(int y) {
		return y > 240 + MAX_Y_BOUNDARY;
	}
}

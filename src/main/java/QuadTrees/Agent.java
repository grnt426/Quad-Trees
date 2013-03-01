package QuadTrees;

import java.util.Arrays;
import java.util.Random;

/**
 * Author:      Grant Kurtz
 */
public class Agent{

	private int[] coords;
	private Map quadrant;
	private final int moveAlgo;
	private final int maxMoveAlgos = 1;
	private final int MAX_X_BOUNDARY;
	private final int MAX_Y_BOUNDARY;
	private boolean xPositive;
	private boolean yPositive;
	private static final Random gen = new Random(0);
	private boolean[] collideWalls = {false, false, false, false};
	private int walls = 0;

	public Agent(int[] coords){
		this.coords = coords;
		moveAlgo = gen.nextInt(maxMoveAlgos);
//		MAX_X_BOUNDARY = 90 + gen.nextInt(220);
//		MAX_Y_BOUNDARY = 80 + gen.nextInt(150);
		MAX_X_BOUNDARY = 1195;
		MAX_Y_BOUNDARY = 795;
		xPositive = gen.nextBoolean();
		yPositive = gen.nextBoolean();
	}

	public int[] getCoords(){
		return coords;
	}

	public void setCoords(int[] coords){
		this.coords = coords;
	}

	public Map getQuadrant(){
		return quadrant;
	}

	public void setQuadrant(Map quadrant){
		this.quadrant = quadrant;
	}

	public void move(long tick){

		switch(moveAlgo){
			case 0:
			default:
				squareMove(tick);
		}
		quadrant.updateQuadrant(this);
	}

	private void squareMove(long tick){

		int x = coords[0];
		int y = coords[1];

		// if we are surrounded on all sides, skip this tick, but keep our
		// vector
		if(walls == 4){
			walls = 0;
			Arrays.fill(collideWalls, false);
			return;
		}
		walls = 0;

		if(!xPositive && (belowXBounds(x) || collideWalls[3])){
			xPositive = true;
		}
		else if(xPositive && (aboveXBounds(x) || collideWalls[1])){
			xPositive = false;
		}

		if(!yPositive && (belowYBounds(y) || collideWalls[0])){
			yPositive = true;
		}
		else if(yPositive && (aboveYBounds(y) || collideWalls[2])){
			yPositive = false;
		}

		Arrays.fill(collideWalls, false);

		coords[0] += xPositive ? 1 : -1;
		coords[1] += yPositive ? 1 : -1;
	}

	private boolean belowXBounds(int x){
		return x < 1200 - MAX_X_BOUNDARY;
	}

	private boolean belowYBounds(int y){
		return y < 800 - MAX_Y_BOUNDARY;
	}

	private boolean aboveXBounds(int x){
		return x > MAX_X_BOUNDARY;
	}

	private boolean aboveYBounds(int y){
		return y > MAX_Y_BOUNDARY;
	}

	public boolean isColliding(Agent againstAgent){
		int[] againstCoords = againstAgent.getCoords();
		int diff = (Math.abs(coords[0] - againstCoords[0]) +
					Math.abs(coords[1] - againstCoords[1]));
		return diff < 3;
	}

	public void collideWith(Agent againstAgent){
		int[] againstCoords = againstAgent.getCoords();
		if(againstCoords[0] < coords[0]){
			if(!collideWalls[3]){
				walls++;
			}
			collideWalls[3] = true;
		}
		else if(againstCoords[0] > coords[0]){
			if(!collideWalls[1]){
				walls++;
			}
			collideWalls[1] = true;
		}
		if(againstCoords[1] < coords[1]){
			if(!collideWalls[0]){
				walls++;
			}
			collideWalls[0] = true;
		}
		if(againstCoords[1] > coords[1]){
			if(!collideWalls[2]){
				walls++;
			}
			collideWalls[2] = true;
		}
	}

	public void setXPositive(boolean xPositive){
		this.xPositive = xPositive;
	}

	public void setYPositive(boolean yPositive){
		this.yPositive = yPositive;
	}

	public boolean collidedWithAgents(){
		return walls > 0;
	}
}

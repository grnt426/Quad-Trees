package QuadTrees;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Author:      Grant Kurtz
 */
public class Main {

	private ArrayList<Agent> agents;
	private JFrame frame;
	private final ArrayList<String> coords = new ArrayList<String>();

	public static void main(String[] args) {
		Main m = new Main();
		m.loop();
	}

	private Main() {

		// Setup the map
		int[][] boundary = new int[2][2];
		boundary[0][0] = 0;
		boundary[0][1] = 0;
		boundary[1][0] = 640;
		boundary[1][1] = 480;
		Map map = new Map(boundary, 1, null, -1);
		agents = new ArrayList<Agent>();
		Random gen = new Random();

		int x = 10;
		int y = 10;

		int[] location = new int[2];
		location[0] = 205;
		location[1] = 205;
		Agent a = new Agent(location);
		a.setXPositive(true);
		a.setYPositive(true);
		map.trackAgent(a);
		agents.add(a);
		location = new int[2];
		location[0] = 225;
		location[1] = 225;
		Agent b = new Agent(location);
		b.setXPositive(false);
		b.setYPositive(false);
		agents.add(b);
		map.trackAgent(b);

//		for (int i = 0; i < 2000; i++) {
//
//			// Setup the Agent
//			int[] location = new int[2];
//			location[0] = x;
//			location[1] = y;
//			Agent a = new Agent(location);
//			map.trackAgent(a);
//			agents.add(a);
//			x += 12;
//			if (x > 630) {
//				x = 12;
//				y += 12;
//			}
//		}

		// Setup the Window
		DrawWindow dw = new DrawWindow(map, agents);

		frame = new JFrame();
		frame.setTitle("QuadTrees!");
		frame.setVisible(true);
		frame.getContentPane().add(dw);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		dw.repaint();
	}

	private void loop() {

		long tick = 0;

		while (true) {
			moveAgents(tick++);
			detectCollisions();
			frame.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void detectCollisions() {
		Set<Map> candidates = Map.getContainsMultiple();
		for (Map node : candidates) {

			// Implement naive collision detection for this node
			// TODO: Improve this by removing non-colliding agents
			for (Agent checkAgent : node.getAgents()) {
				for (Agent againstAgent : node.getAgents()) {
					if (checkAgent == againstAgent)
						continue;
					if (checkAgent.isColliding(againstAgent)) {
						checkAgent.collideWith(againstAgent);
						againstAgent.collideWith(checkAgent);
					}
				}
			}

			for (Agent agent : node.getAgents()) {
				agent.updateVector();
			}
		}
	}

	private void moveAgents(long tick) {
		for (Agent a : agents) {
			a.move(tick);
		}
	}
}

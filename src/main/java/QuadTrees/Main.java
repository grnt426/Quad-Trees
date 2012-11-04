package QuadTrees;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

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
		Map map = new Map(boundary, 0);
		agents = new ArrayList<Agent>();
		Random gen = new Random();

		int x = 10;
		int y = 10;

		for (int i = 0; i < 300; i++) {

			// Setup the Agent
			int[] location = new int[2];
			location[0] = x;
			location[1] = y;
			Agent a = new Agent("Bob", location);
			map.trackAgent(a);
			agents.add(a);
			x += 27;
			if (x > 630) {
				x = 10;
				y += 27;
			}
		}

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
			System.out.println("Tick: " + tick);
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

	}

	private void moveAgents(long tick) {
		for (Agent a : agents) {
			a.move(tick);
		}
	}
}

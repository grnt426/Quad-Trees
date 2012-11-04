package QuadTrees;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Author:      Grant Kurtz
 */
public class Main {

	public static void main(String[] args) {

		// Setup the map
		int[][] boundary = new int[2][2];
		boundary[0][0] = 0;
		boundary[0][1] = 0;
		boundary[1][0] = 640;
		boundary[1][0] = 480;
		Map map = new Map(boundary, true);

		// Setup the Agent
		int[] location = new int[2];
		location[0] = 50;
		location[1] = 50;
		Agent a = new Agent("Bob", location);
		map.trackAgent(a);

		// Setup the Window
		ArrayList<Agent> agents = new ArrayList<Agent>();
		agents.add(a);
		DrawWindow dw = new DrawWindow(map, agents);

		JFrame frame = new JFrame();
		frame.setTitle("QuadTrees!");
		frame.setVisible(true);
		frame.getContentPane().add(dw);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		dw.repaint();
	}
}

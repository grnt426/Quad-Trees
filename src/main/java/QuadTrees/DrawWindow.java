package QuadTrees;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Author:      Grant Kurtz
 */
public class DrawWindow extends JPanel {

	private final Dimension dimension = new Dimension(640, 480);

	private final Map map;
	private final ArrayList<Agent> agents;

	public DrawWindow(Map map, ArrayList<Agent> agents) {
		this.map = map;
		this.agents = agents;
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);

		setBackground(Color.WHITE);

		g.setColor(Color.BLUE);
		for (Agent a : agents) {
			int[] coords = a.getCoords();
			g.drawOval(coords[0], coords[1], 1, 1);
		}
	}

	public Dimension getPreferredSize() {
		return dimension;
	}
}

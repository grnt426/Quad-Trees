package QuadTrees;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Author:      Grant Kurtz
 */
public class DrawWindow extends JPanel {

	private final Dimension dimension = new Dimension(645, 485);

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

		// Draw Agents before the grid
		g.setColor(Color.BLUE);
		for (Agent a : agents) {
			int[] coords = a.getCoords();
			g.drawOval(coords[0], coords[1], 3, 3);
		}

		// Now draw the grid so it appears on top
		g.setColor(Color.DARK_GRAY);
		drawGrid(map, g);
	}

	private void drawGrid(Map node, Graphics g) {
		g.drawRect(node.getStartX(), node.getStartY(),
				node.getEndX() - node.getStartX(),
				node.getEndY() - node.getStartY());
		for (int i = 0; i < node.getSubQuadrantCount(); i++) {
			drawGrid(node.getQuadrant(i), g);
		}
	}

	public Dimension getPreferredSize() {
		return dimension;
	}
}

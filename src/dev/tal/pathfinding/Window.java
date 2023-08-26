package dev.tal.pathfinding;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class Window extends JFrame{
	
	public Window(String title, int width, int height, Panel panel) {
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(panel);
		setVisible(true);
	}

}

package dev.tal.pathfinding;

import java.util.ArrayList;
import java.util.List;

import dev.tal.pathfinding.node.Node;

public class Main {

	public static final String title = "Pathfinding - Test";
	public static final int width = 800, height = 600;
	
	private static Window window;
	private static Panel panel;
	public static List<Node> nodes = new ArrayList<Node>();
	
	public static void main(String[] args) {
		panel = new Panel();
		window = new Window(title, width, height, panel);
	}
	
	public static Window getWindow() {
		return window;
	}
	
	public static Panel getPanel() {
		return panel;
	}
}

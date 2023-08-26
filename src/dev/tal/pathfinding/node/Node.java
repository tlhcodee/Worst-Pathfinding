package dev.tal.pathfinding.node;

@SuppressWarnings("serial")
public class Node {
	
	private int id;
	private int x, y;
	private String tag;

	public Node(String tag, int id, int x, int y) {
		this.tag = tag;
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	

	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}

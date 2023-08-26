package dev.tal.pathfinding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JPanel;

import dev.tal.pathfinding.node.Node;

@SuppressWarnings("serial")
public class Panel extends JPanel{
	
	public static List<Node> way;
	
	public Panel() {
		JButton calculate = new JButton("Calculate");
		calculate.setVisible(true);
		calculate.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				way = new ArrayList<Node>();
				List<Node> checked = new ArrayList<Node>();
				List<Node> notChecked = Main.nodes;
				
				Node lastNode = getStart();
				for(Node n : notChecked) {
					if(n.getTag().equals("wall") || notChecked.get(n.getId() + 1).getTag().equals("wall")) {
						checked.add(n);
						notChecked.remove(n);
					} else {
						Node current = n;
						if(lastNode != null) {
							double currentDiffX = Math.abs(current.getX() - getGoal().getX());
							double lastDiffX = Math.abs(lastNode.getX() - getGoal().getX());
							double currentDiffY = Math.abs(current.getY() - getGoal().getY());
							double lastDiffY = Math.abs(lastNode.getY() - getGoal().getY());
							
							if(currentDiffX < lastDiffX && currentDiffY < lastDiffY) {
								way.add(current);
							} else if(lastDiffX < currentDiffX && lastDiffY < currentDiffY) {
								way.add(lastNode);
							} else if(current.getX() == getGoal().getX()) {
								boolean goDown = current.getY() < getGoal().getY();
								boolean goUp = current.getY() > getGoal().getY();
								if(goDown) {
									for(int y = current.getY(); y < getGoal().getY(); y = y + 30) {
										if(current.getY() == y && y < getGoal().getY()) {
											way.add(current);
										}
									}
								} else if(goUp) {
									for(int y = current.getY(); y > getGoal().getY(); y = y - 30) {
										if(current.getY() == y && y > getGoal().getY()) {
											way.add(current);
										}
									}
								} else {
									break;
								}
							}
						}
						lastNode = current;
					}
				}
				for(Node n : way) {
					System.out.println("X: " + n.getX() + " Y: " + n.getY());
				}
			}
		});
		add(calculate);
		for(int x = 0; x < Main.width; x = x + 30) {
			for(int y = 0; y < Main.height; y = y + 30) {
				Main.nodes.add(new Node("node", Main.nodes.size() + 1, x, y));
			}
		}
		start(30, 30);
		goal(630, 270);
	}
	
	public void goal(int x, int y) {
		List<Node> newNode = Main.nodes.stream().filter(n -> n.getX() == x && n.getY() == y).collect(Collectors.toList());
		
		if(!newNode.isEmpty()) {
			newNode.get(0).setTag("goal");
			System.out.println("SET");
		}
	}
	
	public void start(int x, int y) {
		List<Node> newNode = Main.nodes.stream().filter(n -> n.getX() == x && n.getY() == y).collect(Collectors.toList());
		
		if(!newNode.isEmpty()) {
			newNode.get(0).setTag("start");
		}
	}
	
	public void wall(int x, int y) {
		List<Node> newNode = Main.nodes.stream().filter(n -> n.getX() == x && n.getY() == y).collect(Collectors.toList());
		
		if(!newNode.isEmpty()) {
			newNode.get(0).setTag("wall");
		}
	}
	
	public Node getGoal() {
		for(Node n : Main.nodes) {
			if(n.getTag().equals("goal")) {
				return n;
			}
		}
		return null;
	}
	
	public Node getStart() {
		for(Node n : Main.nodes) {
			if(n.getTag().equals("start")) {
				return n;
			}
		}
		return null;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		if(way != null && !(way.isEmpty())) {
			for(Node node : way) {
				g.drawLine(
						node.getX(),
						node.getY(),
						node.getX() + 30,
						node.getY());
				
				g.drawLine(
						node.getX() + 30,
						node.getY(),
						node.getX() + 30,
						node.getY() + 30);
				
				g.setColor(Color.yellow);
				g.fillRect(node.getX(),
						node.getY(),
						30, 30);
				g.setColor(Color.black);
			}
		}
		for(Node node : Main.nodes) {
			g.drawLine(
					node.getX(),
					node.getY(),
					node.getX() + 30,
					node.getY());
			
			g.drawLine(
					node.getX() + 30,
					node.getY(),
					node.getX() + 30,
					node.getY() + 30);
			
			if(node.getTag().equals("goal")) {
				g.setColor(Color.green);
				g.fillRect(node.getX(),
						node.getY(),
						30, 30);
				g.setColor(Color.black);
			} else if(node.getTag().equals("start")) {
				g.setColor(Color.blue);
				g.fillRect(node.getX(),
						node.getY(),
						30, 30);
				g.setColor(Color.black);
			} else if(node.getTag().equals("wall")) {
				g.setColor(Color.black);
				g.fillRect(node.getX(),
						node.getY(),
						30, 30);
				g.setColor(Color.black);
			}
		}
		this.repaint();
	}

}

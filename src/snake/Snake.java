package snake;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * A SNAKE!
 * 
 * @author Kurtis Eveleigh
 * @version 0.0.9
 */

public class Snake {
	private Point pos;
	private int dir;
	private int length;
	private ArrayList<Point> path;
	private boolean dead;

	public Snake() {
		pos = new Point(GameBoard.side/2 - 1, GameBoard.side/2 - 1);
		dir = 0;
		length = 1;
		path = new ArrayList<Point>();
		dead = false;
		path.add((Point)pos.clone());
	}

	public boolean pathContains(Point p) {
		return path.contains(p);
	}

	public Point getPos() {
		return pos;
	}
	
	public int getLength()
	{
		return length;
	}

	public void move() {
		switch (dir) {
		case 0:
			pos.y--;
			break;
		case 1:
			pos.x++;
			break;
		case 2:
			pos.y++;
			break;
		case 3:
			pos.x--;
			break;
		}
		if (pos.x < 0) {
			pos.x = GameBoard.side - 1;
		} else if (pos.x > GameBoard.side - 1) {
			pos.x = 0;
		}
		if (pos.y < 0) {
			pos.y = GameBoard.side - 1;
		} else if (pos.y > GameBoard.side - 1) {
			pos.y = 0;
		}
		
		if(path.size() >= length)
		{
			path.remove(0);
		}
		if(pathContains(pos))
		{
			dead = true;
		}
		path.add((Point)pos.clone());
	}

	public void setDirection(String newDir) {
		switch (newDir.toLowerCase()) {
		case "up":
			if (dir != 2) {
				dir = 0;
			}
			break;
		case "right":
			if (dir != 3) {
				dir = 1;
			}
			break;
		case "down":
			if (dir != 0) {
				dir = 2;
			}
			break;
		case "left":
			if (dir != 1) {
				dir = 3;
			}
			break;
		}
	}

	public void eat() {
		length++;
	}
	
	public boolean isDead()
	{
		return dead;
	}

	public void draw(Graphics g) {
		for (Point p : path) {
			g.fillRect(p.x * (GameBoard.width / GameBoard.side), p.y * (GameBoard.width / GameBoard.side), (GameBoard.width / GameBoard.side), (GameBoard.width / GameBoard.side));
		}
	}
}

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

	public Snake() {
		pos = new Point(49, 49);
		dir = 0;
		length = 1;
		path = new ArrayList<Point>();
		path.add((Point)pos.clone());
	}

	public boolean pathContains(Point p) {
		return path.contains(p);
	}

	public Point getPos() {
		return pos;
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
			pos.x = 99;
		} else if (pos.x > 99) {
			pos.x = 0;
		}
		if (pos.y < 0) {
			pos.y = 99;
		} else if (pos.y > 99) {
			pos.y = 0;
		}
		path.add((Point)pos.clone());
		if(path.size() > length)
		{
			path.remove(0);
		}
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

	public void draw(Graphics g) {
		for (Point p : path) {
			g.fillRect(p.x * 5, p.y * 5, 5, 5);
		}
	}
}

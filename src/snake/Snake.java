package snake;

import java.awt.Graphics;
import java.util.Random;

/**
 * A SNAKE!
 * 
 * @author Kurtis Eveleigh
 * @version 1.0.0
 */

public class Snake {
	int x;
	int y;
	int length;
	int dir;
	Random rand;

	public Snake() {
		x = 49;
		y = 49;
		dir = 0;
	}

	public void move() {
		switch (dir) {
		case 0:
			y--;
			break;
		case 1:
			x++;
			break;
		case 2:
			y++;
			break;
		case 3:
			x--;
			break;
		}
		if (x < 0) {
			x = 99;
		} else if (x > 99) {
			x = 0;
		}
		if (y < 0) {
			y = 99;
		} else if (y > 99) {
			y = 0;
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

	public void draw(Graphics g) {
		g.drawRect(x * 5, y * 5, 5, 5);
	}
}

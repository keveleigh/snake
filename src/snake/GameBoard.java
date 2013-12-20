package snake;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Displays the snake game board in a JPanel.
 * 
 * @author Kurtis Eveleigh
 * @version 0.0.9
 */

@SuppressWarnings("serial")
public class GameBoard extends JPanel {
	private Timer timer;
	private Snake snake;
	private Point food;
	private Random rand;
	private int delay;
	public static final int side = 50;
	public static final int width = 500;

	public GameBoard() {
		rand = new Random();
		delay = 100;
		timer = new Timer(delay, new TimerListener());
		timer.setRepeats(true);
		snake = new Snake();
		food = new Point(rand.nextInt(side), rand.nextInt(side));
		setPreferredSize(new Dimension(width, width));
		addKeyListener(new PlayerListener());
		setFocusable(true);
	}

	public boolean timerIsEnabled() {
		return timer.isRunning();
	}

	public boolean flipTimer() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
		return timer.isRunning();
	}

	public void enableTimer(boolean enable) {
		if (enable) {
			timer.start();
		} else {
			timer.stop();
		}
	}

	public void step() {
		snake.move();
		repaint();
		if (snake.isDead()) {
			reset();
		}
		if (food.equals(snake.getPos())) {
			snake.eat();
			generateNewFood();
			if(snake.getLength()%3 == 0 && delay > 25)
			{
				delay--;
				timer.setDelay(delay);
			}
		}
	}

	public void changeTimerSpeed(int newSpeed) {
		timer.setDelay(newSpeed);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(food.x * (this.getWidth() / side), food.y * (width / side), (width / side) - 1, (width / side) - 1);
		snake.draw(g);
	}

	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			step();
		}
	}

	public void reset() {
		timer.stop();
		snake = new Snake();
		food = new Point(rand.nextInt(side), rand.nextInt(side));
		repaint();
	}

	public void generateNewFood() {
		int newX = rand.nextInt(side);
		int newY = rand.nextInt(side);
		while (snake.pathContains(new Point(newX, newY))) {
			newX = rand.nextInt(side);
			newY = rand.nextInt(side);
		}
		food = new Point(newX, newY);
	}

	private class PlayerListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				snake.setDirection("up");
				break;
			case KeyEvent.VK_DOWN:
				snake.setDirection("down");
				break;
			case KeyEvent.VK_LEFT:
				snake.setDirection("left");
				break;
			case KeyEvent.VK_RIGHT:
				snake.setDirection("right");
				break;
			case KeyEvent.VK_SPACE:
				flipTimer();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}
}

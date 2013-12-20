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
 * @version 1.0.0
 */

@SuppressWarnings("serial")
public class GameBoard extends JPanel {
	private Timer timer;
	private Snake snake;
	private Point food;
	private Random rand;

	public GameBoard() {
		rand = new Random();
		timer = new Timer(150, new TimerListener());
		timer.setRepeats(true);
		snake = new Snake();
		food = new Point(rand.nextInt(100), rand.nextInt(100));
		setPreferredSize(new Dimension(500, 500));
		addKeyListener(new PlayerListener());
		setFocusable(true);
	}

	public boolean timerIsEnabled() {
		return timer.isRunning();
	}

	public Boolean flipTimer() {
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
	}

	public void changeTimerSpeed(int newSpeed) {
		timer.setDelay(newSpeed);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(food.x * 5, food.y * 5, 5, 5);
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
		food = new Point();
		repaint();
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

package v5.game.sokoban;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import v5.game.sokoban.controller.Controller;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.view.Graphics;
import v5.game.sokoban.view.View;

public class TestGame {

	protected static final Color[] COLORS = { Color.black, Color.yellow,
			Color.blue, Color.green, Color.magenta, Color.cyan };

	public static void main(String[] args) {

		// create controller
		final Controller controller = new Controller();

		// create ui
		JFrame frame = new JFrame("Sokoban") {
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(java.awt.Graphics g) {
				controller.repaintView();
			}
		};
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// create input data stream
		frame.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(final KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					controller.moveMan(Direction.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					controller.moveMan(Direction.RIGHT);
					break;
				case KeyEvent.VK_DOWN:
					controller.moveMan(Direction.DOWN);
					break;
				case KeyEvent.VK_UP:
					controller.moveMan(Direction.UP);
					break;
				case KeyEvent.VK_SPACE:
					controller.loadField(0);
					break;
				default:
					break;
				}
			}

		});

		// create output data stream
		final Graphics2D graphics = (Graphics2D) frame.getGraphics();

		View view = new View();
		view.setGraphics(new Graphics() {

			@Override
			public void fillRect(int x, int y, int width, int height,
					int colorIndex) {
				graphics.setColor(COLORS[colorIndex]);
				graphics.fillRect(x, y, width, height);
			}
		});
		controller.setView(view);

		// init controller
		controller.setFieldDefault();
		// controller.loadField();
	}

}

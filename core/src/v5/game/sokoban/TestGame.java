package v5.game.sokoban;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import v5.game.sokoban.controller.Controller;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.view.Graphics;

public class TestGame {

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
		frame.setSize(450, 300);
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
					controller.moveMan(Direction.UP);
					break;
				case KeyEvent.VK_UP:
					controller.moveMan(Direction.DOWN);
					break;
				default:
					break;
				}
			}

		});

		// create output data stream
		final java.awt.Graphics graphics = frame.getGraphics();

		controller.getView().setGraphics(new Graphics() {

			@Override
			public void fillRect(Unit unit, int x, int y) {

				if (unit == null) {
					graphics.setColor(Color.gray);
				} else {

					switch (unit) {
					case WALL:
						graphics.setColor(Color.black);
						break;
					case TARGET:
						graphics.setColor(Color.red);
						break;
					case BOX:
						graphics.setColor(Color.blue);
						break;
					case MAN:
						graphics.setColor(Color.yellow);
						break;
					default:
						break;
					}
				}

				graphics.fillRect(x, y, 30, 30);
			}

		});

		// init controller
		controller.setFieldDefault();
	}

}

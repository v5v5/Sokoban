package v5.game.sokoban;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import v5.game.sokoban.controller.Controller;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.view.Graphics;

public class TestGame {

	public static void main(String[] args) {

		final Controller controller = new Controller();

		JFrame frame = new JFrame("Sokoban");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 300));

		// create inputs
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
				default:
					break;
				}
			}

		});

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		// create outputs
		final Graphics2D graphics = (Graphics2D) panel.getGraphics();

		controller.getView().setGraphics(new Graphics() {

			@Override
			public void fillRect(Unit unit, int x, int y) {
				 System.out.println("unit: " + unit + ", x: " + x + ", y: " +
				 y);

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

		controller.setField();
	}

}

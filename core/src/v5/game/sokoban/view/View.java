package v5.game.sokoban.view;

import java.util.ArrayList;

import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.model.dynamicObjects.BoxObject;
import v5.game.sokoban.model.dynamicObjects.ManObject;

public class View {

	public void draw(State state) {
		drawField(state.getField());
		drawBoxes(state.getBoxes());
		drawMan(state.getMan());
	}

	private void drawBoxes(ArrayList<BoxObject> boxes) {
		for (BoxObject box : boxes) {
			drawUnit(Unit.BOX, box.getRow(), box.getCol());
		}
	}

	private void drawField(Unit[][] field) {
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				drawUnit(field[row][col], row, col);
			}
		}
	}

	private void drawMan(ManObject pos) {
		// if (null == pos)
		// return;
		drawUnit(Unit.MAN, pos.getRow(), pos.getCol());
	}

	// abstract public void drawUnit(Unit unit, int row, int col);

	final static int BOX_SIZE = 30;
	final static int ORIGIN_X = 50;
	final static int ORIGIN_Y = 50;

	private Graphics g;

	protected void drawUnit(Unit unit, int row, int col) {
		// final java.awt.Graphics g = frame.getGraphics();

		// System.out.println(String.format("unit: %s , row: %d, col: %d", unit,
		// row, col));

		int x = ORIGIN_X + col * BOX_SIZE;
		int y = ORIGIN_Y + row * BOX_SIZE;

		if (null == unit) {
			g.fillRect(x, y, BOX_SIZE, BOX_SIZE, 0);
		} else {

			switch (unit) {
			case WALL:
				g.fillRect(x, y, BOX_SIZE, BOX_SIZE, 1);
				break;
			case TARGET:
				g.fillRect(x, y, BOX_SIZE, BOX_SIZE, 2);
				break;
			case BOX:
				g.fillRect(x, y, BOX_SIZE, BOX_SIZE, 3);
				break;
			case MAN:
				g.fillRect(x, y, BOX_SIZE, BOX_SIZE, 4);
				break;
			default:
				break;
			}
		}
	}

	public void setGraphics(Graphics graphics) {
		g = graphics;
	}

	public void createActors(State state) {
		g.fillRect(0, 0, 20 * BOX_SIZE, 20 * BOX_SIZE, 0);
	}

}

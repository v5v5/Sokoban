package v5.game.sokoban.view;

import java.awt.Color;
import java.util.ArrayList;

import v5.game.sokoban.model.ModelListener;
import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.model.dynamicObjects.BoxObject;
import v5.game.sokoban.model.dynamicObjects.ManObject;

public abstract class View implements ModelListener, ViewInterface {

	// final static int BOX_SIZE = 30;
	// final static int ORIGIN_X = 50;
	// final static int ORIGIN_Y = 50;

	@Override
	public void onChange(State state) {
		draw(state);
	}

	private void draw(State state) {
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

	abstract public void drawUnit(Unit unit, int row, int col);

	final static int BOX_SIZE = 30;
	final static int ORIGIN_X = 50;
	final static int ORIGIN_Y = 50;

	public void drawUnitExample(Unit unit, int row, int col, java.awt.Graphics g) {
//		final java.awt.Graphics g = frame.getGraphics();

//		System.out.println(String.format("unit: %s , row: %d, col: %d", unit,
//				row, col));

		int x = ORIGIN_X + col * BOX_SIZE;
		int y = ORIGIN_Y + row * BOX_SIZE;

		if (null == unit) {
			g.setColor(Color.gray);
		} else {

			switch (unit) {
			case WALL:
				g.setColor(Color.black);
				break;
			case TARGET:
				g.setColor(Color.red);
				break;
			case BOX:
				g.setColor(Color.blue);
				break;
			case MAN:
				g.setColor(Color.yellow);
				break;
			default:
				break;
			}
		}

		g.fillRect(x, y, 30, 30);
	}

}

package v5.game.sokoban.view;

import v5.game.sokoban.model.ModelListener;
import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;

public class View implements ModelListener, ViewInterface {

	final static int BOX_SIZE = 30;
	final static int ORIGIN_X = 50;
	final static int ORIGIN_Y = 50;

	private Graphics _graphics;

	@Override
	public void onChange(State state) {
		draw(state);
	}

	private void draw(final State state) {
		drawField(state.getField());
		drawMan(state.getManPosition());
	}

	private void drawMan(Point pos) {
		drawBox(Unit.MAN, pos._row, pos._col);
	}

	private void drawField(Unit[][] field) {
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				drawBox(field[row][col], row, col);
			}
		}
	}

	private void drawBox(Unit unit, int row, int col) {
		int x = ORIGIN_X + col * BOX_SIZE;
		int y = ORIGIN_X + row * BOX_SIZE;
		_graphics.fillRect(unit, x, y);

	}

	@Override
	public void setGraphics(Graphics g) {
		this._graphics = g;
	}

}

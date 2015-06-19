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
		drawTargets(state.getTargets());
		drawField(state.getField(), state.getTargets());
		drawMan(state.getMan());
	}

	private void drawTargets(Point[] targets) {
		if (null == targets)
			return;
		for (Point point : targets) {
			if (null == point)
				continue;
			drawBox(Unit.TARGET, point._row, point._col);
		}
	}

	private void drawMan(Point pos) {
		if (null == pos)
			return;
		drawBox(Unit.MAN, pos._row, pos._col);
	}

	private void drawField(Unit[][] field, Point[] targets) {
		if (null == field)
			return;
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				if (isTarget(row, col, targets) && (null == field[row][col])) {
//					drawBox(Unit.TARGET, row, col);
					continue;
				}

				drawBox(field[row][col], row, col);
			}
		}
	}

	private boolean isTarget(int row, int col, Point[] targets) {
		if (null == targets)
			return false;
		for (Point t : targets) {
			if (null == t)
				continue;
			if ((t._row == row) && (t._col == col)) {
				return true;
			}
		}
		return false;
	}

	private void drawBox(Unit unit, int row, int col) {
//		System.out.println(String.format("unit: %s , row: %d, col: %d", unit , row, col));
		
		if (null == _graphics)
			return;

		int x = ORIGIN_X + col * BOX_SIZE;
		int y = ORIGIN_Y + row * BOX_SIZE;		
		_graphics.fillRect(unit, x, y);

	}

	@Override
	public void setGraphics(Graphics g) {
		this._graphics = g;
	}

}

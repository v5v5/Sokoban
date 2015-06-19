package v5.game.sokoban.model.dynamicObjects;

import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;

public abstract class DynamicObject {

	protected State _state;
	int _row;
	int _col;

	public int getRow() {
		return _row;
	}

	public int getCol() {
		return _col;
	}

	public void setPos(int row, int col) {
		_row = row;
		_col = col;
	}

	public DynamicObject(State state, int row, int col) {
		_state = state;
		_row = row;
		_col = col;
	}

	public boolean move(Direction dir) {

		Point newPos = getNewPos(dir);

		BoxObject box = _state.getBoxObject(newPos._row, newPos._col);
		if (box != null) {
			box.move(dir);
		}

		_row = newPos._row;
		_col = newPos._col;

		return true;
	}

	public boolean canMove(Direction dir) {
		if (!checkStaticObject(dir)) {
			return false;
		}

		if (!checkDynamicObject(dir)) {
			return false;
		}

		return true;
	}

	abstract protected boolean checkDynamicObject(Direction dir);

	private boolean checkStaticObject(Direction dir) {
		Unit f[][] = _state.getField();
		Point newPos = getNewPos(dir);
		int row = newPos._row;
		int col = newPos._col;

		if ((f == null) || (f[0] == null)) {
			return false;
		}
		if ((row < 0) || (row >= f.length) || (col < 0) || col >= f[0].length) {
			return false;
		}

		if (Unit.WALL == f[row][col]) {
			return false;
		}
		return true;
	}

	protected Point getNewPos(Direction dir) {
		Point pos = new Point();
		pos._row = _row;
		pos._col = _col;

		switch (dir) {
		case RIGHT:
			pos._col = pos._col + 1;
			break;
		case LEFT:
			pos._col = pos._col - 1;
			break;
		case UP:
			pos._row = pos._row - 1;
			break;
		case DOWN:
			pos._row = pos._row + 1;
			break;
		}

		return pos;
	}

}

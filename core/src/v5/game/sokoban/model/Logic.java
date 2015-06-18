package v5.game.sokoban.model;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;

public class Logic implements LogicInterface {

	State _state = new State(this);

	State getState() {
		return _state;
	}

	Point setManPosition(int row, int col) {
		Point mp = _state._manPosition;
		mp._row = row;
		mp._col = col;
		return _state.getManPosition();
	}

	void setWall(int row, int col) {
		try {
			setUnit(row, col, Unit.WALL);
		} catch (OutInField e) {
			e.printStackTrace();
		}
	}

	void setBox(int row, int col) {
		try {
			setUnit(row, col, Unit.BOX);
		} catch (OutInField e) {
			e.printStackTrace();
		}
	}

	void setTarget(int row, int col) {
		try {
			setUnit(row, col, Unit.TARGET);
		} catch (OutInField e) {
			e.printStackTrace();
		}
	}

	void setUnit(int row, int col, Unit unit) throws OutInField {
		try {
			_state._field[row][col] = unit;
		} catch (Exception e) {
			throw new OutInField();
		}
	}

	public boolean moveMan(T.Direction direction) {

		if (!canManMove(direction))
			return false;

		Point p = _state._manPosition;

		switch (direction) {
		case RIGHT:
			setManPosition(p._row, p._col + 1);
			break;
		case LEFT:
			setManPosition(p._row, p._col - 1);
			break;
		case UP:
			setManPosition(p._row + 1, p._col);
			break;
		case DOWN:
			setManPosition(p._row - 1, p._col);
			break;
		}
		return true;
	}

	boolean canManMove(Direction direction) {

		Point p = _state._manPosition;
		Unit nearUnit = null;
		Unit farUnit = null;

		switch (direction) {
		case RIGHT:
			try {
				nearUnit = _state.getUnit(p._row, p._col + 1);
				farUnit = _state.getUnit(p._row, p._col + 2);
			} catch (OutInField e) {
				e.printStackTrace();
			}
			break;

		case LEFT:
			try {
				nearUnit = _state.getUnit(p._row, p._col - 1);
				farUnit = _state.getUnit(p._row, p._col - 2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case UP:
			try {
				nearUnit = _state.getUnit(p._row + 1, p._col);
				farUnit = _state.getUnit(p._row + 2, p._col);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case DOWN:
			try {
				nearUnit = _state.getUnit(p._row - 1, p._col);
				farUnit = _state.getUnit(p._row - 2, p._col);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}

		if (Unit.WALL == nearUnit) {
			return false;
		}
		if (Unit.BOX == nearUnit) {
			if ((Unit.BOX == farUnit) || (Unit.WALL == farUnit)) {
				return false;
			}
		}

		return true;
	}
}

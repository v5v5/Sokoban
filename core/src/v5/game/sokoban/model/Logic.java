package v5.game.sokoban.model;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;

public class Logic implements LogicInterface {

	State _state = new State(this);

	public State getState() {
		return _state;
	}

	Point setMan(int row, int col) {
		Point mp = _state._manPos;
		mp._row = row;
		mp._col = col;
		return _state.getMan();
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

		Point curPos = _state._manPos;
		Point newPos = new Point();
		Point newBoxPos = new Point();

		switch (direction) {
		case RIGHT:
			newPos._row = curPos._row;
			newPos._col = curPos._col + 1;
			newBoxPos._row = curPos._row;
			newBoxPos._col = curPos._col + 2;			
			break;
		case LEFT:
			newPos._row = curPos._row;
			newPos._col = curPos._col - 1;
			newBoxPos._row = curPos._row;
			newBoxPos._col = curPos._col - 2;			
			break;
		case UP:
			newPos._row = curPos._row + 1;
			newPos._col = curPos._col;
			newBoxPos._row = curPos._row + 2;
			newBoxPos._col = curPos._col;			
			break;
		case DOWN:
			newPos._row = curPos._row - 1;
			newPos._col = curPos._col;
			newBoxPos._row = curPos._row - 2;
			newBoxPos._col = curPos._col;			
			break;
		}
		
		Unit nearUnit = null;
		try {
			nearUnit = _state.getUnit(newPos._row, newPos._col);
		} catch (OutInField e) {
			e.printStackTrace();
		}
		
		if (Unit.BOX == nearUnit) {
			_state._field[newPos._row][newPos._col] = null;
			_state._field[newBoxPos._row][newBoxPos._col] = Unit.BOX;
		}
		
		setMan(newPos._row, newPos._col);
		return true;
	}

	boolean canManMove(Direction direction) {

		Point p = _state._manPos;
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

package v5.game.sokoban.model;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.model.dynamicObjects.BoxObject;
import v5.game.sokoban.model.dynamicObjects.ManObject;

public class Logic implements LogicInterface {

	State _state = new State(this);

	public State getState() {
		return _state;
	}

	@Override
	public boolean moveMan(Direction dir) {
		if (!_state._man.canMove(dir)) {
			return false;
		}

		return _state._man.move(dir);
	}

	public void createDefaultField() {
		_state._field = new Unit[7][11];

		// create walls
		int row = 0;
		for (int col = 0; col < _state._field[row].length; col++) {
			_state._field[row][col] = Unit.WALL;
		}
		for (int rrow = 1; rrow <= 5; rrow++) {
			_state._field[rrow][0] = Unit.WALL;
			_state._field[rrow][_state._field[row].length - 1] = Unit.WALL;
		}
		row = _state._field.length - 1;
		for (int col = 0; col < _state._field[row].length; col++) {
			_state._field[row][col] = Unit.WALL;
		}

		// create boxes
		addBox(2, 5);
		// create man
		setMan(3, 2);
		// create target
		setTarget(3, 8);
	}

	protected void setTarget(int row, int col) {
		_state._field[row][col] = Unit.TARGET;
	}

	public void createComplexField() {
		_state._field = new Unit[7][11];

		// create walls
		int row = 0;
		for (int col = 0; col < _state._field[row].length; col++) {
			_state._field[row][col] = Unit.WALL;
		}
		for (int rrow = 1; rrow <= 5; rrow++) {
			_state._field[rrow][0] = Unit.WALL;
			_state._field[rrow][_state._field[row].length - 1] = Unit.WALL;
		}
		row = _state._field.length - 1;
		for (int col = 0; col < _state._field[row].length; col++) {
			_state._field[row][col] = Unit.WALL;
		}

		// create boxes
		addBox(2, 5);
		addBox(4, 5);

		// create man
		setMan(3, 2);

		// create target
		setTarget(2, 8);
		setTarget(4, 8);
	}

	protected void addBox(int row, int col) {
		if (boxExists(row, col)) {
			return;
		}
		ManObject m = _state._man;
		if ((m != null) && (m.getRow() == row) && (m.getCol() == col)) {
			_state._man = null;
		}

		BoxObject box = new BoxObject(_state, row, col);
		_state._boxes.add(box);
	}

	private boolean boxExists(int row, int col) {
		for (BoxObject box : _state._boxes) {
			if ((box.getRow() == row) && (box.getCol() == col)) {
				return true;
			}
		}
		return false;
	}

	protected void setMan(int row, int col) {
		BoxObject box = _state.getBoxObject(row, col);
		if (null != box) {
			_state._boxes.remove(box);
		}

		ManObject m = _state._man;
		if (m == null) {
			_state._man = new ManObject(_state, row, col);
			return;
		}
		if ((m.getRow() == row) && (m.getCol() == col)) {
			return;
		}
		m.setPos(row, col);
	}

	protected void setUnit(int row, int col, Unit unit) throws OutInField {
		if (!checkFieldBounds(row, col)) {
			throw new OutInField();
		}

		if (null == unit) {
			ManObject m = _state._man;
			if ((m != null) && (m.getRow() == row) && (m.getCol() == col)) {
				_state._man = null;
			}
			for (BoxObject box : _state._boxes) {
				if ((box.getRow() == row) && (box.getCol() == col)) {
					_state._boxes.remove(box);
					break;
				}
			}
			_state._field[row][col] = null;
			return;
		}

		switch (unit) {
		case WALL:
			_state._field[row][col] = Unit.WALL;
			break;
		case TARGET:
			_state._field[row][col] = Unit.TARGET;
			break;
		case BOX:
			addBox(row, col);
			break;
		case MAN:
			setMan(row, col);
			break;
		default:
			break;
		}
	}

	public boolean checkFieldBounds(int row, int col) {
		if ((row < 0) || (row >= _state._field.length) || (col < 0)
				|| (col >= _state._field[0].length)) {
			return false;
		}
		return true;
	}

}

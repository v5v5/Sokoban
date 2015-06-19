package v5.game.sokoban.model;

import java.util.ArrayList;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.model.dynamicObjects.BoxObject;
import v5.game.sokoban.model.dynamicObjects.ManObject;

public class State {

	// fill static objects (Unit.WALL, Unit.TARGET, null)
	Unit[][] _field = new Unit[0][0];

	// dynamic objects (Unit.MAN, Unit.BOX)
	ManObject _man;
	ArrayList<BoxObject> _boxes = new ArrayList<BoxObject>();

	public void clear() {
		_field = new Unit[0][0];
		_man = null;
		_boxes.clear();
	}

	public void createDefaultField() {
		_field = new Unit[7][11];

		// create walls
		int row = 0;
		for (int col = 0; col < _field[row].length; col++) {
			_field[row][col] = Unit.WALL;
		}
		for (int rrow = 1; rrow <= 5; rrow++) {
			_field[rrow][0] = Unit.WALL;
			_field[rrow][_field[row].length - 1] = Unit.WALL;
		}
		row = _field.length - 1;
		for (int col = 0; col < _field[row].length; col++) {
			_field[row][col] = Unit.WALL;
		}

		// create boxes
		addBox(2, 5);
		// create man
		setMan(3, 2);
		// create target
		setTarget(3, 8);
	}

	protected void setTarget(int row, int col) {
		_field[row][col] = Unit.TARGET;
	}

	public void createComplexField() {
		_field = new Unit[7][11];

		// create walls
		int row = 0;
		for (int col = 0; col < _field[row].length; col++) {
			_field[row][col] = Unit.WALL;
		}
		for (int rrow = 1; rrow <= 5; rrow++) {
			_field[rrow][0] = Unit.WALL;
			_field[rrow][_field[row].length - 1] = Unit.WALL;
		}
		row = _field.length - 1;
		for (int col = 0; col < _field[row].length; col++) {
			_field[row][col] = Unit.WALL;
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
		if ((_man != null) && (_man.getRow() == row) && (_man.getCol() == col)) {
			_man = null;
		}

		BoxObject box = new BoxObject(this, row, col);
		_boxes.add(box);
	}

	private boolean boxExists(int row, int col) {
		for (BoxObject box : _boxes) {
			if ((box.getRow() == row) && (box.getCol() == col)) {
				return true;
			}
		}
		return false;
	}

	protected void setMan(int row, int col) {
		BoxObject box = getBoxObject(row, col);
		if (null != box) {
			_boxes.remove(box);
		}

		if (_man == null) {
			_man = new ManObject(this, row, col);
			return;
		}
		if ((_man.getRow() == row) && (_man.getCol() == col)) {
			return;
		}
		_man.setPos(row, col);
	}

	public BoxObject getBoxObject(int row, int col) {
		for (BoxObject box : _boxes) {
			if ((box.getRow() == row) && (box.getCol() == col)) {
				return box;
			}
		}
		return null;
	}

	public ManObject getMan() {
		return _man;
	}

	protected void setField(int row, int col) {
		_field = new Unit[row][col];
	}

	public Unit[][] getField() {
		return _field;
	}

	protected void setUnit(int row, int col, Unit unit) throws OutInField {
		if (!checkFieldBounds(row, col)) {
			throw new OutInField();
		}

		if (null == unit) {
			if ((_man != null) && (_man.getRow() == row)
					&& (_man.getCol() == col)) {
				_man = null;
			}
			for (BoxObject box : _boxes) {
				if ((box.getRow() == row) && (box.getCol() == col)) {
					_boxes.remove(box);
					break;
				}
			}
			_field[row][col] = null;
			return;
		}

		switch (unit) {
		case WALL:
			_field[row][col] = Unit.WALL;
			break;
		case TARGET:
			_field[row][col] = Unit.TARGET;
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

	private boolean checkFieldBounds(int row, int col) {
		if ((row < 0) || (row >= _field.length) || (col < 0)
				|| (col >= _field[0].length)) {
			return false;
		}
		return true;
	}

	public Unit getUnit(int row, int col) throws OutInField {
		if (!checkFieldBounds(row, col)) {
			throw new OutInField();
		}

		if ((_man != null) && (_man.getRow() == row) && (_man.getCol() == col)) {
			return Unit.MAN;
		}

		for (BoxObject box : _boxes) {
			if ((box.getRow() == row) && (box.getCol() == col)) {
				return Unit.BOX;
			}
		}

		return _field[row][col];
	}

	public ArrayList<BoxObject> getBoxes() {
		return _boxes;
	}

}

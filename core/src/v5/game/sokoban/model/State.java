package v5.game.sokoban.model;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;


public class State {

	Unit[][] _field = new Unit[0][0];
	Point _manPos = new Point();
	Point[] _targets = new Point[0];
//	Point[] _boxes = new Point[0];
	private Logic _model;

	State(Logic model) {
		_model = model;
	}

	public void createEmptyField() {
	}

	public void createRectangleField(int row, int col) {
		_field = new Unit[row][col];
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
//		_boxes = new Point[1];
//		_boxes[0] = new Point(2, 5);
		_field[3][5] = Unit.BOX;

		// create man
		_model.setMan(3, 2);

		// create target
		_targets = new Point[1];
		_targets[0] = new Point(3, 8);
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
//		_boxes = new Point[1];
//		_boxes[0] = new Point(2, 5);
		_field[2][5] = Unit.BOX;
		_field[4][5] = Unit.BOX;

		// create man
		_model.setMan(3, 2);

		// create target
		_targets = new Point[2];
		_targets[0] = new Point(2, 8);
		_targets[1] = new Point(4, 8);
	}

	public Point getMan() {
		return _manPos;
	}
	
	public Unit[][] getField() {
		return _field;
	}

	public Unit getUnit(int row, int col) throws OutInField {

		Unit unit = null;

		try {
			unit = _field[row][col];
		} catch (Exception e) {
			throw new OutInField();
		}

		return unit;
	}

//	public Point[] getBoxes() {
//		return _boxes;
//	}

	public Point[] getTargets() {
		return _targets;
	}


}

package v5.game.sokoban.model;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;


public class State {

	Unit[][] _field;
	Point _manPosition = new Point();
	private Logic _model;

	State(Logic model) {
		_model = model;
	}

	public Unit[][] createEmptyField() {
		_field = new T.Unit[0][0];
		return _field;
	}

	public Unit[][] createRectangleField(int row, int col) {
		_field = new T.Unit[row][col];
		return _field;
	}

	public Unit[][] createDefaultField() {
		_field = new Unit[5][11];

		// create walls
		int row = 0;
		for (int col = 0; col < _field[row].length; col++) {
			_field[row][col] = Unit.WALL;
		}
		for (int rrow = 1; rrow <= 3; rrow++) {
			_field[rrow][0] = Unit.WALL;
			_field[rrow][_field[row].length - 1] = Unit.WALL;
		}
		row = _field.length - 1;
		for (int col = 0; col < _field[row].length; col++) {
			_field[row][col] = Unit.WALL;
		}

		// create box
		_field[2][5] = Unit.BOX;

		// create man
		_model.setManPosition(2, 2);

		// create target
		_field[2][8] = Unit.TARGET;

		return _field;
	}
	
	public Point getManPosition() {
		return _manPosition;
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


}

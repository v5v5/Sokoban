package v5.game.sokoban.model;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;


public class State {

	T.Unit[][] _field;
	T.Point _manPosition = new T.Point();
	private Logic _model;

	State(Logic model) {
		_model = model;
	}

	public T.Unit[][] createEmptyField() {
		_field = new T.Unit[0][0];
		return _field;
	}

	public T.Unit[][] createRectangleField(int row, int col) {
		_field = new T.Unit[row][col];
		return _field;
	}

	public T.Unit[][] createDefaultField() {
		T.Unit[][] _field = new T.Unit[5][10];

		// create walls
		int row = 0;
		for (int col = 0; col < _field[row].length; col++) {
			_field[row][col] = T.Unit.WALL;
		}
		for (int rrow = 1; rrow <= 3; rrow++) {
			_field[rrow][0] = T.Unit.WALL;
			_field[rrow][_field[row].length - 1] = T.Unit.WALL;
		}
		row = _field.length - 1;
		for (int col = 0; col < _field[row].length; col++) {
			_field[row][col] = T.Unit.WALL;
		}

		// create box
		_field[2][5] = T.Unit.BOX;

		// create man
		_model.setManPosition(2, 2);

		// create target
		_field[2][7] = T.Unit.TARGET;

		return _field;
	}
	
	public Point getManPosition() {
		return _manPosition;
	}
	
	public T.Unit[][] getField() {
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

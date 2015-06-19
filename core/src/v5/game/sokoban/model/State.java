package v5.game.sokoban.model;

import java.util.ArrayList;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Unit;
import v5.game.sokoban.model.dynamicObjects.BoxObject;
import v5.game.sokoban.model.dynamicObjects.ManObject;

public class State {

	Logic _logic;

	// fill static objects (Unit.WALL, Unit.TARGET, null)
	Unit[][] _field = new Unit[0][0];

	// dynamic objects (Unit.MAN, Unit.BOX)
	ManObject _man;
	ArrayList<BoxObject> _boxes = new ArrayList<BoxObject>();

	public State(Logic logic) {
		_logic = logic;
	}

	public void clear() {
		_field = new Unit[0][0];
		_man = null;
		_boxes.clear();
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

	public ArrayList<BoxObject> getBoxes() {
		return _boxes;
	}

	public BoxObject getBoxObject(int row, int col) {
		for (BoxObject box : _boxes) {
			if ((box.getRow() == row) && (box.getCol() == col)) {
				return box;
			}
		}
		return null;
	}

	public Unit getUnit(int row, int col) throws OutInField {
		if (!_logic.checkFieldBounds(row, col)) {
			throw new OutInField();
		}

		ManObject m = _man;
		if ((m != null) && (m.getRow() == row) && (m.getCol() == col)) {
			return Unit.MAN;
		}

		for (BoxObject box : _boxes) {
			if ((box.getRow() == row) && (box.getCol() == col)) {
				return Unit.BOX;
			}
		}

		return _field[row][col];
	}

}

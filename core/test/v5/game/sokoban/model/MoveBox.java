package v5.game.sokoban.model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;

@RunWith(Parameterized.class)
public class MoveBox {

	Logic _gameModel;
	static int manRow = 5;
	static int manCol = 5;

	Point _expectedPos;
	Direction _dir;
	Point _manPos;
	Unit _box;
	Point _boxPos;
	Unit _unit;
	Point _unitPos;

	@Parameters
	public static List<Object[]> data() {

		List<Object[]> list = new LinkedList<Object[]>();

		Point posMan = new Point(manRow, manCol);
		Point posBoxBefore = null;
		Point posBoxAfter = null;
		Point posUnit = null;

		// test direction right
		posBoxBefore = new Point(manRow, manCol + 1);
		posBoxAfter = new Point(manRow, manCol + 1);
		posUnit = new Point(manRow, manCol + 2);

		list.add(p(posBoxAfter, Direction.RIGHT, posMan, Unit.BOX,
				posBoxBefore, Unit.WALL, posUnit));

		list.add(p(posBoxAfter, Direction.RIGHT, posMan, Unit.BOX,
				posBoxBefore, Unit.BOX, posUnit));

		posBoxAfter = new Point(manRow, manCol + 2);
		list.add(p(posBoxAfter, Direction.RIGHT, posMan, Unit.BOX,
				posBoxBefore, null, posUnit));

		// test direction left
		posBoxBefore = new Point(manRow, manCol - 1);
		posBoxAfter = new Point(manRow, manCol - 1);
		posUnit = new Point(manRow, manCol - 2);

		list.add(p(posBoxAfter, Direction.LEFT, posMan, Unit.BOX,
				posBoxBefore, Unit.WALL, posUnit));

		list.add(p(posBoxAfter, Direction.LEFT, posMan, Unit.BOX,
				posBoxBefore, Unit.BOX, posUnit));

		posBoxAfter = new Point(manRow, manCol - 2);
		list.add(p(posBoxAfter, Direction.LEFT, posMan, Unit.BOX,
				posBoxBefore, null, posUnit));

		// test direction up
		posBoxBefore = new Point(manRow + 1, manCol);
		posBoxAfter = new Point(manRow + 1, manCol);
		posUnit = new Point(manRow + 2, manCol);

		list.add(p(posBoxAfter, Direction.UP, posMan, Unit.BOX,
				posBoxBefore, Unit.WALL, posUnit));

		list.add(p(posBoxAfter, Direction.UP, posMan, Unit.BOX,
				posBoxBefore, Unit.BOX, posUnit));

		posBoxAfter = new Point(manRow + 2, manCol);
		list.add(p(posBoxAfter, Direction.UP, posMan, Unit.BOX,
				posBoxBefore, null, posUnit));

		// test direction down
		posBoxBefore = new Point(manRow - 1, manCol);
		posBoxAfter = new Point(manRow - 1, manCol);
		posUnit = new Point(manRow - 2, manCol);

		list.add(p(posBoxAfter, Direction.DOWN, posMan, Unit.BOX,
				posBoxBefore, Unit.WALL, posUnit));

		list.add(p(posBoxAfter, Direction.DOWN, posMan, Unit.BOX,
				posBoxBefore, Unit.BOX, posUnit));

		posBoxAfter = new Point(manRow - 2, manCol);
		list.add(p(posBoxAfter, Direction.DOWN, posMan, Unit.BOX,
				posBoxBefore, null, posUnit));

		return list;
	}

	private static Object[] p(Point expectedPos, Direction dir, Point manPos,
			Unit nearUnit, Point nearPos, Unit farUnit, Point farPos) {

		Object[] o = new Object[] { expectedPos, dir, manPos, nearUnit,
				nearPos, farUnit, farPos };

		return o;
	}

	public MoveBox(Point expectedPos, Direction dir, Point manPos,
			Unit nearUnit, Point nearPos, Unit farUnit, Point farPos) {

		_expectedPos = expectedPos;
		_dir = dir;
		_manPos = manPos;
		_box = nearUnit;
		_boxPos = nearPos;
		_unit = farUnit;
		_unitPos = farPos;
	}

	@Before
	public void setup() {
		_gameModel = new Logic();
		_gameModel.getState().createRectangleField(10, 10);
	}

	@Test
	public void moveBox() {
		_gameModel.setMan(_manPos._row, _manPos._col);
		try {
			_gameModel.setUnit(_boxPos._row, _boxPos._col, _box);
			_gameModel.setUnit(_unitPos._row, _unitPos._col, _unit);
		} catch (OutInField e) {
			e.printStackTrace();
		}

		_gameModel.moveMan(_dir);

		Unit unit = null;
		try {
			unit = _gameModel.getState().getUnit(_expectedPos._row, _expectedPos._col);
		} catch (OutInField e) {
			e.printStackTrace();
		}

		assertEquals(Unit.BOX, unit);
	}

}

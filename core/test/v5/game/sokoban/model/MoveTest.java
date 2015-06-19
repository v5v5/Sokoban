package v5.game.sokoban.model;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.runners.Parameterized.Parameters;

import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;

public class MoveTest {

	Logic _logic;
	static int manRow = 5;
	static int manCol = 5;

	boolean _expectedCan;
	Point _expectedPos;
	Direction _dir;
	Point _manPos;
	Unit _nearUnit;
	Point _nearPos;
	Unit _farUnit;
	Point _farPos;

	@Parameters
	public static List<Object[]> data() {

		List<Object[]> list = new LinkedList<Object[]>();

		Point posBefore = new Point(manRow, manCol);
		Point posAfter = null;

		// test direction right
		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.RIGHT, posBefore, Unit.WALL,
				new Point(manRow, manCol + 1), null, new Point(manRow,
						manCol + 2)));

		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.RIGHT, posBefore, Unit.BOX,
				new Point(manRow, manCol + 1), Unit.WALL, new Point(manRow,
						manCol + 2)));

		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.RIGHT, posBefore, Unit.BOX,
				new Point(manRow, manCol + 1), Unit.BOX, new Point(manRow,
						manCol + 2)));

		posAfter = new Point(manRow, manCol + 1);
		list.add(p(true, posAfter, Direction.RIGHT, posBefore, Unit.BOX,
				new Point(manRow, manCol + 1), null, new Point(manRow,
						manCol + 2)));

		// test direction left
		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.LEFT, posBefore, Unit.WALL,
				new Point(manRow, manCol - 1), null, new Point(manRow,
						manCol - 2)));

		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.LEFT, posBefore, Unit.BOX,
				new Point(manRow, manCol - 1), Unit.WALL, new Point(manRow,
						manCol - 2)));

		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.LEFT, posBefore, Unit.BOX,
				new Point(manRow, manCol - 1), Unit.BOX, new Point(manRow,
						manCol - 2)));

		posAfter = new Point(manRow, manCol - 1);
		list.add(p(true, posAfter, Direction.LEFT, posBefore, Unit.BOX,
				new Point(manRow, manCol - 1), null, new Point(manRow,
						manCol - 2)));

		// test direction up
		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.UP, posBefore, Unit.WALL,
				new Point(manRow - 1, manCol), null, new Point(manRow - 2,
						manCol)));

		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.UP, posBefore, Unit.BOX,
				new Point(manRow - 1, manCol), Unit.WALL, new Point(manRow - 2,
						manCol)));

		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.UP, posBefore, Unit.BOX,
				new Point(manRow - 1, manCol), Unit.BOX, new Point(manRow - 2,
						manCol)));

		posAfter = new Point(manRow - 1, manCol);
		list.add(p(true, posAfter, Direction.UP, posBefore, Unit.BOX,
				new Point(manRow - 1, manCol), null, new Point(manRow - 2,
						manCol)));

		// test direction down
		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.DOWN, posBefore, Unit.WALL,
				new Point(manRow + 1, manCol), null, new Point(manRow + 2,
						manCol)));

		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.DOWN, posBefore, Unit.BOX,
				new Point(manRow + 1, manCol), Unit.WALL, new Point(manRow + 2,
						manCol)));

		posAfter = new Point(manRow, manCol);
		list.add(p(false, posAfter, Direction.DOWN, posBefore, Unit.BOX,
				new Point(manRow + 1, manCol), Unit.BOX, new Point(manRow + 2,
						manCol)));

		posAfter = new Point(manRow + 1, manCol);
		list.add(p(true, posAfter, Direction.DOWN, posBefore, Unit.BOX,
				new Point(manRow + 1, manCol), null, new Point(manRow + 2,
						manCol)));

		return list;
	}

	private static Object[] p(boolean expectedCan, Point expectedPos,
			Direction dir, Point manPos, Unit nearUnit, Point nearPos,
			Unit farUnit, Point farPos) {

		Object[] o = new Object[] { expectedCan, expectedPos, dir, manPos,
				nearUnit, nearPos, farUnit, farPos };

		return o;
	}

	public MoveTest(boolean expectedCan, Point expectedPos, Direction dir,
			Point manPos, Unit nearUnit, Point nearPos, Unit farUnit,
			Point farPos) {

		_expectedCan = expectedCan;
		_expectedPos = expectedPos;
		_dir = dir;
		_manPos = manPos;
		_nearUnit = nearUnit;
		_nearPos = nearPos;
		_farUnit = farUnit;
		_farPos = farPos;

	}

	@Before
	public void setup() {
		_logic = new Logic();
		_logic.getState().setField(10, 10);
	}

}

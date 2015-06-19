package v5.game.sokoban.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Point;
import v5.game.sokoban.model.T.Unit;

@RunWith(Parameterized.class)
public class CanMove extends MoveTest {

	public CanMove(boolean expectedCan, Point expectedPos, Direction dir,
			Point manPos, Unit nearUnit, Point nearPos, Unit farUnit,
			Point farPos) {

		super(expectedCan, expectedPos, dir, manPos, nearUnit, nearPos,
				farUnit, farPos);
	}

	// @Test
	// public void canMoveOnWall() {
	// _gameModel.setWall(manRow, manCol + 1);
	// _gameModel.setUnit(manRow, manCol + 2, null);
	// boolean can = _gameModel.canManMove(T.Direction.RIGHT);
	// assertFalse(can);
	// _gameModel.setUnit(manRow, manCol + 1, null);
	// can = _gameModel.canManMove(T.Direction.RIGHT);
	// assertTrue(can);
	// }
	//
	// @Test
	// public void canMoveOnBox() {
	// _gameModel.setBox(manRow, manCol + 1);
	// _gameModel.setUnit(manRow, manCol + 2, null);
	// boolean can = _gameModel.canManMove(T.Direction.RIGHT);
	// assertTrue(can);
	// _gameModel.setUnit(manRow, manCol + 1, null);
	// can = _gameModel.canManMove(T.Direction.RIGHT);
	// assertTrue(can);
	// }
	//
	// @Test
	// public void canMoveOnBoxBox() {
	// _gameModel.setBox(manRow, manCol + 1);
	// _gameModel.setBox(manRow, manCol + 2);
	// boolean can = _gameModel.canManMove(T.Direction.RIGHT);
	// assertFalse(can);
	// _gameModel.setUnit(manRow, manCol + 1, null);
	// can = _gameModel.canManMove(T.Direction.RIGHT);
	// assertTrue(can);
	// }
	//
	// @Test
	// public void canMoveOnBoxWall() {
	// _gameModel.setBox(manRow, manCol + 1);
	// _gameModel.setWall(manRow, manCol + 2);
	// boolean can = _gameModel.canManMove(T.Direction.RIGHT);
	// assertFalse(can);
	// _gameModel.setUnit(manRow, manCol + 1, null);
	// can = _gameModel.canManMove(T.Direction.RIGHT);
	// assertTrue(can);
	// }

	@Test
	public void canMove() {
		_gameModel.setMan(_manPos._row, _manPos._col);

		Unit unit = null;
		try {
			_gameModel.setUnit(_nearPos._row, _nearPos._col, _nearUnit);
			unit = _gameModel._state.getUnit(_nearPos._row, _nearPos._col);
		} catch (OutInField e) {
			e.printStackTrace();
		}
		assertEquals(_nearUnit, unit);

		try {
			_gameModel.setUnit(_farPos._row, _farPos._col, _farUnit);
			unit = _gameModel._state.getUnit(_farPos._row, _farPos._col);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(_farUnit, unit);

		boolean can = _gameModel.canManMove(_dir);
		assertEquals(_expectedCan, can);

		try {
			_gameModel.setUnit(_nearPos._row, _nearPos._col, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		can = _gameModel.canManMove(_dir);
		assertTrue(can);
	}
}

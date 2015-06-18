package v5.game.sokoban.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import v5.game.sokoban.model.E.OutInField;
import v5.game.sokoban.model.T.Unit;

public class SokobanGameTest {

	Logic _gameModel;

	@Before
	public void setup() {
		_gameModel = new Logic();
	}

	@Test
	public void createEmptyField() {
		Unit[][] gameField = _gameModel.getState().createEmptyField();

		for (int row = 0; row < gameField.length; row++) {
			for (int col = 0; col < gameField[row].length; col++) {
				assertEquals(gameField[row][col], 0);
			}
		}
	}

	@Test
	public void createRectangleField() {

		Unit[][] gameField = _gameModel.getState().createRectangleField(8, 6);

		int countRows = gameField.length;
		assertEquals(8, countRows);

		for (int row = 0; row < countRows; row++) {
			T.Unit[] gameRow = gameField[row];
			assertFalse(null == gameRow);
			int countCols = gameRow.length;
			assertEquals(6, countCols);
		}
	}

	@Test
	public void setUnit() {
		_gameModel.getState().createRectangleField(11, 11);

		Unit unit = null;
		try {
			_gameModel.setUnit(0, 0, Unit.BOX);
			unit = _gameModel._state.getUnit(0, 0);
		} catch (OutInField e) {
			e.printStackTrace();
		}
		assertEquals(Unit.BOX, unit);
	}

	// @Test
	// public void moveMan() {
	// _gameModel.getState().createRectangleField(5, 10);
	//
	// int manRow = 2;
	// int manCol = 1;
	// T.Point mp = _gameModel.setManPosition(manRow, manCol);
	//
	// _gameModel.moveMan(T.Direction.RIGHT);
	//
	// assertEquals(manRow, mp.row);
	// assertEquals(manCol + 1, mp.col);
	// }
	//
	// @Test
	// public void moveManToRightOnWall() {
	// _gameModel.getState().createRectangleField(5, 10);
	//
	// int manRow = 2;
	// int manCol = 3;
	//
	// T.Point mp = _gameModel.setManPosition(manRow, manCol);
	// _gameModel.setWall(manRow, manCol + 1);
	//
	// _gameModel.moveMan(T.Direction.RIGHT);
	//
	// assertEquals(manRow, mp.row);
	// assertEquals(manCol, mp.col);
	// }

}

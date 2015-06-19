package v5.game.sokoban.model.dynamicObjects;

import java.util.ArrayList;

import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.model.T.Point;

public class ManObject extends DynamicObject {

	public ManObject(State state, int row, int col) {
		super(state, row, col);
	}

	@Override
	protected boolean checkDynamicObject(Direction dir) {
		Point newPos = getNewPos(dir);

		ArrayList<BoxObject> boxes = _state.getBoxes();
		for (BoxObject box : boxes) {
			if ((box._row == newPos._row) && (box._col == newPos._col)) {
				return box.canMove(dir);
			}
		}
		return true;
	}

}

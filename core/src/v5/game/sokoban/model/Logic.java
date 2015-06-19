package v5.game.sokoban.model;

import v5.game.sokoban.model.T.Direction;

public class Logic extends State implements LogicInterface {

	public State getState() {
		return this;
	}

	@Override
	public boolean moveMan(Direction dir) {
		if (!_man.canMove(dir)) {
			return false;
		}

		return _man.move(dir);
	}

}

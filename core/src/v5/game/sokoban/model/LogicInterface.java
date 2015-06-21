package v5.game.sokoban.model;

import v5.game.sokoban.model.T.Direction;

public interface LogicInterface {

	public boolean moveMan(Direction direction);
	public boolean loadField(int index);

}

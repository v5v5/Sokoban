package v5.game.sokoban.controller;

import v5.game.sokoban.model.LogicInterface;
import v5.game.sokoban.model.Model;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.view.View;

public class Controller implements LogicInterface {
	
	private Model _model;
	private View _view;
	
	public Controller() {
		_model = new Model();
		_view = new View();
		_model.addListener(_view);
	}

	@Override
	public boolean moveMan(Direction direction) {
		return _model.moveMan(direction);
	}
	

}

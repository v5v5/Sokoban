package v5.game.sokoban.controller;

import v5.game.sokoban.model.LogicInterface;
import v5.game.sokoban.model.Model;
import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.view.View;

public class Controller implements LogicInterface, ControllerInterface {

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

	@Override
	public View getView() {
		return _view;
	}

	public void setFieldDefault() {
		_model.setFieldDefault();
	}

	@Override
	public void repaintView() {
		State state = _model.getLogic().getState();
		_view.onChange(state);
	}
	
	public void loadField() {
		_model.loadField();
	}

}

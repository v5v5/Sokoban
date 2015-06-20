package v5.game.sokoban.controller;

import v5.game.sokoban.model.LogicInterface;
import v5.game.sokoban.model.Model;
import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.view.View;

public class Controller implements LogicInterface {

	private Model _model;
	private View _view;

	public Controller() {
		_model = new Model();
	}

	@Override
	public boolean moveMan(Direction direction) {
		return _model.moveMan(direction);
	}

	public void setView(View view) {
		_view = view;
		_model.addListener(_view);
	}

	public void setFieldDefault() {
		_model.setFieldDefault();
	}

	public void repaintView() {
		try {
			State state = _model.getLogic().getState();
			_view.onChange(state);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadField() {
		_model.loadField();
	}

}

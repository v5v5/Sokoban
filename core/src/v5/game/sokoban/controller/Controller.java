package v5.game.sokoban.controller;

import v5.game.sokoban.SokobanActor;
import v5.game.sokoban.model.Model;
import v5.game.sokoban.model.ModelListener;
import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.view.View;

public class Controller implements ModelListener {

	private Model _model;
	private View _view;
		
	final int SIZE = SokobanActor.SIZE;

	public Controller() {
		_model = new Model();
		_model.addListener(this);
	}

	public boolean moveMan(Direction direction) {
		return _model.moveMan(direction);
	}

	public void setView(View view) {
		_view = view;
	}

	public void setFieldDefault() {
		_model.setFieldDefault();
	}

	public void repaintView() {
		try {
			State state = _model.getLogic().getState();
			this.onChange(Event.MOVE_MAN, state);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean loadField(int index) {
		return _model.loadField(index);
	}

	@Override
	public void onChange(Event event, State state) {
		switch (event) {
		case NEW_GAME:
			if (_view == null)
				return;
			_view.createActors(state);
			break;
		case MOVE_MAN:
			if (_view == null)
				return;
			_view.draw(state);
			break;
		case GAME_OVER:
			System.err.println("Game Over!");
			if (_view == null)
				return;
			_view.draw(state);

			break;
		default:
			break;
		}

	}


}

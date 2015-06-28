package v5.game.sokoban.controller;

import java.util.LinkedList;

import v5.game.sokoban.SokobanActor;
import v5.game.sokoban.model.Model;
import v5.game.sokoban.model.ModelListener;
import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Direction;
import v5.game.sokoban.view.View;

public class Controller implements ModelListener {

	private Model _model;
	private View _view;
		
	private LinkedList<Event> events = new LinkedList<Event>();
	
	final int SIZE = SokobanActor.SIZE;

	public Controller() {
		_model = new Model();
		_model.addListener(this);
	}

	public boolean moveMan(Direction direction) {
		return _model.moveMan(direction);
	}

	public LinkedList<Event> getEvents() {
		return events;
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
		events.add(event);

		switch (event) {
		case NEW_GAME:
			if (_view == null)
				return;
			_view.init(state);
			break;
		case MOVE_MAN:
			break;
		case GAME_OVER:
			System.out.println("Controller.onChange(): You Winner!");
			break;
		default:
			break;
		}

		if (_view == null)
			return;
		_view.draw(state);

		events.clear();

	}


}

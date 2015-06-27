package v5.game.sokoban.model;

import java.util.ArrayList;
import java.util.List;

import v5.game.sokoban.model.ModelListener.Event;
import v5.game.sokoban.model.T.Direction;

public class Model implements ModelInterface {

	List<ModelListener> _listeners = new ArrayList<ModelListener>();
	private Logic _logic = new Logic();

	public Logic getLogic() {
		return _logic;
	}

	@Override
	public void addListener(ModelListener listener) {
		_listeners.add(listener);
	}

	@Override
	public void removeListener(ModelListener listener) {
		_listeners.remove(listener);
	}

	@Override
	public void fireChangedEvent(Event event) {
		for (ModelListener modelListener : _listeners) {
			modelListener.onChange(event, _logic.getState());
		}
	}

	public boolean moveMan(Direction direction) {
		boolean b = _logic.moveMan(direction);
		if (b) {
			if (_logic._state._gameOver) {
				fireChangedEvent(Event.GAME_OVER);
			} else {
				fireChangedEvent(Event.UPDATE);
			}
		}
		return b;
	}

	public void setFieldDefault() {
		// _logic.getState().createDefaultField();
		_logic.createComplexField();
		fireChangedEvent(Event.NEW_GAME);
	}

	public boolean loadField(int index) {
		boolean b = _logic.loadField(index);
		if (b) {
			fireChangedEvent(Event.NEW_GAME);
		}
		return b;
	}

}

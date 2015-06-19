package v5.game.sokoban.model;

import java.util.ArrayList;
import java.util.List;

import v5.game.sokoban.model.T.Direction;

public class Model implements ModelInterface, LogicInterface {

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
	public void fireChangedEvent() {
		for (ModelListener modelListener : _listeners) {
			modelListener.onChange(_logic.getState());
		}
	}

	@Override
	public boolean moveMan(Direction direction) {
		if (_logic.moveMan(direction)) {
			fireChangedEvent();
			return true;
		}
		return false;
	}

	public void setFieldDefault() {
		// _logic.getState().createDefaultField();
		_logic.createComplexField();
		fireChangedEvent();
	}

	public void loadField() {
		FieldLoader.load(_logic.getState());
		fireChangedEvent();
	}

}

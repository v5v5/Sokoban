package v5.game.sokoban.model;

import java.util.ArrayList;
import java.util.List;

import v5.game.sokoban.model.T.Direction;

public class Model implements ModelInterface {

	List<ModelListener> _listeners = new ArrayList<ModelListener>();
	private Logic _logic = new Logic();
	
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
		if (_logic.moveMan(direction)){
			fireChangedEvent();
			return true;
		} 
		return false;
	}
	
}

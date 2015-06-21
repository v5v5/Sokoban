package v5.game.sokoban.model;

import v5.game.sokoban.model.ModelListener.Event;

public interface ModelInterface {

	public void addListener(ModelListener listener);

	public void removeListener(ModelListener listener);

	void fireChangedEvent(Event event);

}

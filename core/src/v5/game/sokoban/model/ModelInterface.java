package v5.game.sokoban.model;

public interface ModelInterface {
	
	public void addListener(ModelListener listener);
	public void removeListener(ModelListener listener);

	void fireChangedEvent();

}

package v5.game.sokoban.model;

public interface ModelListener {
	
	enum Event {NEW_GAME, UPDATE, GAME_OVER};

	void onChange(Event event, State state);

}

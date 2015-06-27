package v5.game.sokoban.model;

public interface ModelListener {
	
	enum Event {NEW_GAME, MOVE_MAN, GAME_OVER};

	void onChange(Event event, State state);

}

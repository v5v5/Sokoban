package v5.game.sokoban.model;

public interface ModelListener {
	
	public enum Event {SHOW_MENU, NEW_GAME, MOVE_MAN, GAME_OVER};

	void onChange(Event event, State state);

}

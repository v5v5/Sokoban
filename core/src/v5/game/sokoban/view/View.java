package v5.game.sokoban.view;

import v5.game.sokoban.model.ModelListener;
import v5.game.sokoban.model.State;
import v5.game.sokoban.model.T.Unit;

public class View implements ModelListener, ViewInterface {

	private Graphics _graphics;

	@Override
	public void onChange(State state) {
		draw(state);
	}

	private void draw(final State state) {
		drawField(state.getField());
//		drawFigure(state.getFigure().getData(), state.row, state.col);
	}

	private void drawField(Unit[][] field) {
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				drawBox(field[row][col], row, col);
			}
		}
	}

	private void drawBox(Unit unit, int row, int col) {
		_graphics.fillRect(unit, row, col);
	}

	@Override
	public void setGraphics(Graphics g) {
		this._graphics = g;		
	}

}

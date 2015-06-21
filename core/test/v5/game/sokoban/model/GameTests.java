package v5.game.sokoban.model;

import org.junit.Before;
import org.junit.Test;

public class GameTests {

	Model _model;

	@Before
	public void setup() {
		_model = new Model();
	}

	@Test
	public void loadField() {
		_model.loadField(0);
	}
	
	@Test
	public void gameOver() {
//		_model.
	}

}
